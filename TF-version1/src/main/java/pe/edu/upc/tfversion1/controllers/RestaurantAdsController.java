package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.RestaurantAdsDTO;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant_ads")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class RestaurantAdsController {

    @Autowired
    private IRestaurantAdsService restaurantAdsService;

    @GetMapping
    public List<RestaurantAdsDTO> listarAnunciosRestaurantes() {
        return restaurantAdsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RestaurantAdsDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody RestaurantAdsDTO restaurantAdsDTO) {
        ModelMapper m = new ModelMapper();
        RestaurantAds ra = m.map(restaurantAdsDTO, RestaurantAds.class);
        restaurantAdsService.insert(ra);
    }

    @PutMapping
    public void modificar(@RequestBody RestaurantAdsDTO restaurantAdsDTO) {
        ModelMapper m = new ModelMapper();
        RestaurantAds ra = m.map(restaurantAdsDTO, RestaurantAds.class);
        restaurantAdsService.update(ra);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        restaurantAdsService.delete(id);
    }
}
