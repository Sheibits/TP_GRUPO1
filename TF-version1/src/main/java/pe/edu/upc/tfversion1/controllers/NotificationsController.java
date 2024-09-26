package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.NotificationsDTO;
import pe.edu.upc.tfversion1.entities.Notifications;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.INotificationsService;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class NotificationsController {

    @Autowired
    private INotificationsService notificationsService;

    @Autowired
    private IRestaurantAdsService restaurantAdsService;

    @GetMapping
    public List<NotificationsDTO> listarNotificaciones() {
        return notificationsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, NotificationsDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody NotificationsDTO notificationsDTO) {
        ModelMapper m = new ModelMapper();
        Notifications notification = m.map(notificationsDTO, Notifications.class);

        // Buscar RestaurantAds existente en la base de datos
        RestaurantAds restaurantAds = restaurantAdsService.findById(notificationsDTO.getRestaurantAdsId());

        // Asignar la entidad persistida a Notifications
        notification.setRestaurantAds(restaurantAds);

        // Guardar Notifications
        notificationsService.insert(notification);
    }

    @PutMapping
    public void modificar(@RequestBody NotificationsDTO notificationsDTO) {
        ModelMapper m = new ModelMapper();
        Notifications notification = m.map(notificationsDTO, Notifications.class);

        // Buscar RestaurantAds existente en la base de datos
        RestaurantAds restaurantAds = restaurantAdsService.findById(notificationsDTO.getRestaurantAdsId());

        // Asignar la entidad persistida a Notifications
        notification.setRestaurantAds(restaurantAds);

        // Actualizar Notifications
        notificationsService.update(notification);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        notificationsService.delete(id);
    }
}
