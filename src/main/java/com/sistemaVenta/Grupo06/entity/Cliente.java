package com.sistemaVenta.Grupo06.entity;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String correo;
    private String dni;

    
	
=======
public class Cliente {

>>>>>>> f249ec7575d874d9b0ee0a8c1564973c4c39072c
}
