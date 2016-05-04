package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EmploymentType")
public class EmploymentType {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long employmentTypeId;


    @Column(name = "employmentTypeName", nullable = false)
    private String employmentTypeName;

//    @OneToMany(mappedBy = "employmentType",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedules;

    public EmploymentType() {
    }

    public EmploymentType(String employmentTypeName) {
        this.employmentTypeName = employmentTypeName;
    }

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getEmploymentTypeName() {
        return employmentTypeName;
    }

    public void setEmploymentTypeName(String employmentTypeName) {
        this.employmentTypeName = employmentTypeName;
    }

//    public Set<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }
}
