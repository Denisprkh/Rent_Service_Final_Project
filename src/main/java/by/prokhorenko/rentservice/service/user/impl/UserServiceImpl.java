package by.prokhorenko.rentservice.service.user.impl;

import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessageKey;
import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.DaoFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import by.prokhorenko.rentservice.util.MailSender;
import by.prokhorenko.rentservice.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger();
    private static final UserService INSTANCE = new UserServiceImpl();
    private static final String ACTIVATION_LINK = "http://localhost:8080/RentSetviceProkhorenkoDM_war/controller?" +
            "command=CONFIRM_REGISTRATION&id=";
    private static final String ACTIVATION_MESSAGE_TITLE = "Confirmation of registration";
    public static UserService getInstance(){
        return INSTANCE;
    }

    private UserServiceImpl(){}

    @Override
    public User signUp(User user) throws ServiceException {
       UserDao userDao = DaoFactory.getInstance().getUserDao();
       MailSender mailSender = new MailSender();
       try{
            User signedUpUser = userDao.add(user).orElseThrow(ServiceException::new);
            mailSender.send(ACTIVATION_MESSAGE_TITLE,ACTIVATION_LINK + user.getId(),user.getEmail());
            return signedUpUser;
       } catch (DaoException e) {
           LOG.debug(e);
           throw new ServiceException("Signing user up error" + e.getMessage(),e);
       }
    }

    public Map<String,Boolean> defineUsersIncorrectData(User user) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        String emailIsUnique = "emailIsUnique";
        String phoneIsUnique = "phoneIsUnique";
        Map<String, Boolean> validatedUsersData = userValidator.validateDataForSignUp(user);
        validatedUsersData.put(emailIsUnique,!findUserByEmail(user.getEmail()).isPresent());
        validatedUsersData.put(phoneIsUnique,!findUserByPhone(user.getPhone()).isPresent());
        return validatedUsersData;
    }



    @Override
    public User signIn(String email, String password) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        DaoFactory daoFactory = DaoFactory.getInstance();
        if(!userValidator.emailIsCorrect(email) || !userValidator.passwordIsCorrect(password)){
            throw new ServiceException(ResourceBundleErrorMessageKey.INVALID_INPUT_VALUES);
        }
        try(UserDao userDao = daoFactory.getUserDao()) {
           Optional<User> user = userDao.findByEmailAndPassword(email,password);
           if(!user.isPresent()){
               throw new ServiceException(ResourceBundleErrorMessageKey.EMAIL_OR_PASSWORD_IS_INCORRECT_ERROR_MESSAGE);
           }
           User signedInUser = user.get();
            LOG.debug(signedInUser.isBanned());
           if(signedInUser.isBanned()){
               throw new ServiceException(ResourceBundleErrorMessageKey.ACCOUNT_IS_BLOCKED);
           }
           if(!signedInUser.isActivated()){
               throw new ServiceException(ResourceBundleErrorMessageKey.ACCOUNT_IS_NOT_ACTIVATED);
           }
           return signedInUser;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Signing user in error",e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> allUsers = null;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
//            allUsers = userDao.findAll();
            return allUsers;
        } catch ( IOException e) {
            throw new ServiceException("Finding all users error",e);
        }
    }

    @Override
    public boolean banUser(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            boolean banned = userDao.banUser(id);
            return banned;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Banning user error",e);
        }
    }

    @Override
    public boolean unBanUser(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            boolean unbanned = userDao.unBanUser(id);
            return unbanned;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Unbanning user error",e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findById(id);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by id error",e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findByEmail(email);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by email error",e);
        }
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()) {
            return userDao.findByPhone(phone);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by phone error",e);
        }
    }

    @Override
    public boolean activateUser(int id) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao userDao = daoFactory.getUserDao()){
            return userDao.activateUser(id);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Activating user error",e);
        }
    }

}
