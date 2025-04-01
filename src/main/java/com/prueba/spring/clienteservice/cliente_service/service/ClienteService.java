package com.prueba.spring.clienteservice.cliente_service.service;

import com.prueba.spring.clienteservice.cliente_service.dto.ClienteDTO;
import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import com.prueba.spring.clienteservice.cliente_service.entity.Persona;
import com.prueba.spring.clienteservice.cliente_service.exception.ResourceNotFoundException;
import com.prueba.spring.clienteservice.cliente_service.repository.ClienteRepository;
import com.prueba.spring.clienteservice.cliente_service.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

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

        return clienteRepository.save(cliente);
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
