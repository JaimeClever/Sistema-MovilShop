package com.sistemaVenta.Grupo06.controller;


import com.sistemaVenta.Grupo06.dto.UsuarioDTO;
import com.sistemaVenta.Grupo06.entity.Usuario;
import com.sistemaVenta.Grupo06.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/admin")
    public ResponseEntity<?> registrarAdmin(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = usuarioService.registrarPrimerUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
