package LumExpress.services.notificationService;

import LumExpress.dtos.requests.NotificationRequest;

public interface NotificationService {
    String send(NotificationRequest notificationRequest);
}
