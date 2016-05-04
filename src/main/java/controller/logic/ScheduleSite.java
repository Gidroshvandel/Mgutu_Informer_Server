package controller.logic;

import dao.Factory;
import model.*;
import utils.Converter;

import java.util.*;

public class ScheduleSite {
    public static void setSchedule(model.json.Schedule scheduleJsonModel) {
        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", scheduleJsonModel.getGroupName()));

        for(int i = 0; i< scheduleJsonModel.getWeekDay().size();i++) {
            Weekday weekday = Weekday.valueOf(scheduleJsonModel.getWeekDay().get(i).getWeekDayName());
            for (int j = 0; j < scheduleJsonModel.getWeekDay().get(i).getLessonTime().size(); j++) {

                String discipline = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getDiscipline();
                String employmentType = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getEmploymentType();
                String lectureHall = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getLectureHall();
                String teacher = scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getScheduleElements().getTeacher();

                if(!discipline.equals("")&&!employmentType.equals("")&&!lectureHall.equals("")&&!teacher.equals("")){
                    model.Schedule schedule = new model.Schedule();
                    schedule.setGroups(groups);
                    schedule.setWeekday(weekday);
                    String lessonTimeStart = Converter.startToDouble(scheduleJsonModel.getWeekDay().get(i).getLessonTime().get(j).getTime()).toString();
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



    public static HashMap getSchedule() {
        HashMap<String, Object> model = new HashMap<>();
        List<Weekday> weekdayNameList = Arrays.asList(Weekday.values());

        List<String> lessonTimeNameList = lessonTimeTransform(Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getAllObjects());

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
        return model;
    }

    private static List<String> lessonTimeTransform(List<LessonTime>  lessonTimeList){
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
        return lessonTimeNameList;
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
