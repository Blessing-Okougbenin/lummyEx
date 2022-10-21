package LumExpress.dtos.requests;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartRequest {
    private Long cartId;
    private Long productId;
}
