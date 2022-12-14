package LumExpress.services.customerServices;

import LumExpress.Data.Models.*;
import LumExpress.Data.repositories.CustomerRepository;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.EmailNotificationRequest;
import LumExpress.dtos.requests.UpdateCustomerDetails;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.exceptions.LumiExpressException;
import LumExpress.exceptions.UserNotFoundException;
import LumExpress.services.notificationService.EmailNotificationService;
import LumExpress.services.verificationServices.VerificationServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final EmailNotificationService emailNotificationService;
    private final VerificationServices verificationServices;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) throws LumiExpressException {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(registrationRequest.getEmail());
        if (foundCustomer.isPresent()) throw new LumiExpressException(
                String.format("User with email %s already in use",registrationRequest.getEmail()));
        Customer customer = mapper.map(registrationRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registrationRequest,customer);
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer to be saved in db::{}", savedCustomer);

        var token = verificationServices.generateVerificationTokens(savedCustomer.getEmail());
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token, savedCustomer.getFirstname()));

        return registrationResponseBuilder(savedCustomer);

    }

    private void setCustomerAddress(CustomerRegistrationRequest registrationRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(registrationRequest.getCountry());
        customer.getAddresses().add(customerAddress);

    }


    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken, String customerName ) {
        var message=   getEmailTemplate();
        String mail = null;
        if (message != null){
            var verificationUrl =  "http://localhost:8080/api/customer/verify/" + verificationToken.getToken();
            mail = String.format(message,customerName,verificationUrl, verificationToken.getToken());
            log.info("mailed url ==> {}",verificationToken.getToken());
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


    private static  CustomerRegistrationResponse registrationResponseBuilder(Customer customer){
        return CustomerRegistrationResponse.builder()
                                    .message("Successfull!")
                                    .id(customer.getId())
                                    .code(201)
                                    .build();

    }

    @Override
    public String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException {
       Customer foundCustomerToUpdate =  customerRepository.findById(updateCustomerDetails.getCustomerId())
               .orElseThrow(()-> new UserNotFoundException(String.format(
                       "customer with id %d, not found",updateCustomerDetails.getCustomerId())));
        log.info("before update ==>, {}", foundCustomerToUpdate);
       mapper.map(updateCustomerDetails,foundCustomerToUpdate);

       Set<Address> customerAddressList = foundCustomerToUpdate.getAddresses();
       Optional<Address> foundAddress = customerAddressList.stream().findFirst();

       if (foundAddress.isPresent()){
           applyAddressMapping(foundAddress.get(),updateCustomerDetails);
       }
       foundCustomerToUpdate.getAddresses().add(foundAddress.get());

       Customer updatedCustomer = customerRepository.save(foundCustomerToUpdate);
       log.info("updated customer==> {}", updatedCustomer);
        return String.format("%s, your details have been updated successfully", foundCustomerToUpdate.getFirstname());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    private void applyAddressMapping(Address foundAddress, UpdateCustomerDetails updateCustomerDetails) {
        foundAddress.setState(updateCustomerDetails.getState());
        foundAddress.setCity(updateCustomerDetails.getCity());
        foundAddress.setBuildingNumber(updateCustomerDetails.getBuildingNumber());
        foundAddress.setStreet(updateCustomerDetails.getStreet());

    }

}
