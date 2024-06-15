package ni.com.tizoreto.tizoreto.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    @Column(name = "id_reserve")
    private Integer idReserve;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String customerName;

    @Min(value = 1, message = "El número de personas debe ser mayor que cero")
    private String customerNumber;

    @Future(message = "La fecha de reserva debe ser en el futuro")
    private Date dateReserve;

    @NotBlank(message = "El estado del plato es obligatorio")
    private String state;

    @NotNull(message = "El menuID obligatorio")
    @PositiveOrZero(message = "El menuid debe de ser mayor a 0")
    private Integer menuId;
}
