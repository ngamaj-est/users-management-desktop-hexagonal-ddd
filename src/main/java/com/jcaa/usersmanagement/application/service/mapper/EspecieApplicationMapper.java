package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.domain.enums.HabitatEnum;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;
import com.jcaa.usersmanagement.domain.valueobject.NombreEspecie;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EspecieApplicationMapper {

  public EspecieModel fromCreateCommandToModel(final CreateEspecieCommand command) {
    return EspecieModel.create(
        new EspecieId(command.id()),
        new NombreEspecie(command.nombre()),
        new NombreCientifico(command.nombreCientifico()),
        command.descripcion(),
        HabitatEnum.valueOf(command.habitat()));
  }

  public EspecieModel fromUpdateCommandToModel(final UpdateEspecieCommand command) {
    return new EspecieModel(
        new EspecieId(command.id()),
        new NombreEspecie(command.nombre()),
        new NombreCientifico(command.nombreCientifico()),
        command.descripcion(),
        HabitatEnum.valueOf(command.habitat()));
  }

  public EspecieId fromGetByIdQueryToEspecieId(final GetEspecieByIdQuery query) {
    return new EspecieId(query.id());
  }

  public EspecieId fromDeleteCommandToEspecieId(final DeleteEspecieCommand command) {
    return new EspecieId(command.id());
  }
}
