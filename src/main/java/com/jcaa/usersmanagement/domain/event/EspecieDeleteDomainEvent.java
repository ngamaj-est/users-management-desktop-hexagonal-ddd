package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.EspecieId;

import lombok.Getter;

@Getter
public class EspecieDeleteDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "especie.deleted";

  private final EspecieId id;

  public EspecieDeleteDomainEvent(final EspecieId id) {
    super(EVENT_NAME);
    this.id = id;
  }

  @Override
  public java.util.Map<String, String> payload() {
    return java.util.Map.of("id", id.value());
  }

}
