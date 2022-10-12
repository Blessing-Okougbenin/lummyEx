package LumExpress.services.notificationService;

import LumExpress.dtos.requests.EmailNotificationRequest;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailDetails);
}
