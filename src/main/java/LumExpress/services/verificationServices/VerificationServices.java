package LumExpress.services.verificationServices;

import LumExpress.Data.Models.VerificationToken;
import LumExpress.exceptions.VerificationTokenException;

public interface VerificationServices {
    VerificationToken generateVerificationTokens(String userName);
    boolean isValidVerificationToken(String token) throws VerificationTokenException;
}
