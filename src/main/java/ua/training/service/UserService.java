package ua.training.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import ua.training.model.dao.DaoFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.training.model.dao.impl.JDBCActivityDao;
import ua.training.model.entity.Users;
import ua.training.model.entity.Activities;

public class UserService {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserService.class));

    DaoFactory daoFactory = DaoFactory.getInstance();

    public String saveUser(String login, String password) {
        return daoFactory.createUserDao().createNewUser(login, password);
    }

    public List<Activities> getRecords(int offset, int limit) {
        return daoFactory.createActivityDao().getRecords(offset, limit);
    }

    public Optional<Users> findUserByLogin(String login) {
        return daoFactory.createUserDao().findByLogin(login);
    }

    public Users findUserById(int id) {
        try {
            return daoFactory.createUserDao().findUserById(id).orElseThrow(NoSuchElementException::new);
        } catch(NoSuchElementException exception) {
            logger.log(Level.WARNING, exception.getLocalizedMessage());
        }
        return null;
    }

}
