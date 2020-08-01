package by.prokhorenko.rentservice.service.user.impl;

import by.prokhorenko.rentservice.controller.command.ResourceBundleErrorMessageKey;
import by.prokhorenko.rentservice.dao.UserDao;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;
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
    private static final String EMAIL_IS_UNIQUE = "emailIsUnique";
    private static final String PHONE_IS_UNIQUE = "phoneIsUnique";

    public static UserService getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {
    }

    @Override
    public User signUp(User user) throws ServiceException {
        MailSender mailSender = new MailSender();
        try(UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            User signedUpUser = userDao.add(user).orElseThrow(ServiceException::new);
            mailSender.send(ACTIVATION_MESSAGE_TITLE, ACTIVATION_LINK + user.getId(), user.getEmail());
            return signedUpUser;
        } catch (DaoException | IOException e) {
            LOG.debug(e);
            throw new ServiceException("Signing user up error" + e.getMessage(), e);
        }
    }

    public Map<String, Boolean> defineUsersIncorrectData(String email, String firstName, String lastName, String password,
                                                         String phoneNumber) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        Map<String, Boolean> validatedUsersData = userValidator.validateDataForSignUp(email, firstName, lastName, password,
                phoneNumber);
        validatedUsersData.put(EMAIL_IS_UNIQUE, findUserByEmail(email).isEmpty());
        validatedUsersData.put(PHONE_IS_UNIQUE, findUserByPhone(phoneNumber).isEmpty());
        return validatedUsersData;
    }


    @Override
    public User signIn(String email, String password) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        if (!userValidator.emailIsCorrect(email) || !userValidator.passwordIsCorrect(password)) {
            throw new ServiceException(ResourceBundleErrorMessageKey.INVALID_INPUT_VALUES);
        }
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            Optional<User> user = userDao.findByEmailAndPassword(email, password);
            if (!user.isPresent()) {
                throw new ServiceException(ResourceBundleErrorMessageKey.EMAIL_OR_PASSWORD_IS_INCORRECT_ERROR_MESSAGE);
            }
            User signedInUser = user.get();
            if (signedInUser.isBanned()) {
                throw new ServiceException(ResourceBundleErrorMessageKey.ACCOUNT_IS_BLOCKED);
            }
            if (!signedInUser.isActivated()) {
                throw new ServiceException(ResourceBundleErrorMessageKey.ACCOUNT_IS_NOT_ACTIVATED);
            }
            return signedInUser;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Signing user in error", e);
        }
    }

    @Override
    public List<User> findAllUsers(int start,int total) throws ServiceException {
        List<User> allUsers;
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            allUsers = userDao.findAll(start,total);
            return allUsers;
        } catch (IOException | DaoException e) {
            throw new ServiceException("Finding all users error", e);
        }
    }

    @Override
    public boolean banUser(int id) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            boolean banned = userDao.banUser(id);
            return banned;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Banning user error", e);
        }
    }

    @Override
    public boolean unBanUser(int id) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            boolean unbanned = userDao.unBanUser(id);
            return unbanned;
        } catch (DaoException | IOException e) {
            throw new ServiceException("Unbanning user error", e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            return userDao.findById(id);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by id error", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            return userDao.findByEmail(email);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by email error", e);
        }
    }

    @Override
    public Optional<User> findUserByPhone(String phone) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            return userDao.findByPhone(phone);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Finding user by phone error", e);
        }
    }

    @Override
    public boolean activateUser(int id) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            return userDao.activateUser(id);
        } catch (DaoException | IOException e) {
            throw new ServiceException("Activating user error", e);
        }
    }

    @Override
    public User updateUserInfo(User user) throws ServiceException {
        try (UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            User updatedUser = userDao.update(user).orElseThrow(ServiceException::new);
            return updatedUser;
        } catch (IOException | DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Map<String, Boolean> defineUsersIncorrectDataForUpdate(String email, String firstName, String lastName,
                                                                  String phoneNumber,int usersId) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        Map<String, Boolean> validatedUsersData = userValidator.validateDataForUpdate(email, firstName, lastName,
                phoneNumber);
        Optional<User> foundByEmail = findUserByEmail(email);
        Optional<User> foundByPhone = findUserByPhone(phoneNumber);
        validatedUsersData.put(EMAIL_IS_UNIQUE, foundByEmail.isEmpty() || foundByEmail.get().getId() == usersId);
        validatedUsersData.put(PHONE_IS_UNIQUE, foundByPhone.isEmpty() || foundByPhone.get().getId() == usersId);
        return validatedUsersData;
    }

    @Override
    public boolean giveAdminRightsById(int usersId) throws ServiceException {
        try(UserDao userDao = DaoFactory.getInstance().getUserDao()){
            boolean rightsWereGiven = userDao.updateRole(usersId, UserRole.ADMIN.getUserRolesId());
            return rightsWereGiven;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean pickUpAdminRightsById(int usersId) throws ServiceException {
       try(UserDao userDao = DaoFactory.getInstance().getUserDao()) {
           boolean rightsWerePickedUp = userDao.updateRole(usersId,UserRole.USER.getUserRolesId());
           return rightsWerePickedUp;
       } catch (IOException | DaoException e) {
           throw new ServiceException(e);
       }
    }

    @Override
    public int findUsersQuantity() throws ServiceException {
        try(UserDao userDao = DaoFactory.getInstance().getUserDao()) {
            int usersQuantity = userDao.findQuantity();
            return usersQuantity;
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }


}
