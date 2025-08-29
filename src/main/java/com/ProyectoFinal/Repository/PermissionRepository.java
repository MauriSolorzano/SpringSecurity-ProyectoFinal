package com.ProyectoFinal.Repository;

import com.ProyectoFinal.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String permissionName);
}
