package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolHolder {
    private static volatile Connection _connection;
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource() {
                        @Override
                        public synchronized Connection getConnection() throws SQLException {
                            if ((_connection!=null) && (_connection.isClosed())){
                                _connection = null;
                            }
                            if (_connection == null) {
                                _connection = super.getConnection();
                            }
                            return _connection;
                        }
                    };
                    ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
                    ds.setUsername("postgres");
                    ds.setPassword("qwerty1234");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}
