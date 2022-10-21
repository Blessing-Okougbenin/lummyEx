package LumExpress.controllers;

import LumExpress.Data.repositories.CartRepository;
import LumExpress.dtos.requests.AddProductRequest;
import LumExpress.dtos.requests.CartRequest;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.responses.CustomerRegistrationResponse;
import LumExpress.exceptions.CartNotFoundException;
import LumExpress.exceptions.LumiExpressException;
import LumExpress.exceptions.ProductNotFoundException;
import LumExpress.services.cartService.CartService;
import LumExpress.services.customerServices.CustomerService;
import LumExpress.services.productServices.ProductService;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer/")
public class CustomerController {
    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;

@PostMapping("/register")
public ResponseEntity<?> register
        (@RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) throws LumiExpressException {
    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(customerService.register(customerRegistrationRequest));
}

@GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
    return ResponseEntity.ok(customerService.getAllCustomers());
}

@PostMapping(path = "/addp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> addProduct(
        @RequestParam String name, @RequestParam Double price,
        @RequestParam Integer quantity,@RequestParam String productCategory,
        @RequestPart MultipartFile file ) throws IOException {
      AddProductRequest addProductRequest = AddProductRequest.builder()
                                                              .name(name)
                                                              .price(BigDecimal.valueOf(price))
                                                              .productCategory(productCategory)
                                                              .quantity(quantity)
                                                              .image(file).build();
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.addProduct(addProductRequest));
}

@PostMapping("/add")
    public ResponseEntity<?> addProductToCart
        (@RequestBody @Valid CartRequest cartRequest) throws ProductNotFoundException, CartNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(cartService.addProductToCart(cartRequest));
}


}
