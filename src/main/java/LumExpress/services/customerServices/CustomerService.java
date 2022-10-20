package LumExpress.services.customerServices;

import LumExpress.Data.Models.Customer;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.UpdateCustomerDetails;
import LumExpress.dtos.responses.CustomerRegistrationResponse;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest);

    String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails);

    List<Customer> getAllCustomers();

}
