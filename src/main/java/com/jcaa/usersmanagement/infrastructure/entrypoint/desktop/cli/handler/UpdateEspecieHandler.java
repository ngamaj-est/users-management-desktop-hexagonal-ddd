package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EspecieResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateEspecieRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateEspecieHandler implements OperationHandler {

  private final EspecieController especieController;
  private final ConsoleIO console;
  private final EspecieResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("ID                              : ");
    final String nombre = console.readRequired("Nombre                          : ");
    final String nombreCientifico = console.readRequired("Nombre Cientifico               : ");
    final String descripcion = console.readRequired("Descripcion                     : ");
    final String habitat = console.readRequired("Habitat                         : ");

    final EspecieResponse updated = especieController.updateEspecie(
        new UpdateEspecieRequest(id, nombre, nombreCientifico, descripcion, habitat));

    console.println("\n  Species updated successfully.");
    printer.print(updated);
  }
}
