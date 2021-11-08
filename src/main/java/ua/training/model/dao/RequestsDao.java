package ua.training.model.dao;

import ua.training.model.entity.Requests;
import java.util.List;

import java.util.Optional;
import ua.training.model.entity.UserRequest;

public interface RequestsDao {

    Optional<Requests> findById(int id);

    void createNewRequest(int id_user, int id_activity, String type);

    void createNewRequest(int id_user, int id_activity, String hours, String type);

    public void allow(int id);

    List<UserRequest> getAllRequest();

    void deleteRequestById(int id);
}
