package ua.training.model.entity;

import java.util.List;
import java.util.ArrayList;

public class ActivityUsers {
    private int id;
    private String name;

    private List<String> usersList;

    public ActivityUsers(int id, String name) {
        this.id = id;
        this.name = name;
        this.usersList = new ArrayList();
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public void setUser(String login) {
        this.usersList.add(login);
    }
    public void setUsers(List<String> theUsers) {
        this.usersList = theUsers;
    }
    public List<String> getUsers() { return this.usersList; }

}