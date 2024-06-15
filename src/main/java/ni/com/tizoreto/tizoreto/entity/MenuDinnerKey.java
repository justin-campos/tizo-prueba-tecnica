package ni.com.tizoreto.tizoreto.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class MenuDinnerKey implements Serializable {
    private Integer idMenu;
    private Integer idDinner;
}
