package ua.training.model.dao.mapper;

import ua.training.model.entity.Activities;
import ua.training.service.SQLColumns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ActivitiesMapper implements ObjectMapper<Activities> {

    @Override
    public Activities extractFromResultSet(ResultSet set) throws SQLException {
        Activities activity = new Activities();
        activity.setId(set.getInt(SQLColumns.ID_OF_ACTIVITY.toString()));
        activity.setActivity(set.getString(SQLColumns.NAME_OF_ACTIVITY.toString()));
        activity.setCategory(set.getString(SQLColumns.CATEGORY_OF_ACTIVITY.toString()));
        return activity;
    }
}
