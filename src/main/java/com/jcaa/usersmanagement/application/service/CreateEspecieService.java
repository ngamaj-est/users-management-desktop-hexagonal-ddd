package com.jcaa.usersmanagement.application.service;

import java.util.Set;
import java.util.UUID;

import com.jcaa.usersmanagement.application.port.in.CreateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByNombreCientificoPort;
import com.jcaa.usersmanagement.application.port.out.SaveEspeciePort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateEspecieCommand;
import com.jcaa.usersmanagement.application.service.mapper.EspecieApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EspecieAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class CreateEspecieService implements CreateEspecieUseCase {

  private final SaveEspeciePort saveEspeciePort;
  private final GetEspecieByNombreCientificoPort getEspecieByNombreCientificoPort;
  private final Validator validator;

  @Override
  public EspecieModel execute(final CreateEspecieCommand command) {
    validateCommand(command);

    final NombreCientifico nombreCientifico = new NombreCientifico(command.nombreCientifico());
    ensureNombreCientificoIsNotTaken(nombreCientifico);

    final EspecieModel especieToSave = EspecieApplicationMapper.fromCreateCommandToModel(
        command,
        new EspecieId(UUID.randomUUID().toString()));
    return saveEspeciePort.save(especieToSave);
  }

  private void validateCommand(final CreateEspecieCommand command) {
    final Set<ConstraintViolation<CreateEspecieCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureNombreCientificoIsNotTaken(final NombreCientifico nombreCientifico) {
    getEspecieByNombreCientificoPort
        .getByNombreCientifico(nombreCientifico)
        .ifPresent(
            ignored -> {
              throw EspecieAlreadyExistsException
                  .becauseNombreCientificoAlreadyExists(nombreCientifico.value());
            });
  }
}
