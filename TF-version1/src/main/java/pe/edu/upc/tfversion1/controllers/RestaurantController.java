package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.RestaurantDTO;
import pe.edu.upc.tfversion1.entities.Restaurant;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> listarRestaurantes() {
        return restaurantService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody RestaurantDTO restaurantDTO) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(restaurantDTO, Restaurant.class);
        restaurantService.insert(r);
    }

    @PutMapping
    public void modificar(@RequestBody RestaurantDTO restaurantDTO) {
        ModelMapper m = new ModelMapper();
        Restaurant r = m.map(restaurantDTO, Restaurant.class);
        restaurantService.update(r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }
}
