package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jcaa.usersmanagement.domain.enums.HabitatEnum;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;
import com.jcaa.usersmanagement.domain.valueobject.NombreEspecie;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EspeciePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.EspecieEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EspeciePersistenceMapper {

  public EspeciePersistenceDto fromModelToDto(final EspecieModel especie) {
    return new EspeciePersistenceDto(
        especie.getId().value(),
        especie.getNombre().value(),
        especie.getNombreCientifico().value(),
        especie.getDescripcion(),
        String.valueOf(especie.getHabitat()),
        null,
        null);
  }

  public EspecieEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new EspecieEntity(
        rs.getString("id"),
        rs.getString("nombre"),
        rs.getString("nombre_cientifico"),
        rs.getString("descripcion"),
        rs.getString("habitat"),
        rs.getString("created_at"),
        rs.getString("updated_at"));
  }

  public EspecieModel fromEntityToModel(final EspecieEntity entity) {
    return new EspecieModel(
        new EspecieId(entity.id()),
        new NombreEspecie(entity.nombre()),
        new NombreCientifico(entity.nombreCientifico()),
        entity.descripcion(),
        HabitatEnum.fromString(entity.habitat()));
  }


  public EspecieModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<EspecieModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<EspecieModel> especies = new ArrayList<>();
    while (rs.next()) {
      especies.add(fromResultSetToModel(rs));
    }
    return especies;
  }
}
