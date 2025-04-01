package com.prueba.spring.clienteservice.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prueba.spring.clienteservice.cliente_service.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
