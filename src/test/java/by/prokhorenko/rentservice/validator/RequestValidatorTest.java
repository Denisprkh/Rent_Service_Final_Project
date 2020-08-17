package by.prokhorenko.rentservice.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestValidatorTest {

    private static final RequestValidator requestValidator = RequestValidator.getInstance();

    @Test
    public void dataForDateIsCorrectTestAssertFalse() {
        String data = "12-12-2020-12-12-2030";
        boolean result = requestValidator.dataForDateIsCorrect(data);
        Assert.assertFalse(result);
    }

    @Test
    public void dataForDateIsCorrectTestAssertTrue() {
        String data = "12/12/2020-12/02/2030";
        boolean result = requestValidator.dataForDateIsCorrect(data);
        Assert.assertTrue(result);
    }
}
