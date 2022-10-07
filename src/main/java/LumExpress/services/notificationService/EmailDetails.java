package LumExpress.services.notificationService;

import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {

    private String userEmail;
    private String mailContent;
}
