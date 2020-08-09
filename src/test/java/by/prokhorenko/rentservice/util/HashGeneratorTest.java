package by.prokhorenko.rentservice.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HashGeneratorTest {

    @Test
    public void generateHashTestOne(){
        String data = "12345";
        String expectedResult = "12345";
        String actualResult = HashGenerator.generateHash(data);
        Assert.assertNotEquals(expectedResult,actualResult);
    }

    @Test
    public void generateHashTestTwo(){
        String data = "12345";
        String expectedResult = "827ccb0eea8a706c4c34a16891f84e7b";
        String actualResult = HashGenerator.generateHash(data);
        Assert.assertEquals(expectedResult,actualResult);
    }
}
