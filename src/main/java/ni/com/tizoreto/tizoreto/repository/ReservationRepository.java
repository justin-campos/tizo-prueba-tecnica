package ni.com.tizoreto.tizoreto.repository;

import ni.com.tizoreto.tizoreto.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
