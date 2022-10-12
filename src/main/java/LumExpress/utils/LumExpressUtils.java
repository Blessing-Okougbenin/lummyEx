package LumExpress.utils;

import java.security.SecureRandom;

public class LumExpressUtils {
    public static String generateTokens(){
        SecureRandom secureRandom = new SecureRandom();
        var token = secureRandom.nextInt(10000,99999);
        return String.valueOf(token);
    }

}
