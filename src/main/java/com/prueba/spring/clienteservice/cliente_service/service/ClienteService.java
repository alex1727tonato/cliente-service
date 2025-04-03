package com.prueba.spring.clienteservice.cliente_service.service;

import com.prueba.spring.clienteservice.cliente_service.dto.ClienteDTO;
import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import com.prueba.spring.clienteservice.cliente_service.entity.Persona;
import com.prueba.spring.clienteservice.cliente_service.exception.ResourceNotFoundException;
import com.prueba.spring.clienteservice.cliente_service.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Cliente crearCliente(ClienteDTO dto) {
        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setGenero(dto.getGenero());
        persona.setEdad(dto.getEdad());
        persona.setIdentificacion(dto.getIdentificacion());
        persona.setDireccion(dto.getDireccion());
        persona.setTelefono(dto.getTelefono());

        Cliente cliente = new Cliente();
        cliente.setClienteid(dto.getClienteid());
        cliente.setContrasena(dto.getContrasena());
        cliente.setPersona(persona);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        kafkaTemplate.send("cliente-creado", String.valueOf(clienteGuardado.getId()));
        log.info("Se creó cliente y se publicó evento Kafka para crear la cuenta: ID={}", clienteGuardado.getId());

        return clienteGuardado;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente actualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setContrasena(dto.getContrasena());
        cliente.getPersona().setNombre(dto.getNombre());
        cliente.getPersona().setGenero(dto.getGenero());
        cliente.getPersona().setEdad(dto.getEdad());
        cliente.getPersona().setIdentificacion(dto.getIdentificacion());
        cliente.getPersona().setDireccion(dto.getDireccion());
        cliente.getPersona().setTelefono(dto.getTelefono());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }
}
