package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "NumberWeekday")
public class NumberWeekday {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long numberWeekdayId;


    @Column(name = "numberWeekdayName", nullable = false)
    private String numberWeekdayName;

//    @OneToMany(mappedBy = "formOfTraining",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedule;

    public NumberWeekday() {
    }
}
