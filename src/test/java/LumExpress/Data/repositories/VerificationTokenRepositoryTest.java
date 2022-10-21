package LumExpress.Data.repositories;
import LumExpress.Data.Models.VerificationToken;
import LumExpress.exceptions.VerificationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class VerificationTokenRepositoryTest {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@gmail.com");
        verificationTokenRepository.deleteAll();
    }

    @Test
    void findByUserEmail() throws VerificationTokenException {
        verificationTokenRepository.save(verificationToken);
        VerificationToken foundToken =
                verificationTokenRepository.findByUserEmail("test@gmail.com")
                        .orElseThrow(()-> new VerificationTokenException("token not found"));

        log.info("found token --> {}", foundToken);
        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getToken()).isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByToken() throws VerificationTokenException {
        verificationTokenRepository.save(verificationToken);
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken.getToken()).orElseThrow(()->
                new VerificationTokenException("token not found"));
        assertThat(token).isNotNull();
        assertThat(token.getToken()).isEqualTo(verificationToken.getToken());
    }
}