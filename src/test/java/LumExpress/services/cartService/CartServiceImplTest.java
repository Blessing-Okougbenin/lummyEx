package LumExpress.services.cartService;

import LumExpress.Data.Models.Cart;
import LumExpress.Data.repositories.CartRepository;
import LumExpress.dtos.requests.AddProductRequest;
import LumExpress.dtos.requests.CartRequest;
import LumExpress.dtos.requests.CustomerRegistrationRequest;
import LumExpress.dtos.requests.GetAllItemsRequest;
import LumExpress.dtos.responses.AddProductResponse;
import LumExpress.dtos.responses.CartResponse;
import LumExpress.services.customerServices.CustomerService;
import LumExpress.services.productServices.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;


    @BeforeEach
    void setUp() throws IOException {
            Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\lumi-Express\\src\\main\\resources\\images\\milk.jpg");
            MultipartFile file = new MockMultipartFile("Peak", Files.readAllBytes(path));
        AddProductRequest addProductRequest = buildAddProductRequest(file);
            var product = productService.addProduct(addProductRequest);
        }

        private AddProductRequest buildAddProductRequest(MultipartFile file) {
            return AddProductRequest.builder()
                    .name("Milk")
                    .productCategory("Beverages")
                    .price(BigDecimal.valueOf(30.00))
                    .quantity(10)
                    .image(file)
                    .build();
        }


    @Test
    void addProductToCart() {

        CartRequest cartRequest = CartRequest.builder()
                .cartId(cartService.getCartList().get(cartService.getCartList().size()-1).getId())
                .productId(productService.getAllProduct(new GetAllItemsRequest(1,10))
                        .getContent().get(productService.getAllProduct(new GetAllItemsRequest(1,10))
                                .getNumberOfElements()-1).getId()).build();
        log.info("cart == {}",cartRequest);
        CartResponse cartResponse = cartService.addProductToCart(cartRequest);
        log.info("added product ==> {}", cartResponse);
        assertThat(cartResponse).isNotNull();
    }
}