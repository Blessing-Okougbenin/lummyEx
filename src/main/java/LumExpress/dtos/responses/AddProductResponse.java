package LumExpress.dtos.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddProductResponse {
    private Long productId;
    private String message;
    private int code;

}
