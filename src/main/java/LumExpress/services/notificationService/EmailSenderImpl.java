package LumExpress.services.notificationService;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;

public class EmailSenderImpl implements EmailSender {

    private JavaMailSender javaMailSender;
    @Override
    public String sendHtmlMail(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("no-reply@email.lumiExpress.com.ng");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
