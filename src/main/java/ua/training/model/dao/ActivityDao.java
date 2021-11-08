package ua.training.model.dao;

import ua.training.model.entity.Activities;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.List;

public interface ActivityDao {

    Optional<Activities> findActivityByName(String name);

    void updateAnActivity(int id, String name, String category);

    void createNewActivity(String name, String category);

    ResultSet getAllActivities();

    List<Activities> getRecordsSortByName(int offset, int limit);

    List<Activities> getRecordsSortByCategory(int offset, int limit);

    Optional<Activities> findActivityById(int id);

    List<Activities> getRecords(int pageid1, int total1);

    int countOfActivities();

    void deleteActivityByName(String name);

}
