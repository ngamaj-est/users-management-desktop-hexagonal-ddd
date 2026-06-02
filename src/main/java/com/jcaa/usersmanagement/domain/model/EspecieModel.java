package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.HabitatEnum;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;
import com.jcaa.usersmanagement.domain.valueobject.NombreEspecie;

import lombok.Value;

@Value
public class EspecieModel {

    EspecieId id;
    NombreEspecie nombre;
    NombreCientifico nombreCientifico;
    String descripcion;
    HabitatEnum habitat;

    public static EspecieModel create(
            final EspecieId id,
            final NombreEspecie nombre,
            final NombreCientifico nombreCientifico,
            final String descripcion,
            final HabitatEnum habitat) {
        return new EspecieModel(id, nombre, nombreCientifico, descripcion, habitat);
    }
}
