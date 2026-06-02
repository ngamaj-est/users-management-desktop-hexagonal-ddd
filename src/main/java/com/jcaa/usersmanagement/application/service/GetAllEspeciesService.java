package com.jcaa.usersmanagement.application.service;

import java.util.List;

import com.jcaa.usersmanagement.application.port.in.GetAllEspeciesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllEspeciesPort;
import com.jcaa.usersmanagement.domain.model.EspecieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllEspeciesService implements GetAllEspeciesUseCase {

  private final GetAllEspeciesPort getAllEspeciesPort;

  @Override
  public List<EspecieModel> execute() {
    return getAllEspeciesPort.getAll();
  }
}
