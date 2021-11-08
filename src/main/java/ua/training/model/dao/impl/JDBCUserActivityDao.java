
package ua.training.model.dao.impl;

import ua.training.service.SQLColumns;
import ua.training.service.SQLCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import ua.training.model.dao.UserActivityDao;
import ua.training.model.dao.mapper.UserActivityMapper;
import ua.training.model.entity.UserActivity;
import ua.training.model.entity.ActivityUsers;
import java.util.ArrayList;

public class JDBCUserActivityDao implements UserActivityDao {
    static {
        new JDBCDaoFactory().executeUpdate(SQLCommands.SQL_CREATE_USER_ACTIVITIES);
    }

    private Connection connection;

    public JDBCUserActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<UserActivity> findById(int id) {
        Optional<UserActivity> result = Optional.empty();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM User_activities WHERE " + SQLColumns.ID_OF_USER_ACTIVITIES.toString() + " = ?");
            ps.setInt( 1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                result = Optional.of(new UserActivityMapper().extractFromResultSet(set));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteUserActivityById(int id_user, int id_activity) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM User_activities WHERE " + SQLColumns.ID_USER.toString() + " = ? AND " + SQLColumns.ID_ACTIVITY + " = ?;" )){
            ps.setInt( 1, id_user);
            ps.setInt(2, id_activity);
            ps.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUserActivityByName(String login, String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User_activities WHERE " + SQLColumns.LOGIN_OF_USER + " = ? AND " + SQLColumns.NAME_OF_ACTIVITY + " = ?;")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResultSet getAllActivitiesOfUser(String login) {
        ResultSet result;
        try {
            PreparedStatement ps = connection.prepareStatement(SQLCommands.GET_USERS_AND_ACTIVITIES + " WHERE u.login_of_user = ?;");
            ps.setString( 1, login);
            result = ps.executeQuery();
            return result;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<ActivityUsers> getUsers() {
        List<ActivityUsers> usersOfActivities = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQLCommands.SQL_GET_ALL_ACTIVITIES);
            ResultSet result = ps.executeQuery();
            try {
                while (result.next()) {
                    ResultSet allUsersOfAnActivity = getAllUsersOfActivity(result.getInt(SQLColumns.ID_OF_ACTIVITY.toString()));
                    List<String> logins = new ArrayList<>();
                    ActivityUsers activityUsers = new ActivityUsers(result.getInt(SQLColumns.ID_OF_ACTIVITY.toString()), result.getString(SQLColumns.NAME_OF_ACTIVITY.toString()));
                    while (allUsersOfAnActivity.next()) {
                        logins.add(allUsersOfAnActivity.getString(SQLColumns.LOGIN_OF_USER.toString()));
                    }
                    activityUsers.setUsers(logins);
                    usersOfActivities.add(activityUsers);
                }
            } catch(SQLException ex) {
                try {
                    connection.rollback();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
                ex.printStackTrace();
            }
            connection.commit();
        } catch(Exception ex) {
            try {
                connection.rollback();
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            ex.printStackTrace();
        }
        return usersOfActivities;
    }

    @Override
    public ResultSet getAllUsersOfActivity(int id) {
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareCall(SQLCommands.GET_USERS_AND_ACTIVITIES + " WHERE a.id_of_activity = ?;");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet getAllUsersOfActivity(String name) {
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareCall(SQLCommands.GET_USERS_AND_ACTIVITIES + " WHERE a.name_of_activity = ?;");
            preparedStatement.setString(1, name);
            result = preparedStatement.executeQuery();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateUserActivity(int id_user, int id_activity, String hours) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User_Activities SET " + SQLColumns.HOURS.toString() + " = ? WHERE " + SQLColumns.ID_USER + " = ? AND " + SQLColumns.ID_ACTIVITY + " = ?;")){
            preparedStatement.setString(1, hours);
            preparedStatement.setInt(2, id_user);
            preparedStatement.setInt(3, id_activity);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void createNewUserActivity(int id_user, int id_activity, String hours) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User_activities (id_user, id_activity, hours) Values (?, ?, ?);")) {
            preparedStatement.setInt(1, id_user);
            preparedStatement.setInt(2, id_activity);
            preparedStatement.setString(3, hours);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}