
package ua.training.model.dao.impl;

import ua.training.model.dao.RequestsDao;
import ua.training.service.SQLColumns;
import ua.training.service.SQLCommands;
import ua.training.model.entity.Requests;
import ua.training.model.entity.Types;
import ua.training.model.dao.mapper.RequestMapper;
import ua.training.service.UserActivityService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import ua.training.model.entity.UserRequest;
import java.util.List;
import java.util.ArrayList;
import ua.training.service.UserService;
import ua.training.service.ActivityService;

public class JDBCRequestsDao implements RequestsDao {
    static {
        new JDBCDaoFactory().executeUpdate(SQLCommands.SQL_CREATE_REQUEST);
    }
    private UserService userService = new UserService();
    private ActivityService activityService = new ActivityService();

    private Connection connection;

    public JDBCRequestsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Requests> findById(int id) {
        Optional<Requests> result = Optional.empty();
        try {
            PreparedStatement ps = connection.prepareCall("SELECT * FROM Requests WHERE " + SQLColumns.ID_OF_REQUEST + " = ?");
            ps.setInt( 1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                result = Optional.of(new RequestMapper().extractFromResultSet(set));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteRequestById(int id) {
        try(PreparedStatement ps = connection.prepareCall("DELETE FROM Requests WHERE " + SQLColumns.ID_OF_REQUEST.toString() + " = ?")){
            ps.setInt( 1, id);
            ps.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void allow(int id) {
        try {
            connection.setAutoCommit(false);
            Requests request = findById(id).orElseThrow(NoSuchElementException::new);
            if(request.getType().equals("ADD")) {
                new UserActivityService().createNewUserActivity(request.getIdOfUser(), request.getIdOfActivity(), request.getHours());
            } else if (request.getType().equals("EDIT")) {
                new UserActivityService().updateUserActivity(request.getIdOfUser(), request.getIdOfActivity(), request.getHours());
            } else {
                new UserActivityService().deleteUserActivityById(request.getIdOfUser(), request.getIdOfActivity());
            }
            deleteRequestById(id);
            connection.commit();
        } catch (Exception ex) {
            try {
                connection.rollback();
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public List<UserRequest> getAllRequest() {
        List<UserRequest> requests = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            ResultSet resultSet = new JDBCDaoFactory().executeQuery(SQLCommands.SQL_GET_ALL_REQUEST);
            while (resultSet.next()) {
                requests.add(new UserRequest(resultSet.getInt(SQLColumns.ID_OF_REQUEST.toString()), userService.findUserById(resultSet.getInt(SQLColumns.ID_USER.toString())).getLogin(), activityService.findActivity(resultSet.getInt(SQLColumns.ID_ACTIVITY.toString())).getActivity(), resultSet.getString(SQLColumns.TYPE.toString())));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requests;
    }

    private int findUserActivityByIdOfUser(int id_user, int id_activity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Requests WHERE id_user = ? AND id_activity = ?");
            preparedStatement.setInt(1, id_user);
            preparedStatement.setInt(2, id_activity);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public void createNewRequest(int id_user, int id_activity, String type) {
        try {
            connection.setAutoCommit(false);
            if (findUserActivityByIdOfUser(id_user, id_activity) < 1) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Requests (id_user, id_activity, type) Values (?, ?, ?);");
                preparedStatement.setInt(1, id_user);
                preparedStatement.setInt(2, id_activity);
                preparedStatement.setString(3, type);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void createNewRequest(int id_user, int id_activity, String hours, String type) {
        try {
            connection.setAutoCommit(false);
            if (findUserActivityByIdOfUser(id_user, id_activity) < 1) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Requests (id_user, id_activity, hours, type) Values (?, ?, ?, ?);");
                preparedStatement.setInt(1, id_user);
                preparedStatement.setInt(2, id_activity);
                preparedStatement.setString(3, hours);
                preparedStatement.setString(4, type);
                preparedStatement.executeUpdate();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}