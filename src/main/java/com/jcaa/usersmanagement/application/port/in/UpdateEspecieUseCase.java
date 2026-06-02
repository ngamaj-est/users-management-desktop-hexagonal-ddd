package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateEspecieCommand;
import com.jcaa.usersmanagement.domain.model.EspecieModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateEspecieUseCase {
  EspecieModel execute(@NotNull @Valid UpdateEspecieCommand command);
}
