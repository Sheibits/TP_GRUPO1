package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.ArtistProfileDTO;
import pe.edu.upc.tfversion1.entities.ArtistProfile;
import pe.edu.upc.tfversion1.serviceinterfaces.IArtistProfileService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist_profiles")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class ArtistProfileController {

    @Autowired
    private IArtistProfileService artistProfileService;

    @GetMapping
    public List<ArtistProfileDTO> listarPerfilesDeArtistas() {
        return artistProfileService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ArtistProfileDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void registrar(@RequestBody ArtistProfileDTO artistProfileDTO) {
        ModelMapper m = new ModelMapper();
        ArtistProfile ap = m.map(artistProfileDTO, ArtistProfile.class);
        artistProfileService.insert(ap);
    }

    @PutMapping
    public void modificar(@RequestBody ArtistProfileDTO artistProfileDTO) {
        ModelMapper m = new ModelMapper();
        ArtistProfile ap = m.map(artistProfileDTO, ArtistProfile.class);
        artistProfileService.update(ap);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        artistProfileService.delete(id);
    }
}
