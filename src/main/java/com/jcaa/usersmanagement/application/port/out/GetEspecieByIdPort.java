package com.jcaa.usersmanagement.application.port.out;

import java.util.Optional;

import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

public interface GetEspecieByIdPort {
  Optional<EspecieModel> getById(EspecieId especieId);
}
