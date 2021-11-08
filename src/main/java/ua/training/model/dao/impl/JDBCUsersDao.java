
package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Users;
import ua.training.service.SQLColumns;

import java.sql.*;
import java.util.Optional;
import ua.training.service.SQLCommands;

public class JDBCUsersDao implements UserDao {
    static {
        new JDBCDaoFactory().executeUpdate(SQLCommands.SQL_CREATE_USER);
    }
    private Connection connection;

    public JDBCUsersDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Users> findByLogin(String name) {
        Optional<Users> result = Optional.empty();
        try {
            PreparedStatement ps = connection.prepareCall("SELECT * FROM Users WHERE " + SQLColumns.LOGIN_OF_USER + " = ?");
            ps.setString( 1, name);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                result = Optional.of(new UserMapper().extractFromResultSet(set));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Users> findUserById(int id) {
        Optional<Users> optionalUser = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM Users WHERE " + SQLColumns.ID_OF_USER.toString() + " = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                optionalUser = Optional.of(new UserMapper().extractFromResultSet(resultSet));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public int countOfUsers() {
        return new JDBCDaoFactory().executeScalar(SQLCommands.SQL_COUNT_OF_USER);
    }

    @Override
    public void deleteUserByLogin(String login) {
        try(PreparedStatement ps = connection.prepareCall("DELETE FROM Users WHERE " + SQLColumns.LOGIN_OF_USER.toString() + " = ?")){
            ps.setString( 1, login);
            ps.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String createNewUser(String login, String password) {
        try {
            connection.setAutoCommit(false);
            int numberOfUsers = countOfUsers();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (login_of_user, password_of_user, role_of_user) Values (?, ?, ?) RETURNING " + SQLColumns.ROLE_OF_USER.toString());
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, (numberOfUsers == 0 ? "ADMIN" : "USER"));
            ResultSet result = preparedStatement.executeQuery();
            connection.commit();
            result.next();
            return result.getString(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return "UNKNOWN";
    }
}