package pe.edu.upc.tfversion1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tfversion1.dtos.RoleDTO;
import pe.edu.upc.tfversion1.entities.Role;
import pe.edu.upc.tfversion1.serviceinterfaces.IRoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public List<RoleDTO> list() {
        return roleService.list().stream().map(role -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(role, RoleDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody RoleDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDTO, Role.class);
        roleService.insert(role);
    }

    @PutMapping
    public void update(@RequestBody RoleDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDTO, Role.class);
        roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        roleService.delete(id);
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable("id") int id) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = roleService.findById(id);
        return modelMapper.map(role, RoleDTO.class);
    }
}
