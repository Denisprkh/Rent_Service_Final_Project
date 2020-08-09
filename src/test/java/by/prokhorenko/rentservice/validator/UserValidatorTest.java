package by.prokhorenko.rentservice.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserValidatorTest {

    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final UserValidator userValidator = UserValidator.getInstance();
    private Map<String, Boolean> validationsForSignUpWithFalseValues;
    private Map<String, Boolean> validationsForSignUpWithAllTrueValues;

    @BeforeClass
    public void setUp() {
        validationsForSignUpWithFalseValues = new HashMap<>();
        validationsForSignUpWithFalseValues.put(EMAIL, Boolean.FALSE);
        validationsForSignUpWithFalseValues.put(FIRST_NAME, Boolean.FALSE);
        validationsForSignUpWithFalseValues.put(LAST_NAME, Boolean.TRUE);
        validationsForSignUpWithFalseValues.put(PASSWORD, Boolean.TRUE);
        validationsForSignUpWithFalseValues.put(PHONE_NUMBER, Boolean.FALSE);

        validationsForSignUpWithAllTrueValues = new HashMap<>();
        validationsForSignUpWithAllTrueValues.put(EMAIL, Boolean.TRUE);
        validationsForSignUpWithAllTrueValues.put(FIRST_NAME, Boolean.TRUE);
        validationsForSignUpWithAllTrueValues.put(LAST_NAME, Boolean.TRUE);
        validationsForSignUpWithAllTrueValues.put(PASSWORD, Boolean.TRUE);
        validationsForSignUpWithAllTrueValues.put(PHONE_NUMBER, Boolean.TRUE);
    }

    @Test
    public void validateUserIncorrectDataForSignUpAssertEquals() {
        Map<String, Boolean> result = userValidator.validateDataForSignUp("email", "Denis12",
                "Prokhorenko", "12345", "+374324423");
        Assert.assertEquals(validationsForSignUpWithFalseValues, result);
    }

    @Test
    public void validateUserIncorrectDataForSignUpAssertNotEquals() {
        Map<String, Boolean> result = userValidator.validateDataForSignUp("email@mail.ru", "Denis",
                "Prokhorenko1", "12345", "+375(44)232-33-33");
        Assert.assertNotEquals(validationsForSignUpWithFalseValues, result);
    }

    @Test
    public void validateUserCorrectDataForSignUpAssertEquals() {
        Map<String, Boolean> result = userValidator.validateDataForSignUp("email@mail.ru", "Denis",
                "Prokhorenko", "12345", "+375(44)232-33-33");
        Assert.assertEquals(validationsForSignUpWithAllTrueValues, result);
    }

    @Test
    public void validateUserCorrectDataForSignUpAssertNotEquals() {
        Map<String, Boolean> result = userValidator.validateDataForSignUp("email@mail.ru@", "Denis12",
                "Prokho12nko", "12345", "+375(44)2323-33");
        Assert.assertNotEquals(validationsForSignUpWithAllTrueValues, result);
    }

}
