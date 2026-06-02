package com.jcaa.usersmanagement.application.service;

import java.util.Set;

import com.jcaa.usersmanagement.application.port.in.DeleteEspecieUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteEspeciePort;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteEspecieCommand;
import com.jcaa.usersmanagement.application.service.mapper.EspecieApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EspecieNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteEspecieService implements DeleteEspecieUseCase {

  private final DeleteEspeciePort deleteEspeciePort;
  private final GetEspecieByIdPort getEspecieByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteEspecieCommand command) {
    validateCommand(command);

    final EspecieId especieId =
        EspecieApplicationMapper.fromDeleteCommandToEspecieId(command);
    ensureEspecieExists(especieId);
    deleteEspeciePort.delete(especieId);
  }

  private void validateCommand(final DeleteEspecieCommand command) {
    final Set<ConstraintViolation<DeleteEspecieCommand>> violations =
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
