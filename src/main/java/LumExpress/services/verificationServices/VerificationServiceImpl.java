package LumExpress.services.verificationServices;

import LumExpress.Data.Models.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationServices{

    @Override
    public VerificationToken generateTokens() {
        return null;
    }
}
