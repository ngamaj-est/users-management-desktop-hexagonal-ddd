package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EspecieResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListEspeciesHandler implements OperationHandler {

  private final EspecieController especieController;
  private final EspecieResponsePrinter printer;

  @Override
  public void handle() {
    final List<EspecieResponse> especies = especieController.listAllEspecies();
    printer.printList(especies);
  }
}
