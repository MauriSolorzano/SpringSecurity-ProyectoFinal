package com.ProyectoFinal.Service;

import com.ProyectoFinal.Entity.Role;
import com.ProyectoFinal.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class RoleService {
    public RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Metodo para crear un Role
    public Role createRole(Role role) {
        if (role.getRoleName() == null || role.getRoleName().isBlank()) {
            throw new NoSuchElementException("El nombre de Role no puede ser NULO");
        }
        if (roleRepository.existsByRole(role.getRoleName())) {
            throw new IllegalArgumentException("Ya existe Role con ese nombre");
        }
        return roleRepository.save(role);
    }

    // Metodo para listar Roles
    public List<Role> roleList() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new NoSuchElementException("No hay roles creados aun");
        }
        return roles;
    }

    // Metodo para modificar Role
    public Role updateRole(Long idRole, Role detailRole) {
        Role existingRole = roleRepository.findById(idRole)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el rol"));

        if (detailRole.getRoleName().isEmpty() || detailRole.getRoleName().isBlank()) {
            throw new IllegalArgumentException("El nombre del Role no puede estar vacio");
        }

        if (!Objects.equals(existingRole.getRoleName(), detailRole.getRoleName())) {
            existingRole.setRoleName(detailRole.getRoleName());
        }
        return roleRepository.save(existingRole);
    }
}
