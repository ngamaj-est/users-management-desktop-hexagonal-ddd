-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios Y Especies de Zoologico - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios_y_especies
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios_y_especies;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
);

-- Tabla de especies del zoológico -- 
CREATE TABLE IF NOT EXISTS species (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    spanish_name VARCHAR(100) NOT NULL,
    scientific_name VARCHAR(150) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- test data for species -- 
INSERT INTO species (spanish_name, scientific_name, description) VALUES
('León', 'Panthera leo', 'Gran felino africano, conocido como el rey de la selva'),
('Elefante Africano', 'Loxodonta africana', 'El mamífero terrestre más grande de África'),
('Jirafa', 'Giraffa camelopardalis', 'El animal más alto del planeta'),
('Tigre de Bengala', 'Panthera tigris tigris', 'Felino rayado originario de la India'),
('Pingüino Rey', 'Aptenodytes patagonicus', 'Ave marina no voladora de regiones subantárticas');

