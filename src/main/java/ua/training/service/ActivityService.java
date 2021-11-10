package ua.training.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCActivityDao;
import ua.training.model.dao.mapper.ActivitiesMapper;
import ua.training.model.entity.Activities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ActivityService.class));

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveActivity(String name, String category) {
        daoFactory.createActivityDao().createNewActivity(name, category);
    }

    public Activities findActivity(String name) {
       return daoFactory.createActivityDao().findActivityByName(name).orElseThrow(NoSuchElementException::new);
    }

    public Activities findActivity(int id) {
        return daoFactory.createActivityDao().findActivityById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Activities> getAllActivities() {
        List<Activities> activities= new ArrayList<Activities>();
        ResultSet set = daoFactory.createActivityDao().getAllActivities();
        try {
            while (set.next()) {
                activities.add(new ActivitiesMapper().extractFromResultSet(set));
            }
        }catch (Exception e){
            logger.log(Level.WARNING, e.getLocalizedMessage());
        }
        return activities;
    }

    public void updateAnActivity(int id, String name, String category) {
        daoFactory.createActivityDao().updateAnActivity(id, name, category);
    }

    public void deleteAnActivity(int id) {
        daoFactory.createActivityDao().deleteActivityByName(findActivity(id).getActivity());
    }

    public void deleteAnActivity(String name) {
        daoFactory.createActivityDao().deleteActivityByName(name);
    }

    public List<Activities> getRecordsSortedByName(int offset, int limit) {
        return daoFactory.createActivityDao().getRecordsSortByName(offset, limit);
    }

    public List<Activities> getRecordsSortByCategory(int offset, int limit) {
        return daoFactory.createActivityDao().getRecordsSortByCategory(offset, limit);
    }

    public int quantityOfActivity() {
        return daoFactory.createActivityDao().countOfActivities();
    }

}
