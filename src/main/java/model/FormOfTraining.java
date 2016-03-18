package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FormOfTraining")
public class FormOfTraining {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long formOfTrainingId;


    @Column(name = "formOfTrainingName", nullable = false)
    private String formOfTrainingName;

//    @OneToMany(mappedBy = "formOfTraining",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Course> course;

    public FormOfTraining() {
    }

    public FormOfTraining(String formOfTrainingName) {
        this.formOfTrainingName = formOfTrainingName;
    }

    public Long getFormOfTrainingId() {
        return formOfTrainingId;
    }

    public void setFormOfTrainingId(Long formOfTrainingId) {
        this.formOfTrainingId = formOfTrainingId;
    }

    public String getFormOfTrainingName() {
        return formOfTrainingName;
    }

    public void setFormOfTrainingName(String formOfTrainingName) {
        this.formOfTrainingName = formOfTrainingName;
    }

//    public Set<Course> getCourse() {
//        return course;
//    }
//
//    public void setCourse(Set<Course> course) {
//        this.course = course;
//    }
}
