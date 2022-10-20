package LumExpress.services;

import LumExpress.Data.Models.Product;
import LumExpress.dtos.requests.AddProductRequest;
import LumExpress.dtos.requests.GetAllItemsRequest;
import LumExpress.dtos.requests.UpdateProductRequest;
import LumExpress.dtos.responses.AddProductResponse;
import LumExpress.dtos.responses.UpdateProductResponse;
import LumExpress.services.productServices.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    AddProductRequest addProductRequest;
    AddProductResponse addProductResponse;



    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\lumi-Express\\src\\main\\resources\\images\\milk.jpg");
        MultipartFile file = new MockMultipartFile("Peak", Files.readAllBytes(path));
        addProductRequest = buildAddProductRequest(file);
       addProductResponse =  productService.addProduct(addProductRequest);
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
    void create(){
        assertThat(addProductResponse).isNotNull();
        assertThat(addProductResponse.getProductId()).isGreaterThan(0L);
        assertThat(addProductResponse.getMessage()).isNotNull();
        assertThat(addProductResponse.getCode()).isEqualTo(201);
    }




    @Test
    void getProductByIdTest() {
        Product foundProduct =
                productService.getProductById(addProductResponse.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(addProductResponse.getProductId());
    }




    @Test
    void getAllProductTest() {
        var getItemsRequest = buildGetItemsRequest();
        Page<Product> productPage = productService.getAllProduct(getItemsRequest);
        log.info("page contents:: {}, {}", productPage.getTotalPages(),productPage.getTotalElements());
        assertThat(productPage).isNotNull();
        assertThat(productPage.getTotalElements()).isGreaterThan(0);
        assertThat(productPage.getTotalPages()).isEqualTo(1);


    }
    private GetAllItemsRequest buildGetItemsRequest() {
        return GetAllItemsRequest
                .builder()
                .numberOfItemsPerPage(8)
                .pageNumber(1)
                .build();
    }




    @Test
    void updateProductDetailsTest() throws JsonProcessingException, JsonPointerException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode value = mapper.readTree("\"This is quality milk\"");
        JsonPatch patch = new JsonPatch(List.of(new ReplaceOperation(new JsonPointer("/description"),value)));

        UpdateProductResponse updateProductResponse =
                productService.update(buildUpdateProductRequest().getProductId(), patch);
        log.info("updated product:: {}",updateProductResponse);
        assertThat(updateProductResponse).isNotNull();
        assertThat(updateProductResponse.getStatusCode()).isEqualTo(200);
        assertThat(productService.getProductById(addProductResponse.getProductId()).getDescription()).isNotEqualTo(updateProductResponse.getDescription());

    }
    private UpdateProductRequest buildUpdateProductRequest() {
        return UpdateProductRequest.builder()
                .productId(addProductResponse.getProductId())
                .price(BigDecimal.valueOf(50.0))
                .description("This is quality milk")
                .quantity(10)
                .build();
    }



    @Test
    void deleteAllElements(){
        var deleted = productService.deleteAll();
        assertThat(deleted).isNull();
    }

}