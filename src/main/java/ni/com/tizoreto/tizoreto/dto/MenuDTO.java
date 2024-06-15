package ni.com.tizoreto.tizoreto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MenuDTO {
    private Integer idMenu;

    @NotBlank(message = "El nombre del plato es obligatorio")
    private String name;

    @NotNull(message = "El precio del plato es obligatorio")
    @PositiveOrZero(message = "El precio del plato debe ser mayor o igual a cero")
    private Double price;

    @NotBlank(message = "El estado del plato es obligatorio")
    private String state;


}
