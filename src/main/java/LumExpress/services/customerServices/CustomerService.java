package LumExpress.services.customerServices;

import LumExpress.Data.Models.Customer;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.UpdateCustomerDetails;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.exceptions.LumiExpressException;
import LumExpress.exceptions.UserNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) throws LumiExpressException;

    String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException;

    List<Customer> getAllCustomers();

}
