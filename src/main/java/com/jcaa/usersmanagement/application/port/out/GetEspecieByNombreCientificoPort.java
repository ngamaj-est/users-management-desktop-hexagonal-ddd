package com.jcaa.usersmanagement.application.port.out;

import java.util.Optional;

import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;

public interface GetEspecieByNombreCientificoPort {
  Optional<EspecieModel> getByNombreCientifico(NombreCientifico nombreCientifico);
}
