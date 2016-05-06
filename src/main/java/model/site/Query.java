package model.site;

public class Query {
    private String weekDay;
    private String lessonTime;
    private String lectureHall;
    private String teacher;
    private String discipline;
    private String employmentType;

    public Query(String weekDay, String lessonTime, String lectureHall, String teacher, String discipline, String employmentType) {
        this.weekDay = weekDay;
        this.lessonTime = lessonTime;
        this.lectureHall = lectureHall;
        this.teacher = teacher;
        this.discipline = discipline;
        this.employmentType = employmentType;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(String lectureHall) {
        this.lectureHall = lectureHall;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
