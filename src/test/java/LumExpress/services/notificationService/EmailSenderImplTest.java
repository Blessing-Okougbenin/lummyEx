package LumExpress.services.notificationService;

import LumExpress.dtos.requests.EmailNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class EmailSenderImplTest {

    @Autowired
    private EmailNotificationService emailSender;


    @Test
    void sendHtmlMail() {
        EmailNotificationRequest emailDetails = new EmailNotificationRequest();
        emailDetails.setUserEmail("blessingokougbenin879@gmail.com");
        emailDetails.setMailContent("Hi, Love");
        String response =
                emailSender.sendHtmlMail(emailDetails);
        log.info("response --> {}",response);
        assertThat(response.contains("successfully")).isTrue();
    }
}