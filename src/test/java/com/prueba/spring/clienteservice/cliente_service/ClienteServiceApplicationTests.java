package com.prueba.spring.clienteservice.cliente_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.spring.clienteservice.cliente_service.entity.Cliente;
import com.prueba.spring.clienteservice.cliente_service.entity.Persona;
import com.prueba.spring.clienteservice.cliente_service.repository.ClienteRepository;
import com.prueba.spring.clienteservice.cliente_service.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceApplicationTests {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void crearCliente_DeberiaGuardarClienteEnBD() {
		Persona persona = new Persona();
		persona.setNombre("Alex");
		persona.setGenero("M");
		persona.setEdad(30);
		persona.setIdentificacion("1727alex");
		persona.setDireccion("Quito");
		persona.setTelefono("0987654321");

		Cliente cliente = new Cliente();
		cliente.setClienteid("test-cliente-001");
		cliente.setContrasena("1234");
		cliente.setPersona(persona);

		Cliente guardado = clienteRepository.save(cliente);

		assertNotNull(guardado.getId());
		assertEquals("Alex", guardado.getPersona().getNombre());
	}

}
