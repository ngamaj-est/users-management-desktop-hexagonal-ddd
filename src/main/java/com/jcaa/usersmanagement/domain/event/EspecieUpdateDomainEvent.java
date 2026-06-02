package com.jcaa.usersmanagement.domain.event;

import java.util.Map;

import com.jcaa.usersmanagement.domain.model.EspecieModel;

import lombok.Getter;

@Getter
public class EspecieUpdateDomainEvent extends DomainEvent {
    
    private static final String EVENT_NAME = "especie.updated";

  private final EspecieModel especie;

  public EspecieUpdateDomainEvent(final EspecieModel especie) {
    super(EVENT_NAME);
    this.especie = especie;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", especie.getId().value(),
        "name", especie.getNombre().value(),
        "nombreCientifico", especie.getNombreCientifico().value(),
        "description", especie.getDescripcion(),
        "habitat", especie.getHabitat().name());
  }
  
}
