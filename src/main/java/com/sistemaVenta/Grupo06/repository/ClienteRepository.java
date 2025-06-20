package com.sistemaVenta.Grupo06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaVenta.Grupo06.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
