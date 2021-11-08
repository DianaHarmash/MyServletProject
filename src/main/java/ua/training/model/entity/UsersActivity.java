package ua.training.model.entity;

public class UsersActivity {
    private int id;
    private String name;
    private String category;
    private String hours;

    public UsersActivity(int id, String name, String category, String hours) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.hours = hours;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getCategory() {
        return this.category;
    }
    public String getHours() {
        return this.hours;
    }
}