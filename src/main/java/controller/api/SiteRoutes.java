package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseController;
import dao.Factory;
import model.*;
import model.json.Schedule;
import spark.ModelAndView;
import utils.Converter;
import utils.template.VelocityTemplateEngine;

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

    private HashMap getGroupsName() {
        HashMap<String, Object> model = new HashMap<>();

        List<Groups> listGroups = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
        List<String> groupsNameList = listGroups.stream().map(groupsLists -> groupsLists.getGroupsName()).collect(Collectors.toList());

        model.put("groupsName", new String());
        model.put("groupsNameArray", groupsNameList);
        return model;
    }

    private HashMap getSchedule() {
        HashMap<String, Object> model = new HashMap<>();
        List<Weekday> weekdayNameList = Arrays.asList(Weekday.values());

        List<LessonTime> lessonTimeList = Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getAllObjects();
        List<String> lessonTimeNameList = new ArrayList<>();
        List<Double> lessonTimeStartList = new ArrayList<>();
        List<Double> lessonTimeEndList = new ArrayList<>();
        for (LessonTime Lists : lessonTimeList) {

            lessonTimeStartList.add(Double.parseDouble(Lists.getLessonTimeStart()));

        }
        for (LessonTime Lists : lessonTimeList) {

            lessonTimeEndList.add(Double.parseDouble(Lists.getLessonTimeEnd()));

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

//    private HashMap getFormParametrName(String modelColumnName, String groupsName){
//        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(modelColumnName, groupsName));
//
////        model.cast(Factory.getInstance().getGenericRepositoryInterface(model).getObject(modelColumnName,columnValue));
//
//        HashMap<String, Object> model = new HashMap<>();
//        List<Groups> groupsList = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
////        List<T> modelList = Factory.getInstance().getGenericRepositoryInterface(model).getAllObjects();
//        for(Groups groupsLists : groupsList ){
//
//            if(groupsLists.getGroupsId().equals(groups.getGroupsId())){
//            }
//            else {
//                groupsList.remove(groupsLists.getGroupsId());
//            }
//
//        }
//
//        model.put("formOfTrainingName", new String());
//        model.put("formOfTrainingNameArray", groupsList);
//        return  model;
//    }

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
            LessonTime lessonTime = new LessonTime(request.queryParams("timeStart"), request.queryParams("timeEnd"));
            response.redirect("/schedule");
            return Factory.getInstance().getGenericRepositoryInterface().addObject(lessonTime);

        });

        post("/api/addGroups", (request, response) -> {
            log.info("Starting /api/addGroups");
            String groupsName = request.queryParams(groupsColumn);
            try {
                Double.parseDouble(groupsName);
                Groups addGroups = new Groups(groupsName);
                response.redirect("/index");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(addGroups);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return "error_name";
            }
        });

        post("/api/deleteGroups", (request, response) -> {
            log.info("Starting /api/deleteGroups");
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn, request.queryParams("name")));
            response.redirect("/index");
            return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
        });

        post("/api/getSchedule", (request, response) -> {
            log.info("Starting /api/getSchedule");
            Gson gson = new GsonBuilder().create();
            String a = request.queryParams("name");
//            JsonParser parser = new JsonParser();
            Schedule schedule = new Schedule();
            try {
                schedule = gson.fromJson(a, Schedule.class);
            } catch (Exception e) {

            }


            return schedule;
        });

//        post("/find", (request, response) -> Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", request.queryParams("groupsName")));


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
//            UserController userController = new UserController(login);
//            userController.setHashPassword();
//            return userController.userLogin();
        });
        //        get("/add", (request, response) -> {
//            System.out.println("Выполняется /advice_form");
//            HashMap<String, Object> model = new HashMap<>();
////            Advice advice = new Advice(request.queryParams("text"), request.queryParams("category"));
////            AdviceController adviceController = new AdviceController(advice);
////            adviceController.addToDataBase();
////            model.put("id", advice.getUsersId());
////            model.put("category", advice.getCategory());
////            model.put("text", advice.getText());
////            model.put("rating", advice.getRating());
////            return new ModelAndView(model, "/public/templates/last_advice.vtl");
//            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
//        }, new VelocityTemplateEngine());

//        post("/addFormOfTraining",(request, response) -> {
//            FormOfTraining formOfTraining = new FormOfTraining(request.queryParams(formOfTrainingColumn));
//            Factory.getInstance().getGenericRepositoryInterface().addObject(formOfTraining);
//        return new ModelAndView(formOfTrainingName(), "/public/index.html");
//        }, new VelocityTemplateEngine());
//
//        post("/deleteFormOfTraining",(request, response) -> {
//           FormOfTraining formOfTraining = FormOfTraining.class.cast(Factory.getInstance().getGenericRepositoryInterface(FormOfTraining.class).getObject(formOfTrainingColumn ,request.queryParams(formOfTrainingColumn)));
//            Factory.getInstance().getGenericRepositoryInterface().removeObject(formOfTraining);
//            return new ModelAndView(formOfTrainingName(), "/public/index.html");
//        }, new VelocityTemplateEngine());
        //        get("/advice_form", (request, response) -> {
//            System.out.println("Выполняется /advice_form");
//            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
//        }, new VelocityTemplateEngine());
    }
}
