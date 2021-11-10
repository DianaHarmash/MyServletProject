
package ua.training.service;

import ua.training.model.dao.UserActivityDao;
import ua.training.model.dao.impl.JDBCActivityDao;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dao.mapper.UserActivityMapper;
import ua.training.model.entity.UsersActivity;
import ua.training.model.entity.UserActivity;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.ActivityUsers;

public class UserActivityService {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserActivityService.class));

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<UserActivity> findById(int id) {
        return daoFactory.createUserActivityDao().findById(id);
    }

    public void deleteUserActivityById(int idOfUser, int idOfActivity) {
        daoFactory.createUserActivityDao().deleteUserActivityById(idOfUser, idOfActivity);
    }

    public void updateUserActivity(int idOfUser, int idOfActivity, String hours) {
        daoFactory.createUserActivityDao().updateUserActivity(idOfUser, idOfActivity, hours);
    }

    public void createNewUserActivity(int id_user, int id_activity, String hours) {
        daoFactory.createUserActivityDao().createNewUserActivity(id_user, id_activity, hours);
    }

    public List<UsersActivity> getAllActivitiesOfUser(String userLogin) {
        List<UsersActivity> myList = new ArrayList();
        ResultSet set = daoFactory.createUserActivityDao().getAllActivitiesOfUser(userLogin);
        try {
            while (set.next()) {
                myList.add(new UsersActivity(set.getInt(SQLColumns.ID_OF_USER_ACTIVITIES.toString()), set.getString(SQLColumns.NAME_OF_ACTIVITY.toString()), set.getString(SQLColumns.CATEGORY_OF_ACTIVITY.toString()), set.getString(SQLColumns.HOURS.toString())));
            }
        } catch(Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return myList;
    }

    public List<Integer> getAllUsersOfActivity(String name) {
        List<Integer> usersActivityList = new ArrayList<>();
        try {
            ResultSet set = daoFactory.createUserActivityDao().getAllUsersOfActivity(name);
            while(set.next()) {
                usersActivityList.add(set.getInt(SQLColumns.ID_OF_USER.toString()));
            }
        } catch(Exception ex) {
            logger.log(Level.WARNING, ex.getLocalizedMessage());
        }
        return usersActivityList;
    }

    public void deleteUserActivityByName(String login, String password) {
        daoFactory.createUserActivityDao().deleteUserActivityByName(login, password);
    }

    public List<ActivityUsers> getAllUsersOfActivity() {
        return daoFactory.createUserActivityDao().getUsers();
    }
}