package LumExpress.dtos.responses;

import LumExpress.Data.Models.Cart;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartResponse {
    private String message;
    private Cart cart;
}
