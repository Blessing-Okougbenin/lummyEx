package LumExpress.services;

import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.services.customerServices.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    private CustomerRegistrationRequest registrationRequest;

    @BeforeEach
    void setUp(){
      registrationRequest = CustomerRegistrationRequest
              .builder()
              .email("blessing@gmail.com")
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
    void login(){

    }
    @Test
    void completeProfile(){

    }




}
