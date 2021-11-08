package ua.training.model.entity;

import java.sql.Array;
import java.util.List;

public class Requests {
    private int id;
    private int idOfUser;
    private int idOfActivity;
    private String hours;
    private String type;

    public Requests(int id, int idOfUser, int idOfActivity, String hours, String type) {
        this.id = id;
        this.idOfUser = idOfUser;
        this.idOfActivity = idOfActivity;
        this.hours = hours;
        this.type = type;
    }

    public Requests() {}

    public int getId() { return this.id; }
    public int getIdOfUser() { return this.idOfUser; }
    public int getIdOfActivity() { return this.idOfActivity; }
    public String getHours() { return this.hours; }
    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }
    public void setId(int id) { this.id = id; }
    public void setIdOfUser(int idOfUser) { this.idOfUser = idOfUser; }
    public void setHours(String hours) { this.hours = hours; }
    public void setIdOfActivity(int idOfActivity) { this.idOfActivity = idOfActivity; }
}
