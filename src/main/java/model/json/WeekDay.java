package model.json;

import java.util.ArrayList;

public class WeekDay {
    private String weekDayName;
    private ArrayList<LessonTime> lessonTime;

    public WeekDay() {
    }

    public String getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    public ArrayList<LessonTime> getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(ArrayList<LessonTime> lessonTime) {
        this.lessonTime = lessonTime;
    }
}
