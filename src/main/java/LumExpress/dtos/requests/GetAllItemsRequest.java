package LumExpress.dtos.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllItemsRequest {
    private int pageNumber;
    private int numberOfItemsPerPage;

}
