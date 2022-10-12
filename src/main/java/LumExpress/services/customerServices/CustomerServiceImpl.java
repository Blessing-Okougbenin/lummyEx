package LumExpress.services.customerServices;

import LumExpress.Data.Models.Address;
import LumExpress.Data.Models.Cart;
import LumExpress.Data.Models.Customer;
import LumExpress.Data.Models.VerificationToken;
import LumExpress.Data.repositories.CustomerRepository;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.EmailNotificationRequest;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.services.notificationService.EmailNotificationService;
import LumExpress.services.verificationServices.VerificationServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final EmailNotificationService emailNotificationService;
    private final VerificationServices verificationServices;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {

        Customer customer = mapper.map(registrationRequest, Customer.class);
        customer.setCart(new Cart());
        Address customerAddress = new Address();
        customerAddress.setCountry(registrationRequest.getCountry());
        customer.getAddresses().add(customerAddress);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);

        var token = verificationServices.generateVerificationTokens(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token));

        return registrationResponseBuilder(savedCustomer);

    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken) {
        var email=   getEmailTemplate();
        String mail = null;
        if (email != null){
            mail = String.format(email,verificationToken.getUserEmail(), "http://localhost:8080/" +
                    verificationToken.getToken());
        }
        return EmailNotificationRequest.builder()
                                .userEmail(verificationToken.getUserEmail())
                                .mailContent(mail)
                                .build();
    }

    private String getEmailTemplate(){
        try(BufferedReader bufferedReader =
                    new BufferedReader(
                            new FileReader("C:\\Users\\user\\IdeaProjects\\lumi-Express\\src\\" +
                                                                   "main\\resources\\welcome.txt"))) {
            return bufferedReader.lines().collect(Collectors.joining());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegistrationRequest customerRegistrationRequest,Customer customer){
        Address customerAddress = new Address();
        customerAddress.setCountry(customerRegistrationRequest.getCountry());
        customer.getAddresses().add(customerAddress);
    }
    private static  CustomerRegistrationResponse registrationResponseBuilder(Customer customer){

        return CustomerRegistrationResponse.builder()
                                    .message("Successfull!")
                                    .id(customer.getId())
                                    .code(201)
                                    .build();

    }
}
