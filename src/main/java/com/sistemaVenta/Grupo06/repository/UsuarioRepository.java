package com.sistemaVenta.Grupo06.repository;

import com.sistemaVenta.Grupo06.entity.Rol;
import com.sistemaVenta.Grupo06.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByRoles_Nombre(Rol.NombreRol nombre);
}
