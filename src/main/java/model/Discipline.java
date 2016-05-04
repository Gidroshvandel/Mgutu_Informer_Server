package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Discipline")
public class Discipline {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long disciplineId;


    @Column(name = "disciplineName", nullable = false)
    private String disciplineName;

//    @OneToMany(mappedBy = "discipline",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedules;

    public Discipline() {
    }

    public Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
