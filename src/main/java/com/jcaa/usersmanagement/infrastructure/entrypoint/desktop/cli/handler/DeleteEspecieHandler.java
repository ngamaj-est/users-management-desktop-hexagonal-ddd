package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteEspecieHandler implements OperationHandler {

  private final EspecieController especieController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("ID                              : ");
    especieController.deleteEspecie(id);
    console.println("\n  Species deleted successfully.");
  }
}
