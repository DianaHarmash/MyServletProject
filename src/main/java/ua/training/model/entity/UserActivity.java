package ua.training.model.entity;

import java.sql.Array;
import java.util.List;

public class UserActivity {
    private int id;
    private int idOfUser;
    private int idOfActivity;
    private String hours;

    public UserActivity(int id, int idOfUser, int idOfActivity, String hours) {
        this.id = id;
        this.idOfUser = idOfUser;
        this.idOfActivity = idOfActivity;
        this.hours = hours;
    }

    public UserActivity() {}

    public int getId() { return this.id; }
    public int getIdOfUser() { return this.idOfUser; }
    public int getIdOfActivity() { return this.idOfActivity; }
    public String getHours() { return this.hours; }

    public void setHours(String hours) { this.hours = hours; }
    public void setId(int id) { this.id = id; }
    public void setIdOfUser(int idOfUser) { this.idOfUser = idOfUser; }
    public void setIdOfActivity(int idOfActivity) { this.idOfActivity = idOfActivity; }
}
