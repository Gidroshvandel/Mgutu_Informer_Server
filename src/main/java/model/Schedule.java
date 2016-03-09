package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupsId", nullable = false)
    private Groups groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formOfTrainingId", nullable = false)
    private FormOfTraining formOfTraining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplineId", nullable = false)
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employmentTypeId", nullable = false)
    private EmploymentType employmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureHallId", nullable = false)
    private LectureHall lectureHall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonTimeId", nullable = false)
    private LessonTime lessonTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numberWeekdayId", nullable = false)
    private NumberWeekday numberWeekday;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    public Groups getGroups() {
        return groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Schedule(Groups groups, Teacher teacher) {
        this.groups = groups;
        this.teacher = teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}


