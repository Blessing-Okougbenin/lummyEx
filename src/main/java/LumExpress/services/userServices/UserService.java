package LumExpress.services.userServices;

import LumExpress.Data.Models.LumiExpressUser;
import LumExpress.dtos.requests.LoginRequest;
import LumExpress.dtos.responses.LoginResponse;
import LumExpress.exceptions.UserNotFoundException;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    LumiExpressUser getUsername(String email) throws UserNotFoundException;
}
