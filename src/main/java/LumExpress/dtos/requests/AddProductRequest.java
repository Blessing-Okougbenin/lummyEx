package LumExpress.dtos.requests;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class AddProductRequest {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String productCategory;
    @NotNull
    private MultipartFile image;
}
