package ua.training.model.dao.impl;

import com.sun.tools.sjavac.Log;
import ua.training.controller.Servlet;
import ua.training.model.dao.ActivityDao;
import ua.training.model.dao.mapper.ActivitiesMapper;
import ua.training.model.entity.Activities;
import ua.training.service.SQLColumns;
import ua.training.service.SQLCommands;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import ua.training.service.UserActivityService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCActivityDao implements ActivityDao {
    private static final Logger logger = Logger.getLogger(String.valueOf(JDBCActivityDao.class));
    static {
        new JDBCDaoFactory().executeUpdate(SQLCommands.SQL_CREATE_ACTIVITY);
    }

    private UserActivityService userActivityService = new UserActivityService();

    private Connection connection;

    public JDBCActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Activities> findActivityByName(String name) {
        Optional<Activities> result = Optional.empty();
        try {
            PreparedStatement ps = connection.prepareCall("SELECT * FROM Activities WHERE " + SQLColumns.NAME_OF_ACTIVITY.toString() + " = ?");
            ps.setString( 1, name);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                result = Optional.of(new ActivitiesMapper().extractFromResultSet(set));
            }
        }catch (Exception ex){
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public List<Activities> getRecords(int offset,int limit){
        List<Activities> list = new ArrayList<Activities>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Activities OFFSET "+offset+" LIMIT "+limit);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                list.add(new ActivitiesMapper().extractFromResultSet(result));
            }
        }catch(Exception ex) {
            logger.log(Level.WARNING, "Exception:", ex);
        }
            return list;
    }

    @Override
    public List<Activities> getRecordsSortByName(int offset, int limit) {
        List<Activities> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Activities ORDER BY " + SQLColumns.NAME_OF_ACTIVITY + " OFFSET ? LIMIT ?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                list.add(new ActivitiesMapper().extractFromResultSet(result));
            }
        } catch(Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<Activities> getRecordsSortByCategory(int offset, int limit) {
        List<Activities> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Activities ORDER BY " + SQLColumns.CATEGORY_OF_ACTIVITY + " OFFSET ? LIMIT ?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                list.add(new ActivitiesMapper().extractFromResultSet(result));
            }
        } catch(Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public Optional<Activities> findActivityById(int id) {
        Optional<Activities> result = Optional.empty();
        try {
            PreparedStatement ps = connection.prepareCall("SELECT * FROM Activities WHERE " + SQLColumns.ID_OF_ACTIVITY.toString() + " = ?");
            ps.setInt( 1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                result = Optional.of(new ActivitiesMapper().extractFromResultSet(set));
            }
        }catch (Exception ex){
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public int countOfActivities() {
        return new JDBCDaoFactory().executeScalar(SQLCommands.SQL_COUNT_OF_ACTIVITIES);
    }

    @Override
    public void updateAnActivity(int id, String name, String category) {
        try(PreparedStatement ps = connection.prepareCall("UPDATE Activities SET " + SQLColumns.NAME_OF_ACTIVITY.toString() + " = ?, " + SQLColumns.CATEGORY_OF_ACTIVITY.toString() + " = ? WHERE " + SQLColumns.ID_OF_ACTIVITY.toString() + " = ?;")){
            ps.setString( 1, name);
            ps.setString(2, category);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception ex){
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
    }

    @Override
    public void createNewActivity(String name, String category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Activities (name_of_activity, category_of_activity) Values (?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, exception.getLocalizedMessage());
        }
    }

    @Override
    public void deleteActivityByName(String name) {
        try {
            connection.setAutoCommit(false);
            List<Integer> listOfActivityUsers = userActivityService.getAllUsersOfActivity(name);
            for (Integer id : listOfActivityUsers) {
                userActivityService.deleteUserActivityById(id, findActivityByName(name).get().getId());
            }
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Activities WHERE " + SQLColumns.NAME_OF_ACTIVITY.toString() + " = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            logger.log(Level.WARNING, exception.getLocalizedMessage());
        }
    }

    @Override
    public ResultSet getAllActivities() {
        return new JDBCDaoFactory().executeQuery(SQLCommands.SQL_GET_ALL_ACTIVITIES);
    }
}
