package by.prokhorenko.rentservice.validator;

import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.AdvertisementDataHandler;

import java.util.List;

/**
 * Class for validating data for building {@link Advertisement}.
 */
public class AdvertisementValidator {

    private static final String COMMON_STRING_DATA_REGEX = "^[a-zA-Zа-яА-Я\\s]{2,45}$";
    private static final String PRICE_REGEX = "^[0-9.]{1,45}$";
    private static final String LOCATION_REGEX = "^[a-zA-Zа-яА-Я\\s.-]{2,45}$";
    private static final String HOUSE_NUMBER_REGEX = "^\\d+[a-zа-я\\/\\d]{0,10}$";
    private static final String ROOMS_REGEX = "^[0-9]{1,10}$";
    private static final String AREA_REGEX = "^[0-9.]{1,45}$";

    private AdvertisementValidator() {

    }

    private static class AdvertisementValidatorHolder {
        private static AdvertisementValidator INSTANCE = new AdvertisementValidator();
    }

    public static AdvertisementValidator getInstance() {
        return AdvertisementValidatorHolder.INSTANCE;
    }

    /**
     * Returns List of {@code Boolean} which represents if data is correct. If List contains
     * {@code false} values, user will get information about them.
     *
     * @param handler {@link AdvertisementDataHandler}
     * @return List of {@code Boolean}
     */
    public List<Boolean> validateAdvertisementsData(AdvertisementDataHandler handler) {
        List<Boolean> validations = List.of(
                advertisementTitleDataIsValid(handler.getTitle()),
                priceDataIsValid(handler.getPrice()),
                cityDataIsValid(handler.getCity()),
                districtDataIsValid(handler.getDistrict()),
                streetDataIsValid(handler.getStreet()),
                houseNumberDataIsValid(handler.getHouseNumber()),
                roomsDataIsValid(handler.getRooms()),
                areaDataIsValid(handler.getArea())
        );
        return validations;
    }

    private boolean advertisementTitleDataIsValid(String titleData) {
        boolean isValid = !(isNullOrBlank(titleData) || !titleData.matches(COMMON_STRING_DATA_REGEX));
        return isValid;
    }

    private boolean priceDataIsValid(String priceData) {
        boolean isValid = !(isNullOrBlank(priceData) || !priceData.matches(PRICE_REGEX));
        return isValid;
    }

    private boolean cityDataIsValid(String cityData) {
        boolean isValid = !(isNullOrBlank(cityData) || !cityData.matches(LOCATION_REGEX));
        return isValid;
    }


    private boolean districtDataIsValid(String districtData) {
        boolean isValid = !(isNullOrBlank(districtData) || !districtData.matches(LOCATION_REGEX));
        return isValid;
    }

    private boolean streetDataIsValid(String streetData) {
        boolean isValid = !(isNullOrBlank(streetData) || !streetData.matches(LOCATION_REGEX));
        return isValid;
    }

    private boolean houseNumberDataIsValid(String houseNumberData) {
        boolean isValid = !(isNullOrBlank(houseNumberData) || !houseNumberData.matches(HOUSE_NUMBER_REGEX));
        return isValid;
    }

    private boolean roomsDataIsValid(String roomsData) {
        boolean isValid = !(isNullOrBlank(roomsData) || !roomsData.matches(ROOMS_REGEX));
        return isValid;
    }

    private boolean areaDataIsValid(String areaData) {
        boolean isValid = !(isNullOrBlank(areaData) || !areaData.matches(AREA_REGEX));
        return isValid;
    }


    private boolean isNullOrBlank(String data) {
        boolean isNullOrEmpty = data == null || data.isBlank();
        return isNullOrEmpty;
    }
}
