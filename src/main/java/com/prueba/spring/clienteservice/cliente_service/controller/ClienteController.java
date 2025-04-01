package com.prueba.spring.clienteservice.cliente_service.controller;

import com.prueba.spring.clienteservice.cliente_service.dto.ClienteDTO;
import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import com.prueba.spring.clienteservice.cliente_service.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.status(201).body(clienteService.crearCliente(dto));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
