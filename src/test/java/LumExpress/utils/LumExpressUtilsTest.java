package LumExpress.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class LumExpressUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateTokens() {
        String token = LumExpressUtils.generateTokens();
        log.info("token --> {}", token);
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(5);
    }
}