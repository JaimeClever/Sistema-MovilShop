package com.sistemaVenta.Grupo06.service;

import com.sistemaVenta.Grupo06.dto.UsuarioDTO;
import com.sistemaVenta.Grupo06.entity.Rol;
import com.sistemaVenta.Grupo06.entity.Usuario;
import com.sistemaVenta.Grupo06.repository.RolRepository;
import com.sistemaVenta.Grupo06.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public Usuario registrarPrimerUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByRoles_Nombre(Rol.NombreRol.ADMIN)) {
            throw new RuntimeException("El administrador ya ha sido creado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setNombres(dto.getNombres());

        Rol rolAdmin = rolRepository.findByNombre(Rol.NombreRol.ADMIN)
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no existe"));
        usuario.getRoles().add(rolAdmin);

        return usuarioRepository.save(usuario);
    }
}

