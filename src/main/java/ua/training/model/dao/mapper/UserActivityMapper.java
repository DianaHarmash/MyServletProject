package ua.training.model.dao.mapper;

import ua.training.service.SQLColumns;

import java.sql.ResultSet;
import java.sql.SQLException;
import ua.training.model.entity.UserActivity;

public class UserActivityMapper implements ObjectMapper<UserActivity> {

    @Override
    public UserActivity extractFromResultSet(ResultSet set) throws SQLException {
        UserActivity userActivity = new UserActivity();
        userActivity.setId(set.getInt(SQLColumns.ID_OF_USER_ACTIVITIES.toString()));
        userActivity.setIdOfUser(set.getInt(SQLColumns.ID_USER.toString()));
        userActivity.setIdOfActivity(set.getInt(SQLColumns.ID_ACTIVITY.toString()));
        userActivity.setHours(set.getString(SQLColumns.HOURS.toString()));
        return userActivity;
    }
}
