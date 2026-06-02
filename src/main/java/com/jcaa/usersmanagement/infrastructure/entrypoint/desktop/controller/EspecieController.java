package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import java.util.List;

import com.jcaa.usersmanagement.application.port.in.CreateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllEspeciesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetEspecieByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEspecieUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateEspecieRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEspecieRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.EspecieDesktopMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class EspecieController {

  private final CreateEspecieUseCase createEspecieUseCase;
  private final UpdateEspecieUseCase updateEspecieUseCase;
  private final DeleteEspecieUseCase deleteEspecieUseCase;
  private final GetEspecieByIdUseCase getEspecieByIdUseCase;
  private final GetAllEspeciesUseCase getAllEspeciesUseCase;

  public List<EspecieResponse> listAllEspecies() {
    final var especies = getAllEspeciesUseCase.execute();
    return EspecieDesktopMapper.toResponseList(especies);
  }

  public EspecieResponse findEspecieById(final String id) {
    final var query = EspecieDesktopMapper.toGetByIdQuery(id);
    final var especie = getEspecieByIdUseCase.execute(query);
    return EspecieDesktopMapper.toResponse(especie);
  }

  public EspecieResponse createEspecie(final CreateEspecieRequest request) {
    final var command = EspecieDesktopMapper.toCreateCommand(request);
    final var especie = createEspecieUseCase.execute(command);
    return EspecieDesktopMapper.toResponse(especie);
  }

  public EspecieResponse updateEspecie(final UpdateEspecieRequest request) {
    final var command = EspecieDesktopMapper.toUpdateCommand(request);
    final var especie = updateEspecieUseCase.execute(command);
    return EspecieDesktopMapper.toResponse(especie);
  }

  public void deleteEspecie(final String id) {
    final var command = EspecieDesktopMapper.toDeleteCommand(id);
    deleteEspecieUseCase.execute(command);
  }
}
