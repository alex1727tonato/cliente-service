package com.prueba.spring.clienteservice.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByClienteid(String clienteid);
}
