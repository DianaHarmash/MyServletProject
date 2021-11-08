package ua.training.model.entity;

import java.sql.Array;
import java.util.List;

public class UserRequest {
    private int id;
    private String login;
    private String activity;
    private String type;

    public UserRequest(int id, String login, String activity, String type) {
        this.id = id;
        this.login = login;
        this.activity = activity;
        this.type = type;
    }

    public UserRequest() {}

    public long getId() {
        return this.id;
    }
    public String getLogin() {
        return this.login;
    }
    public String getActivity() {
        return this.activity;
    }
    public String getType() { return this.type; }

    public void setId(int id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setType(String type) {
        this.type = type;
    }

}
