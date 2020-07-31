package by.prokhorenko.rentservice.service.user;

import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    User signUp(User user) throws ServiceException;
    User signIn(String email, String password) throws ServiceException;
    List<User> findAllUsers(int start,int total) throws ServiceException;
    Map<String,Boolean> defineUsersIncorrectData(String email, String firstName, String lastName, String password,
                                                 String phoneNumber) throws ServiceException;
    boolean banUser(int id) throws  ServiceException;
    boolean unBanUser(int id) throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByPhone(String phone) throws  ServiceException;
    boolean activateUser(int id) throws ServiceException;
    User updateUserInfo(User user) throws ServiceException;
    Map<String,Boolean> defineUsersIncorrectDataForUpdate(String email, String firstName, String lastName,
                                                          String phoneNumber, int usersId) throws ServiceException;
    boolean giveAdminRightsById(int usersId) throws ServiceException;
    boolean pickUpAdminRightsById(int usersId) throws ServiceException;
}
