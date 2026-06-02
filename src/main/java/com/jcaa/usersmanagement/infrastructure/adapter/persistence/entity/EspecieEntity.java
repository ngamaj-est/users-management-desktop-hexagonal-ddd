package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record EspecieEntity(
    String id,
    String nombre,
    String nombreCientifico,
    String descripcion,
    String habitat,
    String createdAt,
    String updatedAt) {}
