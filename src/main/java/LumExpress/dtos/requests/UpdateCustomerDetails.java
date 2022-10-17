package LumExpress.dtos.requests;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerDetails {
    private Long customerId;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
    private int buildingNumber;
    private String street;
    private String city;
    private String state;

}

