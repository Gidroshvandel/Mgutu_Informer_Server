package model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "secretKey", nullable = false, length = 36)
    private String secretKey;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "groups", nullable = false)
    private String groups;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "student", nullable = true)
    private Boolean student;

    public Users(String name, String groups, String login, String password, Boolean student) {
        this.name = name;
        this.groups = groups;
        this.login = login;
        this.password = password;
        this.student = student;
        this.secretKey = UUID.randomUUID().toString();
    }
    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.secretKey = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
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
