package com.sistemaVenta.Grupo06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaVenta.Grupo06.entity.Venta;


@Repository

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
