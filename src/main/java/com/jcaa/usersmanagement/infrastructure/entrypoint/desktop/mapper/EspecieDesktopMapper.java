package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import java.util.List;

import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateEspecieRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEspecieRequest;

public final class EspecieDesktopMapper {

  private EspecieDesktopMapper() {}

  public static CreateEspecieCommand toCreateCommand(final CreateEspecieRequest request) {
    return new CreateEspecieCommand(
        request.id(),
        request.nombre(),
        request.nombreCientifico(),
        request.descripcion(),
        request.habitat());
  }

  public static UpdateEspecieCommand toUpdateCommand(final UpdateEspecieRequest request) {
    return new UpdateEspecieCommand(
        request.id(),
        request.nombre(),
        request.nombreCientifico(),
        request.descripcion(),
        request.habitat());
  }

  public static DeleteEspecieCommand toDeleteCommand(final String id) {
    return new DeleteEspecieCommand(id);
  }

  public static GetEspecieByIdQuery toGetByIdQuery(final String id) {
    return new GetEspecieByIdQuery(id);
  }

  public static EspecieResponse toResponse(final EspecieModel especie) {
    return new EspecieResponse(
        especie.getId().value(),
        especie.getNombre().value(),
        especie.getNombreCientifico().value(),
        especie.getDescripcion(),
        String.valueOf(especie.getHabitat())
    );
  }

  public static List<EspecieResponse> toResponseList(final List<EspecieModel> especies) {
    return especies.stream().map(EspecieDesktopMapper::toResponse).toList();
  }
}
