package LumExpress.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DeleteProductRequest {
    private Long id;
}
