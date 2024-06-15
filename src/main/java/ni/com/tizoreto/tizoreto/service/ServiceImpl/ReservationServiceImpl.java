package ni.com.tizoreto.tizoreto.service.ServiceImpl;

import ni.com.tizoreto.tizoreto.dto.ReservationDTO;
import ni.com.tizoreto.tizoreto.entity.Menu;
import ni.com.tizoreto.tizoreto.entity.Reservation;
import ni.com.tizoreto.tizoreto.repository.MenuRepository;
import ni.com.tizoreto.tizoreto.repository.ReservationRepository;
import ni.com.tizoreto.tizoreto.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(reservationDTO.getCustomerName());
        reservation.setCustomerNumber(reservationDTO.getCustomerNumber());
        reservation.setDateReserve(reservationDTO.getDateReserve());
        reservation.setState(reservationDTO.getState());

        if (reservationDTO.getMenuId() != null) {
            Menu menu = menuRepository.findById(Long.valueOf(reservationDTO.getMenuId())).orElse(null);
            reservation.setMenu(menu);
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(Long.valueOf(id));
    }


}
