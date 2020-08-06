package by.prokhorenko.rentservice.validator;

public class RequestValidator {
    private static final String RENT_DATE_REGEX = "^([\\d]{2}.){2}[\\d]{4}-([\\d]{2}.){2}[\\d]{4}$";

    private RequestValidator() {

    }

    private static class RequestValidatorHolder {
        private static RequestValidator INSTANCE = new RequestValidator();
    }

    public static RequestValidator getInstance(){
        return RequestValidatorHolder.INSTANCE;
    }

    public boolean dataForDateIsCorrect(String data){
        boolean isCorrect = !(isNullOrEmpty(data) || !data.matches(RENT_DATE_REGEX));
        return isCorrect;
    }

    private boolean isNullOrEmpty(String data) {
        boolean isNullOrEmpty = data == null || data.isEmpty();
        return isNullOrEmpty;
    }
}
