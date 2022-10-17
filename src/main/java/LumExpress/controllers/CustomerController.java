package LumExpress.controllers;

import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.services.customerServices.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

@PostMapping()
public ResponseEntity<?> register( @RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest){

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(customerService.register(customerRegistrationRequest));
}

}
