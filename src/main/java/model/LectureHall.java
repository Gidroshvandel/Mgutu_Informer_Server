package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LectureHall")
public class LectureHall {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long lectureHallId;


    @Column(name = "lectureHallName", nullable = false)
    private String lectureHallName;

//    @OneToMany(mappedBy = "lectureHall",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedules;

    public LectureHall() {
    }

    public LectureHall(String lectureHallName) {
        this.lectureHallName = lectureHallName;
    }

    public Long getLectureHallId() {
        return lectureHallId;
    }

    public void setLectureHallId(Long lectureHallId) {
        this.lectureHallId = lectureHallId;
    }

    public String getLectureHallName() {
        return lectureHallName;
    }

    public void setLectureHallName(String lectureHallName) {
        this.lectureHallName = lectureHallName;
    }

//    public Set<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }
}
