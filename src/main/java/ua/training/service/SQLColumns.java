package ua.training.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum SQLColumns {
    ID_OF_USER("id_of_user"),
    LOGIN_OF_USER("login_of_user"),
    PASSWORD_OF_USER("password_of_user"),
    ROLE_OF_USER("role_of_user"),
    ACTIVITIES_OF_USER("activities_of_user"),

    ID_OF_ACTIVITY("id_of_activity"),
    NAME_OF_ACTIVITY("name_of_activity"),
    CATEGORY_OF_ACTIVITY("category_of_activity"),
    USERS_OF_ACTIVITY("users_of_activity"),

    ID_OF_USER_ACTIVITIES("id_of_user_activity"),
    ID_USER("id_user"),
    ID_ACTIVITY("id_activity"),
    HOURS("hours"),

    ID_OF_REQUEST("id_of_request"),
    TYPE("type");

    private String name;
    SQLColumns(String name) {this.name = name;}

    @Override
    public String toString() {
        return this.name;
    }
}
