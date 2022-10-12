package LumExpress.services.verificationServices;

import LumExpress.Data.Models.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class VerificationServiceImplTest {
    @Autowired
    private VerificationServices verificationServices;

    private VerificationToken verificationToken;
    @BeforeEach
    void setUp() {
        verificationToken = verificationServices.generateVerificationTokens("test@gmail.com");
    }

    @Test
    void generateVerificationTokens() {
        log.info("verificationToken ==> {}", verificationToken);
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getToken().length()).isEqualTo(5);
    }

    @Test
    void isValidVerificationToken(){
        var foundToken = verificationServices.isValidVerificationToken(verificationToken.getToken());
        assertThat(foundToken).isTrue();

    }
}