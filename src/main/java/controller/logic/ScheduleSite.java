package controller.logic;

import dao.Factory;
import model.*;
import model.site.Query;
import utils.Converter;

import java.util.*;

public class ScheduleSite {

    public static Weekday getWeekdayByName(String name){
        Weekday weekday = null;
        for (Weekday weekdayf : Weekday.values()){
            if(weekdayf.getName().equals(name)){
                weekday = weekdayf;
            }
        }
        return weekday;
    }

    public static void setSchedule(model.json.Schedule scheduleJsonModel) {
        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", scheduleJsonModel.getGroupName()));

        for(int i = 0; i< scheduleJsonModel.getWeekDay().size();i++) {
            Weekday weekday = getWeekdayByName(scheduleJsonModel.getWeekDay().get(i).getWeekDayName());
            for (int j = 0; j < scheduleJsonModel.getWeekDay().get(i).getLessonTime().size(); j++) {

                String discipline = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getDiscipline();
                String employmentType = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getEmploymentType();
                String lectureHall = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getLectureHall();
                String teacher = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getTeacher();
                String numberWeekDay = scheduleJsonModel.getWeekDay().get(i).getNumberWeekDay();

                if(!discipline.equals("")&&!employmentType.equals("")&&!lectureHall.equals("")&&!teacher.equals("")){
                    model.Schedule schedule = new model.Schedule();
                    schedule.setGroups(groups);
                    schedule.setWeekday(weekday);
                    String lessonTimeStart = Converter.startToDouble(scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getTime()).toString();
                    LessonTime lessonTime = LessonTime.class.cast(Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getObject("lessonTimeStart",lessonTimeStart));
                    schedule.setLessonTime(lessonTime);

                    schedule.setNumberWeekday(convertNumberWeekDay(numberWeekDay));

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
                    map.put("groups",schedule.getGroups().getGroupsId());
                    map.put("weekday",schedule.getWeekday());
                    map.put("numberWeekday",schedule.getNumberWeekday());
                    map.put("lessonTime",schedule.getLessonTime().getLessonTimeId());

                    Schedule scheduleOld = (Schedule)(Factory.getInstance().getGenericRepositoryInterface(model.Schedule.class).getObject(map));

                    if(scheduleOld == null){
                        Factory.getInstance().getGenericRepositoryInterface().addObject(schedule);
                    }
                    else{
                        schedule.setScheduleId(scheduleOld.getScheduleId());
                        Factory.getInstance().getGenericRepositoryInterface(Schedule.class).editObject(schedule);
                    }

                }
            }
        }
    }


    public static NumberWeekday convertNumberWeekDay(String numberWeekDay){
        if(numberWeekDay!=null&&numberWeekDay.equals("2"))
        {
            return NumberWeekday.second;
        }
        else {
            return NumberWeekday.first;
        }
    }

    public static HashMap getSchedule(String groupsName, String numberWeekday) {
        HashMap<String, Object> model = new HashMap<>();
        List<Weekday> weekdayNameList = Arrays.asList(Weekday.values());

        List<String> lessonTimeNameList = LessonTimeController.lessonTimeTransform(Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getAllObjects());

        List<LectureHall> lectureHallList = Factory.getInstance().getGenericRepositoryInterface(LectureHall.class).getAllObjects();
        List<Teacher> teacherList = Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getAllObjects();
        List<Discipline> disciplineList = Factory.getInstance().getGenericRepositoryInterface(Discipline.class).getAllObjects();
        List<EmploymentType> employmentTypeList = Factory.getInstance().getGenericRepositoryInterface(EmploymentType.class).getAllObjects();
        model.put("lectureHall", new String());
        model.put("lectureHallList", lectureHallList);
        model.put("teacher", new String());
        model.put("teacherList", teacherList);
        model.put("discipline", new String());
        model.put("disciplineList", disciplineList);
        model.put("employmentType", new String());
        model.put("employmentTypeList", employmentTypeList);
        model.put("lessonTime", new String());
        model.put("lessonTimeArray", lessonTimeNameList);
        model.put("weekDayName", new String());
        model.put("weekDayArray", weekdayNameList);
        model.put("model", new String());
        model.put("modelList",setQuery(groupsName,numberWeekday));
        return model;
    }

    private static List<Query> setQuery(String groupsName, String numberWeekDay){


        Map<String,Object> map = new HashMap<>();
        map.put("groups",Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName",groupsName));
        map.put("numberWeekday",convertNumberWeekDay(numberWeekDay));
        List<Schedule> scheduleList = Factory.getInstance().getGenericRepositoryInterface(Schedule.class).getObjects(map);
        List<Query> queryList = new ArrayList<>();
        for (Schedule scheduleList1: scheduleList){
            queryList.add(new Query(scheduleList1.getWeekday().getName(),
                    Converter.toString(scheduleList1.getLessonTime().getLessonTimeStart(),scheduleList1.getLessonTime().getLessonTimeEnd()),
                    scheduleList1.getLectureHall().getLectureHallName(),
                    scheduleList1.getTeacher().getTeacherName(),
                    scheduleList1.getDiscipline().getDisciplineName(),
                    scheduleList1.getEmploymentType().getEmploymentTypeName()));
        }

        return queryList;


    }

    private static  <T> T setScheduleElement(String columnName, String columnValue, Object o, Class<T> tClass){
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
}
