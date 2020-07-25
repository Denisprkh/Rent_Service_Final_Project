package by.prokhorenko.rentservice.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

    public static String generateHash(String password) {
        String hashedPassword = DigestUtils.md5Hex(password);
        return hashedPassword;
    }

}
