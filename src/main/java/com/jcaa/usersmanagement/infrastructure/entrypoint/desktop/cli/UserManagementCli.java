package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateEspecieHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteEspecieHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindEspecieByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListEspeciesHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateEspecieHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.EspecieResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserManagementCli {

  private static final String BANNER =
      """
      ==========================================
           Users and Species Management System
      ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final UserController userController;
  private final EspecieController especieController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final UserResponsePrinter userPrinter = new UserResponsePrinter(console);
    final EspecieResponsePrinter especiePrinter = new EspecieResponsePrinter(console);
    runLoop(buildHandlers(userPrinter, especiePrinter));
  }

  private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");
      final Optional<MenuOption> option = MenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Invalid option. Please try again.");
      } else if (option.get() == MenuOption.EXIT) {
        console.println("\n  Goodbye!\n");
        running = false;
      } else {
        executeHandler(handlers, option.get());
      }
    }
  }

  private void executeHandler(
      final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
    try {
      handlers.get(option).handle();
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception.getConstraintViolations()
          .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final RuntimeException exception) {
      console.println("  Unexpected error: " + exception.getMessage());
    }
  }

  private Map<MenuOption, OperationHandler> buildHandlers(
      final UserResponsePrinter userPrinter,
      final EspecieResponsePrinter especiePrinter) {
    return Map.ofEntries(
        Map.entry(MenuOption.LIST_USERS,    new ListUsersHandler(userController, userPrinter)),
        Map.entry(MenuOption.FIND_USER,     new FindUserByIdHandler(userController, console, userPrinter)),
        Map.entry(MenuOption.CREATE_USER,   new CreateUserHandler(userController, console, userPrinter)),
        Map.entry(MenuOption.UPDATE_USER,   new UpdateUserHandler(userController, console, userPrinter)),
        Map.entry(MenuOption.DELETE_USER,   new DeleteUserHandler(userController, console)),
        Map.entry(MenuOption.LOGIN,         new LoginHandler(userController, console, userPrinter)),
        Map.entry(MenuOption.LIST_ESPECIES, new ListEspeciesHandler(especieController, especiePrinter)),
        Map.entry(MenuOption.FIND_ESPECIE,  new FindEspecieByIdHandler(especieController, console, especiePrinter)),
        Map.entry(MenuOption.CREATE_ESPECIE,new CreateEspecieHandler(especieController, console, especiePrinter)),
        Map.entry(MenuOption.UPDATE_ESPECIE,new UpdateEspecieHandler(especieController, console, especiePrinter)),
        Map.entry(MenuOption.DELETE_ESPECIE,new DeleteEspecieHandler(especieController, console)));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Main Menu");
    console.println(MENU_BORDER);
    console.println("    Users");
    console.println("    ------------------------------------------");
    for (final MenuOption option : new MenuOption[] {
        MenuOption.LIST_USERS,
        MenuOption.FIND_USER,
        MenuOption.CREATE_USER,
        MenuOption.UPDATE_USER,
        MenuOption.DELETE_USER,
        MenuOption.LOGIN}) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println();
    console.println("    Especies");
    console.println("    ------------------------------------------");
    for (final MenuOption option : new MenuOption[] {
        MenuOption.LIST_ESPECIES,
        MenuOption.FIND_ESPECIE,
        MenuOption.CREATE_ESPECIE,
        MenuOption.UPDATE_ESPECIE,
        MenuOption.DELETE_ESPECIE}) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println();
    console.println("    General");
    console.println("    ------------------------------------------");
    console.printf("    [%d] %s%n", MenuOption.EXIT.getNumber(), MenuOption.EXIT.getDescription());
    console.println(MENU_BORDER);
  }
}