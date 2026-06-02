package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.EspecieResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class EspecieResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-18s : %s%n";

  private final ConsoleIO console;

  public void print(final EspecieResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID", response.id());
    console.printf(ROW_FORMAT, "Nombre", response.nombre());
    console.printf(ROW_FORMAT, "Nombre Cientifico", response.nombreCientifico());
    console.printf(ROW_FORMAT, "Descripcion", response.descripcion());
    console.printf(ROW_FORMAT, "Habitat", response.habitat());
    console.println(SEPARATOR);
  }

  public void printList(final List<EspecieResponse> especies) {
    if (especies.isEmpty()) {
      console.println("  No especies found.");
      return;
    }
    console.printf("%n  Total: %d especie(s)%n", especies.size());
    especies.forEach(this::print);
  }
}
