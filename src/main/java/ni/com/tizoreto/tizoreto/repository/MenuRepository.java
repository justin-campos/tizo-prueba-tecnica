package ni.com.tizoreto.tizoreto.repository;

import ni.com.tizoreto.tizoreto.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
