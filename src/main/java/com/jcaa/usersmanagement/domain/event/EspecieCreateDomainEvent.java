package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.EspecieModel;

import java.util.Map;
import lombok.Getter;

@Getter
public final class EspecieCreateDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "especie.created";
    
    private final EspecieModel especie;
    
    public EspecieCreateDomainEvent(final EspecieModel especie) {
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
