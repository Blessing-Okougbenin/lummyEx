package LumExpress.services;

import LumExpress.Data.repositories.CustomerRepository;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.UpdateCustomerDetails;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.services.customerServices.CustomerService;
import LumExpress.utils.LumExpressUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;
    private CustomerRegistrationRequest registrationRequest;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
      registrationRequest = CustomerRegistrationRequest
              .builder()
              .email("sleek@gmail.com")
              .password("password")
              .country("Nigeria")
              .build();

    }


    @Test
    void register(){
        var customerRegistrationResponse = customerService.register(registrationRequest);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage()).isNotNull();
        assertThat(customerRegistrationResponse.getId()).isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode()).isEqualTo(201);

    }


    @Test
    void completeProfile(){
        CustomerRegistrationResponse customerRegistrationResponse = customerService.register(registrationRequest);
        UpdateCustomerDetails details = UpdateCustomerDetails.builder()
                .customerId(customerRegistrationResponse.getId())
                .imageUrl(LumExpressUtils.getMockCloudinaryImageUrl())
                .lastName("test lastName")
                .phoneNumber("78635262781")
                .city("Lagos")
                .state("Lagos state")
                .buildingNumber(23)
                .firstname("test firstName")
                .street("semicolon street")
                .build();

        var updatedCustomer = customerService.completeCustomerProfile(details);
        log.info("updated customer ==> {}", updatedCustomer);
        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.contains("success")).isTrue();
    }
    @Test
    void getAllCustomers(){
        var foundCustomers = customerService.getAllCustomers();
        log.info("found customers :: {}", foundCustomers);
    }




}
