package model.json;

import java.util.ArrayList;

public class Schedule {

    private String groupName;

    private ArrayList<WeekDay> weekDay;

    public Schedule() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<WeekDay> getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(ArrayList<WeekDay> weekDay) {
        this.weekDay = weekDay;
    }
}
