package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Teacher",uniqueConstraints = @UniqueConstraint(columnNames = {"teacherName"}))
public class Teacher {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    long teacherId;

    @Column(name = "teacherName", nullable = false)
    private String teacherName;

//    @OneToMany(mappedBy = "teacher",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedules;

    public Teacher() {
    }

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

//    public Set<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }
}
