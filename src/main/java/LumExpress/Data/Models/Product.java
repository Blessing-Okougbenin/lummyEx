package LumExpress.Data.Models;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private BigDecimal price = BigDecimal.ZERO;
    private int quantity;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();
    private String imageUrl;

}
