package ua.training.model.dao.mapper;

import ua.training.model.entity.Users;
import ua.training.service.SQLColumns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<Users> {

    @Override
    public Users extractFromResultSet(ResultSet set) throws SQLException {
        Users user = new Users();
        user.setId(set.getLong(SQLColumns.ID_OF_USER.toString()));
        user.setLogin(set.getString(SQLColumns.LOGIN_OF_USER.toString()));
        user.setPassword(set.getString(SQLColumns.PASSWORD_OF_USER.toString()));
        user.setRole(set.getString(SQLColumns.ROLE_OF_USER.toString()));
        return user;
    }
}
