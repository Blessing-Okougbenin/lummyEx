package LumExpress.Data.repositories;

import LumExpress.Data.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
