package by.prokhorenko.rentservice.validator;

import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserAdvertisementDataHandler;
import by.prokhorenko.rentservice.entity.flat.Flat;
import by.prokhorenko.rentservice.entity.flat.FlatAddress;
import by.prokhorenko.rentservice.entity.flat.FlatDescription;
import by.prokhorenko.rentservice.util.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertisementValidator {

    private static final String COMMON_STRING_DATA_REGEX = "^[a-zA-Zа-яА-Я]{2,45}$";
    private static final String PRICE_REGEX = "^[0-9.]{1,45}$";
    private static final String LOCATION_REGEX = "^[a-zA-Zа-яА-Я.-]{2,45}$";
    private static final String HOUSE_NUMBER_REGEX = "^[a-zA-Z0-9_\\-.]{2,40}$";
    private static final String ROOMS_REGEX = "^[0-9]{1,10}$";
    private static final String AREA_REGEX = "^[0-9.]{1,45}$";
    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementValidator() {

    }

    private static class AdvertisementValidatorHolder {
        private static AdvertisementValidator INSTANCE = new AdvertisementValidator();
    }

    public static AdvertisementValidator getInstance(){
        return AdvertisementValidatorHolder.INSTANCE;
    }

    public List<Boolean> validateAdvertisementsData(UserAdvertisementDataHandler handler) {
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
        LOG.debug(validations);
        return validations;
    }

    private boolean advertisementTitleDataIsValid(String titleData) {
        boolean isValid = !(isNullOrEmpty(titleData) || !titleData.matches(COMMON_STRING_DATA_REGEX));
        return isValid;
    }

    private boolean priceDataIsValid(String priceData) {
        boolean isValid = !(isNullOrEmpty(priceData) || !priceData.matches(PRICE_REGEX));
        return isValid;
    }

    private boolean cityDataIsValid(String cityData) {
        boolean isValid = !(isNullOrEmpty(cityData) || !cityData.matches(LOCATION_REGEX));
        return isValid;
    }


    private boolean districtDataIsValid(String districtData) {
        boolean isValid = !(isNullOrEmpty(districtData) || !districtData.matches(LOCATION_REGEX));
        return isValid;
    }

    private boolean streetDataIsValid(String streetData) {
        boolean isValid = !(isNullOrEmpty(streetData) || !streetData.matches(LOCATION_REGEX));
        return isValid;
    }

    private boolean houseNumberDataIsValid(String houseNumberData) {
        boolean isValid = !(isNullOrEmpty(houseNumberData) || !houseNumberData.matches(HOUSE_NUMBER_REGEX));
        return isValid;
    }

    private boolean roomsDataIsValid(String roomsData) {
        boolean isValid = !(isNullOrEmpty(roomsData) || !roomsData.matches(ROOMS_REGEX));
        return isValid;
    }

    private boolean areaDataIsValid(String areaData) {
        boolean isValid = !(isNullOrEmpty(areaData) || !areaData.matches(AREA_REGEX));
        return isValid;
    }


    private boolean isNullOrEmpty(String data) {
        boolean isNullOrEmpty = data == null || data.isEmpty();
        return isNullOrEmpty;
    }
}
