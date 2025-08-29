package com.ProyectoFinal.Service;

import com.ProyectoFinal.Entity.Permission;
import com.ProyectoFinal.Repository.PermissionRepository;
import org.springframework.stereotype.Service;

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
}
