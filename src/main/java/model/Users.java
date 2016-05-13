package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
//@SequenceGenerator(name = "user_", sequenceName = "user_")
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class Users {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
//    @GeneratedValue(generator = "user_")
    private Long usersId;

    @Column(name = "secretKey", nullable = false, length = 36)
    private String secretKey;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupsId", nullable = true)
    private Groups groups;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "student", nullable = true)
    private Boolean student;

    public Users(String name, Groups groups, String login, String password, Boolean student) {
        this.name = name;
        this.groups = groups;
        this.login = login;
        this.password = password;
        this.student = student;
        this.secretKey = UUID.randomUUID().toString();
    }

    public Users() {
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.secretKey = UUID.randomUUID().toString();
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setId(Long id) {
        this.usersId = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }
}
