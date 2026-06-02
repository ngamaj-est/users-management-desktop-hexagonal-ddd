package com.jcaa.usersmanagement.infrastructure.config;

import java.sql.Connection;

import com.jcaa.usersmanagement.application.port.in.CreateEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEspecieUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllEspeciesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetEspecieByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEspecieUseCase;
import com.jcaa.usersmanagement.application.service.CreateEspecieService;
import com.jcaa.usersmanagement.application.service.DeleteEspecieService;
import com.jcaa.usersmanagement.application.service.GetAllEspeciesService;
import com.jcaa.usersmanagement.application.service.GetEspecieByIdService;
import com.jcaa.usersmanagement.application.service.UpdateEspecieService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.EspecieRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EspecieController;

import jakarta.validation.Validator;

public final class EspecieDependencyContainer {

  private static final String DB_HOST     = "db.host";
  private static final String DB_PORT     = "db.port";
  private static final String DB_NAME     = "db.name";
  private static final String DB_USER     = "db.username";
  private static final String DB_PASSWORD = "db.password";

  private final EspecieController especieController;

  public EspecieDependencyContainer() {
    final AppProperties properties = new AppProperties();
    final Connection connection    = buildDatabaseConnection(properties);
    final Validator validator      = ValidatorProvider.buildValidator();

    final EspecieRepositoryMySQL especieRepository =
        new EspecieRepositoryMySQL(connection);

    final CreateEspecieUseCase createEspecieUseCase =
        new CreateEspecieService(especieRepository, especieRepository, validator);

    final UpdateEspecieUseCase updateEspecieUseCase =
        new UpdateEspecieService(especieRepository, especieRepository, validator);

    final DeleteEspecieUseCase deleteEspecieUseCase =
        new DeleteEspecieService(especieRepository, especieRepository, validator);

    final GetEspecieByIdUseCase getEspecieByIdUseCase =
        new GetEspecieByIdService(especieRepository, validator);

    final GetAllEspeciesUseCase getAllEspeciesUseCase =
        new GetAllEspeciesService(especieRepository);

    this.especieController = new EspecieController(
        createEspecieUseCase,
        updateEspecieUseCase,
        deleteEspecieUseCase,
        getEspecieByIdUseCase,
        getAllEspeciesUseCase);
  }

  public EspecieController especieController() {
    return especieController;
  }

  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config = new DatabaseConfig(
        properties.get(DB_HOST),
        properties.getInt(DB_PORT),
        properties.get(DB_NAME),
        properties.get(DB_USER),
        properties.get(DB_PASSWORD));
    return DatabaseConnectionFactory.createConnection(config);
  }
}
