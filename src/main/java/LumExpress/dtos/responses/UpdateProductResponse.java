package LumExpress.dtos.responses;


import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UpdateProductResponse {
    private String message;
    private int statusCode;
    private String name;
    private String description;
    private BigDecimal price;
}
