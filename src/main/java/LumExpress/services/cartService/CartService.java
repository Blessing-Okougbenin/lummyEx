package LumExpress.services.cartService;

import LumExpress.Data.Models.Cart;
import LumExpress.dtos.requests.CartRequest;
import LumExpress.dtos.responses.CartResponse;
import LumExpress.exceptions.CartNotFoundException;
import LumExpress.exceptions.ProductNotFoundException;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException;
    List<Cart> getCartList();
    void saveCart(Cart cart);
}
