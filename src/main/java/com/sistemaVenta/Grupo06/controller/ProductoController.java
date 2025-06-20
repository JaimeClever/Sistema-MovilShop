package com.sistemaVenta.Grupo06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaVenta.Grupo06.entity.Producto;
import com.sistemaVenta.Grupo06.service.ProductoService;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	@Autowired
    private ProductoService productoService;

    //prioridad de solo que vera el admin y vendedor
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public List<Producto> listarProductos() {
        return productoService.listarTodos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    // Crear producto - solo el administrador
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // actualizar solo el administrador
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }

    // Eliminar producto prioridad al admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
