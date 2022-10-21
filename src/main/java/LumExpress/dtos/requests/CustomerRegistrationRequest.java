package LumExpress.dtos.requests;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Validated
public class CustomerRegistrationRequest {
   @NotNull(message = "you must have been born in a acountry,yea?")
   @NotEmpty(message = "country cannot be empty")
   private String country;

   @Email(message = "invalid email")
   @NotNull(message = "email cannot be null")
   @NotEmpty(message = "email cannot be empty")
   private String email;

   @NotNull(message = "provide your name")
   @NotEmpty(message = "provide your name")
   private String firstName;

   @NotNull
   @NotEmpty(message = "this field cannot be empty")
   private String password;
}


