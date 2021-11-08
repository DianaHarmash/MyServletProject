package ua.training.model.entity;

import java.sql.Array;
import java.util.List;

public class Users {
    private long id;
    private String login;
    private String password;
    private String role;

    private List<Activities> activities;

    public Users(long id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Users() {}

    public long getId() {
        return this.id;
    }
    public String getLogin() {
        return this.login;
    }
    public String getPassword() {
        return this.password;
    }
    public String getRole() {
        return this.role;
    }
    public List<Activities> getActivities() {
        return this.activities;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setActivities(Array activities) {
        this.activities = (List<Activities>) activities;
    }

    public void setActivity(Activities activity) {
        this.activities.add(activity);
    }

}
