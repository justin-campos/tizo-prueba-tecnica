package ni.com.tizoreto.tizoreto.controller;

import io.swagger.v3.oas.annotations.Operation;
import ni.com.tizoreto.tizoreto.dto.ReservationDTO;
import ni.com.tizoreto.tizoreto.entity.Reservation;
import ni.com.tizoreto.tizoreto.exception.TizoNotFoundException;
import ni.com.tizoreto.tizoreto.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ni.com.tizoreto.tizoreto.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    // Crea una nueva reserva.
    @PostMapping("/reservations/create/")
    @Operation(summary = "Crear una nueva reserva", description = "Crea una nueva reserva con la información proporcionada en el cuerpo de la solicitud y retorna los detalles de la reserva creada.")
    public ResponseEntity<ReservationDTO> createReservation(@Validated @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        ReservationDTO createdReservationDTO = convertToDTO(reservation);
        return new ResponseEntity<>(createdReservationDTO, HttpStatus.CREATED);
    }

    // Elimina reserva por id
    @DeleteMapping("/reservations/{id}")
    @Operation(summary = "Eliminar una reserva por ID", description = "Elimina una reserva específica basada en el ID proporcionado.")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Integer id) {
        try {
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar la reserva.");
        }
    }

    // Obtener la información de una reserva por su ID.
    @GetMapping("/reservations/{id}")
    @Operation(summary = "Obtener una reserva por ID", description = "Obtiene la información detallada de una reserva específica basada en el ID proporcionado.")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id) throws Exception{
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new TizoNotFoundException("No se encontró una reserva con el ID proporcionado");
        }
        Reservation reservation = reservationOptional.get();
        ReservationDTO reservationDTO = convertToDTO(reservation);
        return ResponseEntity.ok(reservationDTO);
    }


    // Listar todas las reservas realizadas.
    @GetMapping("/reservations")
    @Operation(summary = "Obtener todas las reservas", description = "Obtiene una lista de todas las reservas registradas en el sistema.")
    public ResponseEntity<List<ReservationDTO>> listReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOs = reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationDTOs);
    }

    // cancela una reserva
    @PutMapping("/reservations/{id}/cancel")
    @Operation(summary = "Cancelar una reserva", description = "Cambia el estado de una reserva a 'Cancelado' para una reserva específica identificada por su ID.")
    public ResponseEntity<?> cancelReservation(@PathVariable("id") Integer id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(Long.valueOf(id));
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setState("Cancelado");
            reservationRepository.save(reservation);
            return ResponseEntity.ok("Reserva cancelada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //dtos
    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setIdReserve(reservation.getIdReserve());
        reservationDTO.setCustomerName(reservation.getCustomerName());
        reservationDTO.setCustomerNumber(reservation.getCustomerNumber());
        reservationDTO.setDateReserve(reservation.getDateReserve());
        reservationDTO.setState(reservation.getState());
        reservationDTO.setMenuId(reservation.getMenu().getIdMenu());

        return reservationDTO;
    }
}
