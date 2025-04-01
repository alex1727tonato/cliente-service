package com.prueba.spring.clienteservice.cliente_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    @NotBlank
    private String clienteid;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String nombre;

    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
