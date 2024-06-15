package ni.com.tizoreto.tizoreto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "Dinner")
@Data
@NoArgsConstructor
public class Dinner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dinner")
    private Integer idDinner;

    @NotBlank(message = "El nombre obligatorio")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @NotBlank(message = "Precio obligatorio")
    @PositiveOrZero(message = "El precio del plato debe ser mayor o igual a cero")
    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "dinner")
    private List<MenuDinner> menuDinners;
}
