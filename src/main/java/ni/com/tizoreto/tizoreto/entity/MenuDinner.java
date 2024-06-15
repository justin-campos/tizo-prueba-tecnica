package ni.com.tizoreto.tizoreto.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menudinner")
@Data
@NoArgsConstructor
public class MenuDinner {

    @EmbeddedId
    private MenuDinnerKey id;

    @ManyToOne
    @MapsId("idMenu")
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @ManyToOne
    @MapsId("idDinner")
    @JoinColumn(name = "id_dinner")
    private Dinner dinner;
}
