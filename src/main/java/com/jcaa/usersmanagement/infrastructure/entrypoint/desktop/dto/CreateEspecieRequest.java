package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateEspecieRequest(
    String nombre,
    String nombreCientifico,
    String descripcion,
    String habitat) {}
