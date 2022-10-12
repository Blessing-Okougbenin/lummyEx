package LumExpress.services.userServices;

import LumExpress.dtos.requests.LoginRequest;
import LumExpress.dtos.responses.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
