package by.prokhorenko.rentservice.validator;

import by.prokhorenko.rentservice.entity.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for validating data for building {@link Request}.
 */
public class RequestValidator {
    private static final String RENT_DATE_REGEX = "^([\\d]{2}\\/){2}[\\d]{4}-([\\d]{2}\\/){2}[\\d]{4}$";

    private RequestValidator() {

    }

    private static class RequestValidatorHolder {
        private static RequestValidator INSTANCE = new RequestValidator();
    }

    public static RequestValidator getInstance(){
        return RequestValidatorHolder.INSTANCE;
    }

    /**
     * Returns if data for parsing rent date is correct.
     * @param data contains date in string format
     * @return {@code true} if data is correct, {@code false} if not
     */
    public boolean dataForDateIsCorrect(String data){
        boolean isCorrect = !(isNullOrEmpty(data) || !isStringMatchesRegex(data,RENT_DATE_REGEX));
        return isCorrect;
    }

    private boolean isNullOrEmpty(String data) {
        boolean isNullOrEmpty = !(data != null && !data.isEmpty());
        return isNullOrEmpty;
    }

    private boolean isStringMatchesRegex(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
