package ua.training.model.dao.impl;

import org.postgresql.Driver;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ActivityDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.UserActivityDao;
import ua.training.model.dao.RequestsDao;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUsersDao(getConnection());
    }
    @Override
    public ActivityDao createActivityDao() {
        return new JDBCActivityDao(getConnection());
    }
    @Override
    public UserActivityDao createUserActivityDao() {
        return new JDBCUserActivityDao(getConnection());
    }
    @Override
    public RequestsDao createRequestsDao() {
        return new JDBCRequestsDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String SQLCommand) {
        try (Connection connection = getConnection();
             Statement statement = Objects.requireNonNull(connection).createStatement()) {
             statement.executeUpdate(SQLCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeScalar(String SQLCommand) {
        ResultSet result = this.executeQuery(SQLCommand);
        try{
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;
    }

    public ResultSet executeQuery(String SQLCommand) {
        ResultSet set;
        try {Connection connection = getConnection();
            Statement statement = Objects.requireNonNull(connection).createStatement();
            set = statement.executeQuery(SQLCommand);
            return set;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void displaySQL(ResultSet resultSet) {
        System.out.println(resultSet.toString());
    }
}
