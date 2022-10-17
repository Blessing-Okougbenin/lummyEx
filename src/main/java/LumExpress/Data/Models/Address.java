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
        private Long addressId;
        private String street;
        private int buildingNumber;
        private String city;
        private String state;
        private String country;

}
