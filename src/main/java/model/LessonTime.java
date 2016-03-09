package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LessonTime")
public class LessonTime {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long lessonTimeId;


    @Column(name = "lessonTimeStart", nullable = false)
    private String lessonTimeStart;

    @Column(name = "lessonTimeEnd", nullable = false)
    private String lessonTimeEnd;

    @OneToMany(mappedBy = "lessonTime",orphanRemoval=true, cascade = {CascadeType.ALL})
    private Set<Schedule> schedule;

    public LessonTime() {
    }

    public Long getLessonTimeId() {
        return lessonTimeId;
    }

    public void setLessonTimeId(Long lessonTimeId) {
        this.lessonTimeId = lessonTimeId;
    }

    public String getLessonTimeStart() {
        return lessonTimeStart;
    }

    public void setLessonTimeStart(String lessonTimeStart) {
        this.lessonTimeStart = lessonTimeStart;
    }

    public String getLessonTimeEnd() {
        return lessonTimeEnd;
    }

    public void setLessonTimeEnd(String lessonTimeEnd) {
        this.lessonTimeEnd = lessonTimeEnd;
    }

    public Set<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }
}
