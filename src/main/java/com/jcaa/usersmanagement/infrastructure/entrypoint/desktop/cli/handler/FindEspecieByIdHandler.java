package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EspecieResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindEspecieByIdHandler implements OperationHandler {

  private final EspecieController especieController;
  private final ConsoleIO console;
  private final EspecieResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("ID                              : ");
    final EspecieResponse especie = especieController.findEspecieById(id);
    printer.print(especie);
  }
}
