package pl.majorczyk.mybookstore.service;

import pl.majorczyk.mybookstore.dao.FactoryDAO;
import pl.majorczyk.mybookstore.dao.UserDAO;
import pl.majorczyk.mybookstore.model.User;

import java.util.List;

public class UserService {

    public void addUser(String firstname,String lastname,String password,String email,String street,String city){
        User user=new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setEmail(email);
        user.setStreet(street);
        user.setCity(city);

        UserDAO dao=getUserDAO();

        dao.create(user);
    }
    public User getUserByEmail(String email){
        User user=null;
        UserDAO dao=getUserDAO();
        user=dao.readUserByEmail(email);

        return user;
    }
    public List<User> getAllUsers(){
        UserDAO dao=getUserDAO();
        List<User> usersList=dao.readAllUsers();
        return usersList;
    }
    private UserDAO getUserDAO(){
        FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
        UserDAO dao=factory.getUserDAO();
        return dao;
    }
}
