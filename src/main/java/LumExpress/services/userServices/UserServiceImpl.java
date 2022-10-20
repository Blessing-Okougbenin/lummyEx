package LumExpress.services.userServices;

import LumExpress.Data.Models.*;
import LumExpress.Data.repositories.AdminRepository;
import LumExpress.Data.repositories.CustomerRepository;
import LumExpress.Data.repositories.VendorRepository;
import LumExpress.dtos.requests.LoginRequest;
import LumExpress.dtos.responses.LoginResponse;
import LumExpress.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class
UserServiceImpl implements UserService{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
     Optional<Customer> customer =  customerRepository.findByEmail(loginRequest.getEmail());
     if (customer.isPresent() && customer.get().getPassword().equals(loginRequest.getPassword())) return  buildSuccessfulLoginResponse(customer.get());

     Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
     if (admin.isPresent() && admin.get().getPassword().equals(loginRequest.getPassword())) return buildSuccessfulLoginResponse(admin.get());

     Optional<Vendor> vendor = vendorRepository.findByEmail(loginRequest.getEmail());
     if (vendor.isPresent() && vendor.get().getPassword().equals(loginRequest.getPassword())) return buildSuccessfulLoginResponse(vendor.get());


     return LoginResponse.builder()
                             .code(400)
                             .message("Invalid Login!!!")
                             .build();
    }

    @Override
    public LumiExpressUser getUsername(String email) {
        var foundAdmin = adminRepository.findByEmail(email);
        if(foundAdmin.isPresent()) return foundAdmin.get();

        var foundCustomer = customerRepository.findByEmail(email);
        if(foundCustomer.isPresent()) return foundCustomer.get();

        var foundVendor = vendorRepository.findByEmail(email);
        if(foundVendor.isPresent()) return foundVendor.get();

        throw new UserNotFoundException("User does not exist");
    }

    private LoginResponse buildSuccessfulLoginResponse(LumiExpressUser user) {
        return LoginResponse.builder()
                            .message("Logged in successfully")
                            .code(200)
                            .build();
    }
}
