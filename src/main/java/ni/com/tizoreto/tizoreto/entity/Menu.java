package ni.com.tizoreto.tizoreto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "Menu")
@Data
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMenu;

    @NotBlank(message = "El nombre del plato es obligatorio")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "El precio del plato es obligatorio")
    @PositiveOrZero(message = "El precio del plato debe ser mayor o igual a cero")
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @OneToOne(mappedBy = "menu")
    private Reservation reservation;

    @OneToMany(mappedBy = "menu")
    private List<MenuDinner> menuDinners;

}
