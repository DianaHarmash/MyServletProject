package ua.training.model.dao;

import ua.training.model.entity.Users;

import java.util.Optional;

public interface UserDao {

    Optional<Users> findByLogin(String name);

    String createNewUser(String login, String password);

    Optional<Users> findUserById(int id);

    int countOfUsers();

    void deleteUserByLogin(String login);
}
