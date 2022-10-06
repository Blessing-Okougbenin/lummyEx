package LumExpress.Data.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String street;
        private int buildingNumber;
        private int city;
        private int state;
        private String country;

}
