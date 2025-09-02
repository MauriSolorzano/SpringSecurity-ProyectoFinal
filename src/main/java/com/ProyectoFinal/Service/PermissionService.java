package com.ProyectoFinal.Service;

import com.ProyectoFinal.Entity.Permission;
import com.ProyectoFinal.Repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PermissionService {
    public PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // Metodo para crear un permiso
    public Permission createPermission(Permission permission){
        if (permission.getPermissionName() == null || permission.getPermissionName().isBlank()){
            throw new IllegalArgumentException("El nombre del permiso no puede ser nulo");
        }
        if (permissionRepository.existsByName(permission.getPermissionName())){
            throw new IllegalArgumentException("Ya existe un permiso con ese nombre");
        }


        return permissionRepository.save(permission);
    }

    // Metodo para listar permisos
    public List<Permission> permissionList(){
        List<Permission> permissions = permissionRepository.findAll();

        if (permissions.isEmpty()){
            throw new NoSuchElementException("No hay permisos registrados");
        }
        return permissions;
    }

    // Metodo para actualizar permiso
    public Permission updatePermission(Long idPermission, Permission detailsPermission){
        Permission existingPermission = permissionRepository.findById(idPermission)
                .orElseThrow(() -> new NoSuchElementException("No se encontro permiso con ese ID"));

        if (detailsPermission.getPermissionName() == null || detailsPermission.getPermissionName().isBlank()){
            throw new IllegalArgumentException("El nombre del permiso no puede estar vacio");
        }

        if (!Objects.equals(existingPermission.getPermissionName(), detailsPermission.getPermissionName())){
            existingPermission.setPermissionName(detailsPermission.getPermissionName());
        }

        return permissionRepository.save(existingPermission);
    }

    // Metodo para eliminar permiso
    public void deletePermission (Long idPermission){
        Permission permission = permissionRepository.findById(idPermission)
                .orElseThrow(() -> new NoSuchElementException("No se encontro permiso con ese ID"));

        permissionRepository.delete(permission);
    }

}
