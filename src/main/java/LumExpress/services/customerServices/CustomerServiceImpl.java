package LumExpress.services.customerServices;

import LumExpress.Data.Models.Address;
import LumExpress.Data.Models.Cart;
import LumExpress.Data.Models.Customer;
import LumExpress.Data.repositories.CustomerRepository;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.services.customerServices.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {

        Customer customer = mapper.map(registrationRequest, Customer.class);
        customer.setCart(new Cart());
        Address customerAddress = new Address();
        customerAddress.setCountry(registrationRequest.getCountry());
        customer.getAddresses().add(customerAddress);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);
        return registrationResponseBuilder(savedCustomer);
    }

    private static  CustomerRegistrationResponse registrationResponseBuilder(Customer customer){

        return CustomerRegistrationResponse.builder()
                                    .message("Successfull!")
                                    .id(customer.getId())
                                    .code(201)
                                    .build();

    }
}
