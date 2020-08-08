package by.prokhorenko.rentservice.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

    /**
     * Returns hashed by md5 algorithm data.
     *
     * @param data {@code String}
     * @return hash {@code String}
     */
    public static String generateHash(String data) {
        String hashedData = DigestUtils.md5Hex(data);
        return hashedData;
    }

}
