package LumExpress.Data.repositories;

import LumExpress.Data.Models.Admin;
import LumExpress.Data.Models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

    Optional<Vendor> findByEmail(String email);

}
