package com.sistemaVenta.Grupo06.controller;


import com.sistemaVenta.Grupo06.entity.Producto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Integer cantidad;
	    private Double precio;
	    private Double subtotal; // ✅ Agregar este campo

	    @ManyToOne
	    @JoinColumn(name = "producto_id")
	    private Producto producto;

	    @ManyToOne
	    @JoinColumn(name = "venta_id")
	    private Venta venta;
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Integer getCantidad() {
	        return cantidad;
	    }

	    public void setCantidad(Integer cantidad) {
	        this.cantidad = cantidad;
	    }

	    public Double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(Double precio) {
	        this.precio = precio;
	    }

	    public Double getSubtotal() {
	        return subtotal;
	    }

	    public void setSubtotal(Double subtotal) {
	        this.subtotal = subtotal;
	    }

	    public Producto getProducto() {
	        return producto;
	    }

	    public void setProducto(Producto producto) {
	        this.producto = producto;
	    }

	    public Venta getVenta() {
	        return venta;
	    }

	    public void setVenta(Venta venta) {
	        this.venta = venta;
	    }
}
