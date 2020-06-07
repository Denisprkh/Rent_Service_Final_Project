package by.prokhorenko.rentservice.service.user.impl;

import by.prokhorenko.rentservice.dao.user.UserDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger();
    private final UserDao userDao;
    private final UserValidator userValidator;

    public UserServiceImpl(){
        userDao = DaoFactory.getInstance().getUserDao();
        userValidator = UserValidator.getInstance();
    }

    @Override
    public User signUp(User user) throws ServiceException {
        if(!userValidator.userIsCorrectForSignUp(user)) {
            throw new ServiceException("Data for registration is not correct");
        }
            try {
                User signedUpUser = null;
                if(userDao.add(user)){
                    signedUpUser = findUserByEmail(user.getEmail());
                }
                return signedUpUser;
            } catch (DaoException e) {
                throw new ServiceException("Signing up user error",e);
            }finally {
                userDao.closeConnection();
            }
    }

    @Override
    public User signIn(String email, String password) throws ServiceException {
        if(!userValidator.emailIsCorrect(email) || !userValidator.passwordIsCorrect(password)){
            throw new ServiceException("Email or password is incorrect");
        }
        List<User> users = new ArrayList<>();
        try {
            users.add(userDao.findByEmailAndPassword(email,password));
            if(!users.isEmpty()){
                return users.get(0);
            }
        } catch (DaoException e) {
            throw new ServiceException("Signing user in error",e);
        }
        finally {
            userDao.closeConnection();
        }
        throw new ServiceException("User with such login and password has not been found");
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        return null;
    }

    @Override
    public boolean banUser(int id) throws ServiceException {
        return false;
    }

    @Override
    public boolean unBanUser(int id) throws ServiceException {
        return false;
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Finding user by email error",e);
        }finally {
            userDao.closeConnection();
        }
    }
}
