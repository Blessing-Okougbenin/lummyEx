package LumExpress.Data.repositories;

import LumExpress.Data.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
