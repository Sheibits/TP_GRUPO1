package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.ArtistApplicationsDTO;
import pe.edu.upc.tfversion1.entities.ArtistApplications;
import pe.edu.upc.tfversion1.entities.ArtistProfile;
import pe.edu.upc.tfversion1.entities.RestaurantAds;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistApplicationsService;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistProfileService;
import pe.edu.upc.tfversion1.serviceinterfaces.IRestaurantAdsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist_applications")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class ArtistApplicationsController {

    @Autowired
    private IArtistApplicationsService artistApplicationsService;
    @Autowired
    private IRestaurantAdsService restaurantAdsService;
    @Autowired
    private IArtistProfileService artistProfileService;

    @GetMapping
    public List<ArtistApplicationsDTO> listarSolicitudesDeArtistas() {
        return artistApplicationsService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ArtistApplicationsDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody ArtistApplicationsDTO artistApplicationsDTO) {
        ModelMapper m = new ModelMapper();
        ArtistApplications aa = m.map(artistApplicationsDTO, ArtistApplications.class);
        RestaurantAds restaurantAds = restaurantAdsService.findById(artistApplicationsDTO.getRestaurantAdsId());
        ArtistProfile artistProfile = artistProfileService.findById(artistApplicationsDTO.getArtistProfileId());
        aa.setRestaurantAds(restaurantAds);
        aa.setArtistProfile(artistProfile);
        artistApplicationsService.insert(aa);
    }

    @PutMapping
    public void modificar(@RequestBody ArtistApplicationsDTO artistApplicationsDTO) {
        ModelMapper m = new ModelMapper();
        ArtistApplications artistApplication = m.map(artistApplicationsDTO, ArtistApplications.class);
        RestaurantAds restaurantAds = restaurantAdsService.findById(artistApplicationsDTO.getRestaurantAdsId());
        ArtistProfile artistProfile = artistProfileService.findById(artistApplicationsDTO.getArtistProfileId());
        artistApplication.setRestaurantAds(restaurantAds);
        artistApplication.setArtistProfile(artistProfile);
        artistApplicationsService.update(artistApplication);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        artistApplicationsService.delete(id);
    }
}
