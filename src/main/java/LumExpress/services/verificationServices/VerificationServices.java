package LumExpress.services.verificationServices;

import LumExpress.Data.Models.VerificationToken;

public interface VerificationServices {
    VerificationToken generateTokens();
}
