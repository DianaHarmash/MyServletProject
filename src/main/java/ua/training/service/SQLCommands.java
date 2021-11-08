package ua.training.service;

import org.postgresql.Driver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public interface SQLCommands {
    String SQL_CREATE_USER = "CREATE TABLE IF NOT EXISTS Users (" +
            "id_of_user SERIAL PRIMARY KEY, login_of_user VARCHAR(86) UNIQUE NOT NULL, password_of_user VARCHAR(86), role_of_user VARCHAR(8));";

    String SQL_CREATE_ACTIVITY = "CREATE TABLE if not exists Activities (" +
            "id_of_activity SERIAL NOT NULL, name_of_activity varchar(32) NOT NULL UNIQUE, category_of_activity varchar(32) NOT NULL);";

    String SQL_CREATE_USER_ACTIVITIES = "CREATE TABLE if not exists User_activities (" +
            "id_of_user_activity SERIAL NOT NULL, id_user INT8 NOT NULL, id_activity INT8 NOT NULL, hours VARCHAR(8) NOT NULL);";


    String SQL_CREATE_REQUEST = "CREATE TABLE if not exists Requests (" +
            "id_of_request SERIAL NOT NULL, id_user INT8 NOT NULL, id_activity INT8 NOT NULL, hours VARCHAR(16), type VARCHAR(8) NOT NULL);";

    String SQL_COUNT_OF_USER = "SELECT COUNT(*) FROM Users;";
    String SQL_COUNT_OF_ACTIVITIES = "SELECT COUNT(*) FROM Activities;";
    String SQL_GET_ALL_ACTIVITIES = "SELECT * FROM Activities;";
    String SQL_GET_ALL_REQUEST = "SELECT * FROM Requests;";
    String SQL_GET_USER_ACTIVITIES = "SELECT * FROM User_activities;";

    String GET_USERS_AND_ACTIVITIES = "SELECT * FROM Users u " +
            "JOIN User_activities ua ON u.id_of_user = ua.id_user " +
            "JOIN Activities a ON a.id_of_activity = ua.id_activity ";
}
