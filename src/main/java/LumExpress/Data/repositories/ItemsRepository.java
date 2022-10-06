package LumExpress.Data.repositories;



import LumExpress.Data.Models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}


