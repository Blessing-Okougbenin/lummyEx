package LumExpress.dtos.requests;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UpdateProductRequest {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String description;
    private Long productId;
}
