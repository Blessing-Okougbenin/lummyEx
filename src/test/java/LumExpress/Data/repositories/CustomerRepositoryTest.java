package LumExpress.Data.repositories;

import LumExpress.Data.Models.Cart;
import LumExpress.Data.Models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void findByEmailTest() {
        var customer = Customer.builder().cart(new Cart()).build();
        customer.setEmail("test@gmail.com");
        customer.setFirstname("John");
        customer.setLastName("Doe");
        customer.setPassword("password");
        customerRepository.save(customer);
        log.info("saved customer ==> {}",customer);
        assertThat(customerRepository.findByEmail(customer.getEmail())).isNotNull();

    }
}