package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EspecieModel;

public interface SaveEspeciePort {
  EspecieModel save(EspecieModel especie);
}
