package com.jcaa.usersmanagement.application.port.out;

import java.util.List;

import com.jcaa.usersmanagement.domain.model.EspecieModel;

public interface GetAllEspeciesPort {
  List<EspecieModel> getAll();
}
