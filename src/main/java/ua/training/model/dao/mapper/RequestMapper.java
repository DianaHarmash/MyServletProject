package ua.training.model.dao.mapper;

import ua.training.service.SQLColumns;

import java.sql.ResultSet;
import java.sql.SQLException;
import ua.training.model.entity.Requests;

public class RequestMapper implements ObjectMapper<Requests> {

    @Override
    public Requests extractFromResultSet(ResultSet set) throws SQLException {
        Requests request = new Requests();
        request.setId(set.getInt(SQLColumns.ID_OF_REQUEST.toString()));
        request.setIdOfUser(set.getInt(SQLColumns.ID_USER.toString()));
        request.setIdOfActivity(set.getInt(SQLColumns.ID_ACTIVITY.toString()));
        request.setHours(set.getString(SQLColumns.HOURS.toString()));
        request.setType(set.getString(SQLColumns.TYPE.toString()));
        return request;
    }
}
