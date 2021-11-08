package ua.training.service;

import ua.training.model.dao.DaoFactory;

import java.util.List;

import ua.training.model.entity.UserRequest;

public class RequestService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void addARequest(int idOfUser, int idOfActivity, String type) {
        daoFactory.createRequestsDao().createNewRequest(idOfUser, idOfActivity, type);
    }
    public void addRequest(int idOfUser, int idOfActivity, String hours, String type) {
        daoFactory.createRequestsDao().createNewRequest(idOfUser, idOfActivity, hours, type);
    }
    public List<UserRequest> getRequests() {
        return daoFactory.createRequestsDao().getAllRequest();
    }
    public void allow(int id) {
        daoFactory.createRequestsDao().allow(id);
    }
}
