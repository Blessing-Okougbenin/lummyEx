package LumExpress.services.customerServices;

import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.UpdateCustomerDetails;
import LumExpress.dtos.responses.CustomerRegistrationResponse;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest);

    String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails);
//    String updateProfile()

}
