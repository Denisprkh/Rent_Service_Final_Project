package by.prokhorenko.rentservice.service.user;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    User signUp(User user) throws ServiceException;
    User signIn(String email, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    Map<String,Boolean> defineUsersIncorrectData(User user) throws ServiceException;
    boolean banUser(int id) throws  ServiceException;
    boolean unBanUser(int id) throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByPhone(String phone) throws  ServiceException;
    boolean activateUser(int id) throws ServiceException;
}
