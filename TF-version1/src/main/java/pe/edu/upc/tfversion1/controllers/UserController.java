package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.UserDTO;
import pe.edu.upc.tfversion1.entities.User;
import pe.edu.upc.tfversion1.serviceinterfaces.IUserService;
import pe.edu.upc.tfversion1.serviceimplements.UserServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> listar() {
        return userService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UserDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void registrar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        userService.insert(u);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public void modificar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        userService.insert(u);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        UserDTO dto = m.map(userService.findById(id), UserDTO.class);
        return dto;
    }

}
