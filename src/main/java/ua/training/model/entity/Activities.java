package ua.training.model.entity;

import java.sql.Array;
import java.util.List;

public class Activities {
    private int id;
    private String activity;
    private String category;

    private List<Users> users;

    public Activities(int id, String activity, String category) {
        this.id = id;
        this.activity = activity;
        this.category = category;
    }

    public Activities() {}

    public int getId() {
        return this.id;
    }
    public String getActivity() {
        return this.activity;
    }
    public String getCategory() {
        return this.category;
    }
    public List<Users> getUsers() {
        return this.users;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setUsers(Array users) {
        this.users = (List<Users>) users;
    }

    public void setUser(Users user) {
        this.users.add(user);
    }
}
