package com.jcaa.usersmanagement.application.port.in;

import java.util.List;

import com.jcaa.usersmanagement.domain.model.EspecieModel;

public interface GetAllEspeciesUseCase {
  List<EspecieModel> execute();
}
