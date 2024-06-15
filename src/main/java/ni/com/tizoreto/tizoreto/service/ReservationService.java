package ni.com.tizoreto.tizoreto.service;

import ni.com.tizoreto.tizoreto.dto.ReservationDTO;
import ni.com.tizoreto.tizoreto.entity.Reservation;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservationDTO);
    void deleteReservationById(Integer id);
}
