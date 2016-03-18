package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.reflect.Type;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupsId", nullable = false)
    private Groups groups;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formOfTrainingId", nullable = false)
    private FormOfTraining formOfTraining;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplineId", nullable = false)
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employmentTypeId", nullable = false)
    private EmploymentType employmentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lectureHallId", nullable = false)
    private LectureHall lectureHall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lessonTimeId", nullable = false)
    private LessonTime lessonTime;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public Schedule() {
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


