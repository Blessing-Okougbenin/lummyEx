package LumExpress.services.verificationServices;
import LumExpress.Data.Models.VerificationToken;
import LumExpress.Data.repositories.VerificationTokenRepository;
import LumExpress.exceptions.VerificationTokenException;
import LumExpress.utils.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationServices{
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken generateVerificationTokens(String userEmail) {
            String tokenString = LumExpressUtils.generateTokens();

       var token = VerificationToken
                .builder()
                .token(tokenString)
                .userEmail(userEmail)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();
        return verificationTokenRepository.save(token);
    }

    @Override
    public boolean isValidVerificationToken(String token) throws VerificationTokenException {
        VerificationToken foundToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(()-> new VerificationTokenException("token not found"));
        if (isTokenNotExpired(foundToken)) return true;

        throw new VerificationTokenException("token has expired");
    }

    private boolean isTokenNotExpired(VerificationToken verificationToken) {
        return LocalDateTime.now().isBefore(verificationToken.getExpiresAt());
    }
}
