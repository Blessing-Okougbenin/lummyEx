package LumExpress.dtos.responses;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationResponse {
    private long id;
    private String message;
    private int code;
}


