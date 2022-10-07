package LumExpress.Data.Models;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class LumiExpressUser {
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String imageUrl;
    private boolean isEnabled;


}
