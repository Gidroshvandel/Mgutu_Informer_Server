package model.json;

public class LessonTime {
    private String time;
    private ScheduleElements scheduleElements;

    public LessonTime() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ScheduleElements getScheduleElements() {
        return scheduleElements;
    }

    public void setScheduleElements(ScheduleElements scheduleElements) {
        this.scheduleElements = scheduleElements;
    }
}
