package LumExpress.services.customerServices;

import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.responses.CustomerRegistrationResponse;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest);
}
