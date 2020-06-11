package by.prokhorenko.rentservice.service.user;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User signUp(User user) throws ServiceException;
    User signIn(String email, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    boolean banUser(int id) throws  ServiceException;
    boolean unBanUser(int id) throws ServiceException;
    User findUserById(int id) throws ServiceException;
    User findUserByEmail(String email) throws ServiceException;
    User findUserByPhone(String phone) throws  ServiceException;
}
