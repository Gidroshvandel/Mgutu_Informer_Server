package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Entity
//@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
@Table(name = "Groups",uniqueConstraints = @UniqueConstraint(columnNames = {"groupsName"}))
public class Groups{


    @Id
//    @GeneratedValue(generator = "user_seq")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long groupsId;


    @Column(name = "groupsName", nullable = false)
    private String groupsName;


//    @OneToMany(mappedBy = "groups",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Users> users;
//
//    @OneToMany(mappedBy = "groups",orphanRemoval=true, cascade = {CascadeType.ALL})
//    private Set<Schedule> schedule;

    public Groups() {
    }

    public Groups(String groupsName) {
        this.groupsId = getGroupsId();
        this.groupsName = groupsName;
    }

    public Long getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Long groupsId) {
        this.groupsId = groupsId;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

//    public Set<Users> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<Users> users) {
//        this.users = users;
//    }
}
