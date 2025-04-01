package com.prueba.spring.clienteservice.cliente_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @Column(unique = true)
    private String clienteid;

    private String contrasena;
    private Boolean estado = true;
}
