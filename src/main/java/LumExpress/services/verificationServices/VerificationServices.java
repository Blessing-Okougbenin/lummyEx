package LumExpress.services.verificationServices;

import LumExpress.Data.Models.VerificationToken;

public interface VerificationServices {
    VerificationToken generateVerificationTokens(String userName);
    boolean isValidVerificationToken(String token);
}
