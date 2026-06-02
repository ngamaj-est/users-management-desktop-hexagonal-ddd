package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEspecieCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "nombre must not be blank")
        @Size(min = 2, message = "nombre must have at least 2 characters")
        String nombre,
    @NotBlank(message = "nombreCientifico must not be blank")
        @Size(min = 3, message = "nombreCientifico must have at least 3 characters")
        String nombreCientifico,
    String descripcion,
    String habitat) {
        
    }
