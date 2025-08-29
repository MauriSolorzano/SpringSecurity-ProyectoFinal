package com.ProyectoFinal.Repository;

import com.ProyectoFinal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
