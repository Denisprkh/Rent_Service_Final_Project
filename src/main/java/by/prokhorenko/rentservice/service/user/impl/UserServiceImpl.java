package by.prokhorenko.rentservice.service.user.impl;

import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.dao.impl.UserDaoImpl;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger();

    public UserServiceImpl(){

    }

    @Override
    public Optional<User> signUp(User user) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        if(!userValidator.userIsCorrectForSignUp(user)) {
            throw new ServiceException("Data for registration is not correct" + user);
        }
            try(UserDao userDao = UserDaoImpl.getInstance()) {
                    return userDao.add(user);
            } catch (DaoException e) {
                throw new ServiceException("Signing up user error",e);
            } catch (Exception e) {
                throw new ServiceException("Signing up user error",e);
            }
    }

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        DaoFactory daoFactory = DaoFactory.getInstance();
        if(!userValidator.emailIsCorrect(email) || !userValidator.passwordIsCorrect(password)){
            throw new ServiceException("Email or password is incorrect");
        }
        try(UserDao userDao = daoFactory.getUserDao()) {
            return (userDao.findByEmailAndPassword(email,password));
        } catch (DaoException e) {
            throw new ServiceException("Signing user in error",e);
        } catch (Exception e) {
            throw new ServiceException("Signing user in error",e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> allUsers = null;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            allUsers = userDao.findAll();
            return allUsers;
        } catch (DaoException e) {
            throw new ServiceException("Finding all users error",e);
        } catch (Exception e) {
            throw new ServiceException("Finding all users error",e);
        }
    }

    @Override
    public boolean banUser(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            boolean banned = userDao.ban(id);
            return banned;
        } catch (DaoException e) {
            throw new ServiceException("Banning user error",e);
        } catch (Exception e) {
            throw new ServiceException("Banning user error",e);
        }
    }

    @Override
    public boolean unBanUser(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            boolean unbanned = userDao.unBan(id);
            return unbanned;
        } catch (DaoException e) {
            throw new ServiceException("Unbanning user error",e);
        } catch (Exception e) {
            throw new ServiceException("Unbanning user error",e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Finding user by id error",e);
        } catch (Exception e) {
            throw new ServiceException("Finding user by id error",e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Finding user by email error",e);
        } catch (Exception e) {
            throw new ServiceException("Finding user by email error",e);
        }
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findByPhone(phone);
        } catch (DaoException e) {
            throw new ServiceException("Finding user by phone error",e);
        } catch (Exception e) {
            throw new ServiceException("Finding user by phone error",e);
        }
    }
}
