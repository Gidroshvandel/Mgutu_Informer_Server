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

    //    @Column(name = "groupsId")
//    private int groupsId;

    //    int numberAudience;
//    @Column(name = "teacherId")
//    private int teacherId;

//    int disciplineId;

//    int weekdayid;

//    int numberWeekdayId;

//    int employmentTypeId;

    //    int lessonNumber;



    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}


