package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

public interface DeleteEspeciePort {
  void delete(EspecieId especieId);
}
