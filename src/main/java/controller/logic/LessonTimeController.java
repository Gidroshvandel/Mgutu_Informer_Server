package controller.logic;

import model.LessonTime;
import utils.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LessonTimeController {
    public static List<String> lessonTimeTransform(List<LessonTime>  lessonTimeList){
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
}
