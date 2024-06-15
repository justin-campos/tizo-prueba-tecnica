package ni.com.tizoreto.tizoreto.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import ni.com.tizoreto.tizoreto.dto.MenuDTO;
import ni.com.tizoreto.tizoreto.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ni.com.tizoreto.tizoreto.repository.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MenuController {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Obtener la información de un plato por su ID.
    @GetMapping("/menu/{id}")
    @Operation(summary = "Obtener un ítem de menú por ID", description = "Obtiene la información detallada de un ítem de menú utilizando su ID. Si el ítem de menú no se encuentra, retorna un mensaje de error.")
    public ResponseEntity<?> getMenuById(@PathVariable("id") @NotNull Long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un menú con el ID proporcionado");
        }
        Menu menu = menuOptional.get();
        MenuDTO menuDTO = convertToDTO(menu);
        return ResponseEntity.ok(menuDTO);
    }

    // Lista todos los platos del menú.
    @GetMapping("/menu")
    @Operation(summary = "Obtener todos los ítems de menú", description = "Obtiene una lista de todos los ítems de menú")
    @ApiResponse(code = 200, message = "Ítems de menú recuperados correctamente")
    public ResponseEntity<List<MenuDTO>> listMenu() {
        List<Menu> menuItems = menuRepository.findAll();
        List<MenuDTO> menuDTOs = menuItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(menuDTOs);
    }


    //dto
    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setIdMenu(menu.getIdMenu());
        menuDTO.setName(menu.getName());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setState(menu.getState());
        return menuDTO;
    }

}
