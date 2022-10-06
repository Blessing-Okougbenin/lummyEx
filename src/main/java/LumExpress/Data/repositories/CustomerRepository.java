package LumExpress.Data.repositories;

import LumExpress.Data.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
