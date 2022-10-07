package LumExpress.services.productServices;
import LumExpress.Data.Models.Category;
import LumExpress.Data.Models.Product;
import LumExpress.Data.repositories.ProductRepository;
import LumExpress.dtos.requests.AddProductRequest;
import LumExpress.dtos.requests.GetAllItemsRequest;
import LumExpress.dtos.responses.AddProductResponse;
import LumExpress.dtos.responses.DeleteProductResponse;
import LumExpress.dtos.responses.UpdateProductResponse;
import LumExpress.exceptions.ProductNotFoundException;
import LumExpress.services.cloudServices.CloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private final CloudService cloudService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) throws IOException {
//        create a product
//        map the create product request to the product class for creation
//        get the category of the product, which is part of an enum
//        then add the value of the product category from the request to the product
       Product product = modelMapper.map(addProductRequest,Product.class);
       product.getCategories()
               .add(Category.valueOf(addProductRequest.getProductCategory().toUpperCase()));
       var imageUrl=cloudService
               .upload(addProductRequest.getImage().getBytes(), ObjectUtils.emptyMap());
       log.info("cloudinary image url ::{}", imageUrl);
       product.setImageUrl(imageUrl);
       Product savedProduct = productRepository.save(product);
        log.info("saved product::{}", savedProduct);
        return buildCreateProductResponse(savedProduct);
    }

    @Override
    public UpdateProductResponse update(Long productId, JsonPatch patch){
//        find product
        var foundProduct =
                productRepository.findById(productId)
                                 .orElseThrow(()-> new ProductNotFoundException(String.format(
                                         "Product with id %d not found",productId)));
        Product updatedProduct = applyPatchToProduct(patch,foundProduct);
//        save updatedProduct
        var savedProduct = productRepository.save(updatedProduct);
        return buildUpdateProductResponse(savedProduct);

    }
    private Product applyPatchToProduct(JsonPatch patch,Product foundProduct) {
        //      convert found product to json node
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
//        apply patch to productNode
        JsonNode patchedProductNode;
        try {
            patchedProductNode = patch.apply(productNode);
//        convert patchedNode to
            var updatedProduct =
                    objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode),Product.class);
            return updatedProduct;
        } catch (IOException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Product getProductById(Long id) {
        return  productRepository.findById(id)
                                 .orElseThrow(()-> new ProductNotFoundException(
                                         String.format("Product with id %d not found",id)));
//        if(foundProduct.isPresent()) return foundProduct.get();
//        throw new ProductNotFoundException(String.format("Product with id %d not found", id));

    }

    @Override
    public Page<Product> getAllProduct(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageSpecs = PageRequest.of(getAllItemsRequest.getPageNumber()-1,
                getAllItemsRequest.getNumberOfItemsPerPage());
        return productRepository.findAll(pageSpecs);
    }



    @Override
    public DeleteProductResponse deleteProduct(Long id) {
       var foundProduct = productRepository.findById(id)
                                                    .orElseThrow(
                                                      ()-> new ProductNotFoundException(
                                                              String.format("Product with id %d not found",id)));
        log.info("Deleted product :: {}", foundProduct);
       productRepository.delete(foundProduct);

    return DeleteProductResponse
                .builder()
                .message("Product deleted")
                .build();
    }

    @Override
    public String deleteAll() {
        productRepository.deleteAll();
        return null;
    }


    private AddProductResponse buildCreateProductResponse(Product savedProduct) {
        return AddProductResponse.builder()
                .code(201)
                .productId(savedProduct.getId())
                .message("product added successfully")
                .build();
    }

    private UpdateProductResponse buildUpdateProductResponse(Product savedProduct) {
        return UpdateProductResponse.builder()
                .statusCode(200)
                .message("Product successfully added")
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .build();
    }
}
