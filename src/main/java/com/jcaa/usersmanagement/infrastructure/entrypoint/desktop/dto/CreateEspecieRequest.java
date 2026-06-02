package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateEspecieRequest(
    String id,
    String nombre,
    String nombreCientifico,
    String descripcion,
    String habitat) {}
