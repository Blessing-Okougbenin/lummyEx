package LumExpress.services.notificationService;

import LumExpress.dtos.requests.NotificationRequest;

public interface LumExpressNotificationService {
    String send(NotificationRequest notificationRequest);
}
