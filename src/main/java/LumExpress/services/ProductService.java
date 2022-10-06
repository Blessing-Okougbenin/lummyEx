package LumExpress.services;

import LumExpress.Data.Models.Product;
import LumExpress.dtos.requests.AddProductRequest;
import LumExpress.dtos.requests.DeleteProductRequest;
import LumExpress.dtos.requests.GetAllItemsRequest;
import LumExpress.dtos.requests.UpdateProductRequest;
import LumExpress.dtos.responses.AddProductResponse;
import LumExpress.dtos.responses.DeleteProductResponse;
import LumExpress.dtos.responses.UpdateProductResponse;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest addProductRequest) throws IOException;
    UpdateProductResponse update(Long productId, JsonPatch patch);
    Product getProductById(Long id);
    Page<Product> getAllProduct(GetAllItemsRequest getAllItemsRequest);
    DeleteProductResponse deleteProduct(Long id);

    String deleteAll();
}
