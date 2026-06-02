package com.jcaa.usersmanagement.application.service;

import java.util.Set;

import com.jcaa.usersmanagement.application.port.in.UpdateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateEspeciePort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.application.service.mapper.EspecieApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EspecieNotFoundException;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateEspecieService implements UpdateEspecieUseCase {

  private final UpdateEspeciePort updateEspeciePort;
  private final GetEspecieByIdPort getEspecieByIdPort;
  private final Validator validator;

  @Override
  public EspecieModel execute(final UpdateEspecieCommand command) {
    validateCommand(command);

    final EspecieId especieId = new EspecieId(command.id());
    ensureEspecieExists(especieId);

    final EspecieModel especieToUpdate =
        EspecieApplicationMapper.fromUpdateCommandToModel(command);
    return updateEspeciePort.update(especieToUpdate);
  }

  private void validateCommand(final UpdateEspecieCommand command) {
    final Set<ConstraintViolation<UpdateEspecieCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEspecieExists(final EspecieId especieId) {
    getEspecieByIdPort
        .getById(especieId)
        .orElseThrow(() -> EspecieNotFoundException.becauseIdWasNotFound(especieId.value()));
  }
}
