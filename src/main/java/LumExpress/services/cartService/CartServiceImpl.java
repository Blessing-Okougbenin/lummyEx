package LumExpress.services.cartService;

import LumExpress.Data.Models.Cart;
import LumExpress.Data.Models.Items;
import LumExpress.Data.Models.Product;
import LumExpress.Data.repositories.CartRepository;
import LumExpress.Data.repositories.ProductRepository;
import LumExpress.dtos.requests.CartRequest;
import LumExpress.dtos.responses.CartResponse;
import LumExpress.exceptions.CartNotFoundException;
import LumExpress.exceptions.ProductNotFoundException;
import LumExpress.services.cartService.CartService;
import LumExpress.services.productServices.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartRequest.getCartId())
                .orElseThrow(()-> new CartNotFoundException(
                        String.format("cart with id %d not found",cartRequest.getCartId())));

        Product foundProduct = productService.getProductById(cartRequest.getProductId());
        Items item = buildCartItem(foundProduct);
        cart.getItems().add(item);
        Cart cartToBesaved = updateCartSubTotal(cart, item);
        Cart savedCart = cartRepository.save(cartToBesaved);

        return CartResponse.builder()
                            .message("item added to cart")
                            .cart(savedCart)
                            .build();
    }

    @Override
    public List<Cart> getCartList() {
        var cartList = cartRepository.findAll();
        return cartList;
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }


    private Items buildCartItem(Product foundProduct) {
        return Items.builder()
                .product(foundProduct)
                .quantity(foundProduct.getQuantity())
                .build();
    }

    private Cart updateCartSubTotal(Cart cart, Items item){
        cart.setSubTotal(cart.getSubTotal()
                .add(item.getProduct().getPrice()));
        return cart;
    }




}
