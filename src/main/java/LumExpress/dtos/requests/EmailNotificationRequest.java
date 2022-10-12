package LumExpress.dtos.requests;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotificationRequest {
    private String userEmail;
    private String mailContent;
}
