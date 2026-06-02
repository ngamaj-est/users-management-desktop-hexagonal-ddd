package com.jcaa.usersmanagement.application.service;

import java.util.Set;

import com.jcaa.usersmanagement.application.port.in.GetEspecieByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetEspecieByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.EspecieApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.EspecieNotFoundException;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetEspecieByIdService implements GetEspecieByIdUseCase {

  private final GetEspecieByIdPort getEspecieByIdPort;
  private final Validator validator;

  @Override
  public EspecieModel execute(final GetEspecieByIdQuery query) {
    validateQuery(query);

    final EspecieId especieId =
        EspecieApplicationMapper.fromGetByIdQueryToEspecieId(query);
    return getEspecieByIdPort
        .getById(especieId)
        .orElseThrow(() -> EspecieNotFoundException.becauseIdWasNotFound(especieId.value()));
  }

  private void validateQuery(final GetEspecieByIdQuery query) {
    final Set<ConstraintViolation<GetEspecieByIdQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
