package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseController;
import controller.api.subapi.AdministrationRoutes;
import dao.Factory;
import model.*;
import model.json.Schedule;
import spark.ModelAndView;
import utils.Converter;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;


public class SiteRoutes extends BaseController {
    private static Logger log = Logger.getLogger(SiteRoutes.class.getName());

    private String groupsColumn = "groupsName";
    private String formOfTrainingColumn = "formOfTrainingName";

    public void initRoutes(){
        new AdministrationRoutes();
    }

    private HashMap getGroupsName() {
        HashMap<String, Object> model = new HashMap<>();

        List<Groups> listGroups = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
        List<String> groupsNameList = listGroups.stream().map(groupsLists -> groupsLists.getGroupsName()).collect(Collectors.toList());

        model.put("groupsName", new String());
        model.put("groupsNameArray", groupsNameList);
        return model;
    }

    private void setSchedule(Schedule scheduleJsonModel) {
        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn, scheduleJsonModel.getGroupName()));

        for(int i = 0; i< scheduleJsonModel.getWeekDay().size();i++) {
            Weekday weekday = Weekday.valueOf(scheduleJsonModel.getWeekDay().get(i).getWeekDayName());
            for (int j = 0; j < scheduleJsonModel.getWeekDay().get(i).getLessonTime().size(); j++) {

                String discipline = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getDiscipline();
                String employmentType = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getEmploymentType();
                String lectureHall = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getLectureHall();
                String teacher = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getTeacher();

                if(discipline.equals("")&&employmentType.equals("")&&lectureHall.equals("")&&teacher.equals("")){}
                else {
                    model.Schedule schedule = new model.Schedule();
                    schedule.setGroups(groups);
                    schedule.setWeekday(weekday);
                    String lessonTimeStart = Converter.startToDouble(scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getTime()).toString();
//                    String lessonTimeEnd = Converter.endToDouble(scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getTime()).toString();
                    LessonTime lessonTime = LessonTime.class.cast(Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getObject("lessonTimeStart",lessonTimeStart));
                    schedule.setLessonTime(lessonTime);
                    schedule.setNumberWeekday(NumberWeekday.first);

                    if (!discipline.equals("")) {
                        schedule.setDiscipline(setScheduleElement("disciplineName", discipline, new Discipline(discipline), Discipline.class));
                    }
                    if (!employmentType.equals("")) {
                        schedule.setEmploymentType(setScheduleElement("employmentTypeName", employmentType, new EmploymentType(employmentType), EmploymentType.class));
                    }
                    if (!lectureHall.equals("")) {
                        schedule.setLectureHall(setScheduleElement("lectureHallName", lectureHall, new LectureHall(lectureHall), LectureHall.class));

                    }
                    if (!teacher.equals("")) {
                        schedule.setTeacher(setScheduleElement("teacherName", teacher, new Teacher(teacher), Teacher.class));
                    }

                    Map<String,Object> map = new HashMap<>();
                    map.put("weekday",schedule.getWeekday());
                    map.put("numberWeekday",schedule.getNumberWeekday());
                    map.put("lessonTime",schedule.getLessonTime().getLessonTimeId());

                    if( Factory.getInstance().getGenericRepositoryInterface(model.Schedule.class).getObject(map) == null){
                        Factory.getInstance().getGenericRepositoryInterface().addObject(schedule);
                    }

                }
            }
        }
    }

    private <T> T setScheduleElement(String columnName, String columnValue, Object o, Class<T> tClass){
        Boolean bool = true;
        T scheduleElement = null;
        while (bool) {
            scheduleElement = tClass.cast(Factory.getInstance().getGenericRepositoryInterface(tClass).getObject(columnName, columnValue));
            if (scheduleElement != null) {
                bool = false;
            } else {
                Factory.getInstance().getGenericRepositoryInterface().addObject(o);
            }
        }
        return scheduleElement;
    }

    private HashMap getSchedule() {
        HashMap<String, Object> model = new HashMap<>();
        List<Weekday> weekdayNameList = Arrays.asList(Weekday.values());

        List<LessonTime> lessonTimeList = Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getAllObjects();
        List<String> lessonTimeNameList = new ArrayList<>();
        List<Double> lessonTimeStartList = new ArrayList<>();
        List<Double> lessonTimeEndList = new ArrayList<>();
        for (LessonTime Lists : lessonTimeList) {

            lessonTimeStartList.add(Lists.getLessonTimeStart());

        }
        for (LessonTime Lists : lessonTimeList) {

            lessonTimeEndList.add(Lists.getLessonTimeEnd());

        }
        Collections.sort(lessonTimeStartList);
        Collections.sort(lessonTimeEndList);
        for (int i = 0; i < lessonTimeEndList.size(); i++) {
            lessonTimeNameList.add(Converter.toString(lessonTimeStartList.get(i), lessonTimeEndList.get(i)));
        }
        List<LectureHall> lectureHallList = Factory.getInstance().getGenericRepositoryInterface(LectureHall.class).getAllObjects();
        List<String> lectureHallNameList = lectureHallList.stream().map(lectureHallLists -> lectureHallLists.getLectureHallName()).collect(Collectors.toList());
        List<Teacher> teacherList = Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getAllObjects();
        List<String> teacherNameList = teacherList.stream().map(Lists -> Lists.getTeacherName()).collect(Collectors.toList());
        List<Discipline> disciplineList = Factory.getInstance().getGenericRepositoryInterface(Discipline.class).getAllObjects();
        List<String> disciplineNameList = disciplineList.stream().map(Lists -> Lists.getDisciplineName()).collect(Collectors.toList());
        List<EmploymentType> employmentTypeList = Factory.getInstance().getGenericRepositoryInterface(EmploymentType.class).getAllObjects();
        List<String> employmentTypeNameList = employmentTypeList.stream().map(Lists -> Lists.getEmploymentTypeName()).collect(Collectors.toList());
        model.put("lectureHall", new String());
        model.put("lectureHallArray", lectureHallNameList);
        model.put("teacher", new String());
        model.put("teacherArray", teacherNameList);
        model.put("discipline", new String());
        model.put("disciplineArray", disciplineNameList);
        model.put("employmentType", new String());
        model.put("employmentTypeArray", employmentTypeNameList);
        model.put("lessonTime", new String());
        model.put("lessonTimeArray", lessonTimeNameList);
        model.put("weekDayName", new String());
        model.put("weekDayArray", weekdayNameList);
        return model;
    }

    @Override
    public void routes() {
        get("/schedule", (request, response) -> {
            log.info("Starting /schedule");
            return new ModelAndView(getSchedule(), "/public/schedule.html");
        }, new VelocityTemplateEngine());

        get("/", (request, response) -> {
            log.info("Starting /");
            return new ModelAndView(getGroupsName(), "/public/index.html");
        }, new VelocityTemplateEngine());

        get("/index", (request, response) -> {
            log.info("Starting /index");
            return new ModelAndView(getGroupsName(), "/public/index.html");
        }, new VelocityTemplateEngine());

        post("/api/addLessonTime", (request, response) -> {
            log.info("Starting /api/addLessonTime");
            try {
                LessonTime lessonTime = new LessonTime(Double.parseDouble(request.queryParams("timeStart")), Double.parseDouble(request.queryParams("timeEnd")));
                response.redirect("/schedule");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(lessonTime);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post("/api/addGroups", (request, response) -> {
            log.info("Starting /api/addGroups");
            String groupsName = request.queryParams(groupsColumn);
            try {
                Groups addGroups = new Groups(groupsName);
                response.redirect("/index");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(addGroups);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post("/api/deleteGroups", (request, response) -> {
            log.info("Starting /api/deleteGroups");
            String a =request.queryParams("name");
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn, a));
            response.redirect("/index");
            return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
        });

        post("/api/setSchedule", (request, response) -> {
            log.info("Starting /api/setSchedule");
            Gson gson = new GsonBuilder().create();
            String a = request.queryParams("name");
            Schedule schedule = new Schedule();
            try {
                schedule = gson.fromJson(a, Schedule.class);
                setSchedule(schedule);
                return "";
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post("/api/users/registration", (request, response) -> {
            log.info("Starting /api/users/registration");
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn, request.queryParams(groupsColumn)));
            Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            return Factory.getInstance().getUsersDAO().addUser(registration);
        });

        post("/api/users/login", (request, response) -> {
            log.info("Starting /api/users/login");
            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

           return Factory.getInstance().getUsersDAO().loginUsers(login);
        });
    }
}
