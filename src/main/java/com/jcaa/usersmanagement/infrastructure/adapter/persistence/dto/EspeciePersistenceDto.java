package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record EspeciePersistenceDto(
    String id,
    String nombre,
    String nombreCientifico,
    String descripcion,
    String habitat,
    String createdAt,
    String updatedAt) {
        
    }
