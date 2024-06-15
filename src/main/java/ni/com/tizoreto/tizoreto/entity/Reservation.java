package ni.com.tizoreto.tizoreto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Reserve")
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserve")
    private Integer idReserve;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Min(value = 1, message = "El número de personas debe ser mayor que cero")
    @Column(name = "customer_number", nullable = false, length = 15)
    private String customerNumber;

    @Future(message = "La fecha de reserva debe ser en el futuro")
    @Column(name = "date_reserve", nullable = false)
    private Date dateReserve;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @OneToOne
    @JoinColumn(name = "id_menu", nullable = false, unique = true)
    private Menu menu;
}
