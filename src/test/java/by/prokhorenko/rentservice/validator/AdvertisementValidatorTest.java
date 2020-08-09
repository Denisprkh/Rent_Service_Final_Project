package by.prokhorenko.rentservice.validator;

import by.prokhorenko.rentservice.builder.AdvertisementDataHandlerBuilder;
import by.prokhorenko.rentservice.entity.AdvertisementDataHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementValidatorTest {

    private static final AdvertisementValidator advertisementValidator = AdvertisementValidator.getInstance();
    private List<Boolean> validationsWithAllTrueValues;

    @BeforeClass
    public void setUp() {
        validationsWithAllTrueValues = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            validationsWithAllTrueValues.add(Boolean.TRUE);
        }
    }

    @Test
    public void validateAdvertisementsDataTestAssertEquals() {
        AdvertisementDataHandler dataHandler = new AdvertisementDataHandlerBuilder()
                .buildCity("Minsk")
                .buildDistrict("Leninskiy")
                .buildHouseNumber("12a")
                .buildPrice("500")
                .buildStreet("Prushinskiy")
                .buildTitle("Comfortable")
                .buildRooms("4")
                .buildLivingArea("56.5")
                .buildAdvertisementDataHandler();
        List<Boolean> result = advertisementValidator.validateAdvertisementsData(dataHandler);
        Assert.assertEquals(validationsWithAllTrueValues, result);
    }

    @Test
    public void validateAdvertisementsDataTestAssertNotEquals() {
        AdvertisementDataHandler dataHandler = new AdvertisementDataHandlerBuilder()
                .buildCity("Minsk")
                .buildDistrict("Leninskiy")
                .buildHouseNumber("12a")
                .buildPrice(null)
                .buildStreet("Prushinskiy")
                .buildTitle(null)
                .buildRooms("4")
                .buildLivingArea("56.5")
                .buildAdvertisementDataHandler();
        List<Boolean> result = advertisementValidator.validateAdvertisementsData(dataHandler);
        Assert.assertNotEquals(validationsWithAllTrueValues, result);
    }

}
