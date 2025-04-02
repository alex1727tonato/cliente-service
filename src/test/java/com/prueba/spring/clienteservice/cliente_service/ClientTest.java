package com.prueba.spring.clienteservice.cliente_service;

import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import com.prueba.spring.clienteservice.cliente_service.entity.Persona;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    void testClienteCreation() {
        Persona persona = new Persona();
        persona.setNombre("Alex");
        persona.setEdad(30);

        Cliente cliente = new Cliente();
        cliente.setClienteid("1727alx");
        cliente.setContrasena("1234");
        cliente.setPersona(persona);

        assertEquals("1727alx", cliente.getClienteid());
        assertEquals("1234", cliente.getContrasena());
        assertNotNull(cliente.getPersona());
        assertEquals("Alex", cliente.getPersona().getNombre());
    }
}
