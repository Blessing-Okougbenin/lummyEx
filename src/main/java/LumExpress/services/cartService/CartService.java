package LumExpress.services.cartService;

import LumExpress.Data.Models.Cart;
import LumExpress.dtos.requests.CartRequest;
import LumExpress.dtos.responses.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest);
    List<Cart> getCartList();
}
