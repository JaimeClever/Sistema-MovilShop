package com.sistemaVenta.Grupo06.service;

import com.sistemaVenta.Grupo06.dto.UsuarioDTO;
import com.sistemaVenta.Grupo06.entity.Rol;
import com.sistemaVenta.Grupo06.entity.Usuario;
import com.sistemaVenta.Grupo06.repository.RolRepository;
import com.sistemaVenta.Grupo06.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        usuario.getRoles().add(rolAdmin);// OBTIENE EL ROL Y LO ASIGANA AL USUARIO

        return usuarioRepository.save(usuario);
    }


    // implemenmtacion del metodo para registrar un vendedor

    public Usuario registrarVendedor(UsuarioDTO dto) {
        // Obtener el usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Usuario admin = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
        // Verificar si tiene rol ADMIN
        boolean esAdmin = admin.getRoles().stream()
                .anyMatch(rol -> rol.getNombre() == Rol.NombreRol.ADMIN);

        if (!esAdmin) {
            throw new RuntimeException("Solo el administrador puede registrar vendedores");
        }
        // Verificar si el vendedor ya está registrado
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Usuario ya registrado");
        }
        // Crear el nuevo usuario vendedor
        Usuario vendedor = new Usuario();
        vendedor.setUsername(dto.getUsername());
        vendedor.setPassword(passwordEncoder.encode(dto.getPassword()));
        vendedor.setNombres(dto.getNombres());

        Rol rolVendedor = rolRepository.findByNombre(Rol.NombreRol.VENDEDOR)
                .orElseThrow(() -> new RuntimeException("Rol VENDEDOR no existe"));
        vendedor.getRoles().add(rolVendedor);

        return usuarioRepository.save(vendedor);
    }

// metodo encargado de autenticar al usuario
    public Usuario loginUsuario(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}

