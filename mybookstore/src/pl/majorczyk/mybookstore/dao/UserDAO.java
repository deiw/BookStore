package pl.majorczyk.mybookstore.dao;

import pl.majorczyk.mybookstore.model.User;

import java.util.List;

public interface UserDAO {
    User create(User user);
    List<User> readAllUsers();
    User readUserByEmail(String email);
}
