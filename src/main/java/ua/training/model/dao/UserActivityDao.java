package ua.training.model.dao;

import ua.training.model.entity.ActivityUsers;
import ua.training.model.entity.UserActivity;

import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;

public interface UserActivityDao {

    Optional<UserActivity> findById(int id);

    void createNewUserActivity(int id_user, int id_activity, String hours);

    List<ActivityUsers> getUsers();

    ResultSet getAllUsersOfActivity(int id);

    ResultSet getAllUsersOfActivity(String name);

    void updateUserActivity(int id_user, int id_activity, String hours);

    void deleteUserActivityById(int id_user, int id_activity);

    void deleteUserActivityByName(String login, String name);

    ResultSet getAllActivitiesOfUser(String login);
}
