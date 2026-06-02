package com.jcaa.usersmanagement.infrastructure.adapter.persistence.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseInitializer {

  public static void initialize(final Connection connection) {
    try {
      if (!tableExists(connection, "especie")) {
        createEspecieTable(connection);
        migrateLegacySpeciesTable(connection);
      }
    } catch (final SQLException ex) {
      throw PersistenceException.becauseConnectionFailed(ex);
    }
  }

  private static boolean tableExists(final Connection connection, final String tableName)
      throws SQLException {
    final DatabaseMetaData metaData = connection.getMetaData();
    try (final ResultSet tables =
        metaData.getTables(null, null, tableName, new String[] {"TABLE"})) {
      return tables.next();
    }
  }

  private static void createEspecieTable(final Connection connection) throws SQLException {
    try (final Statement statement = connection.createStatement()) {
      statement.executeUpdate(
          "CREATE TABLE IF NOT EXISTS especie ("
              + "id VARCHAR(36) NOT NULL PRIMARY KEY, "
              + "nombre VARCHAR(100) NOT NULL, "
              + "nombre_cientifico VARCHAR(150) NOT NULL UNIQUE, "
              + "descripcion TEXT, "
              + "habitat ENUM('SABANA', 'DESIERTO', 'BOSQUE', 'SELVA') NOT NULL, "
              + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
              + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)"
      );
    }
  }

  private static void migrateLegacySpeciesTable(final Connection connection) throws SQLException {
    if (!tableExists(connection, "species")) {
      return;
    }

    final String copySql =
        "INSERT INTO especie (id, nombre, nombre_cientifico, descripcion, habitat) "
            + "SELECT id, name, scientific_name, description, habitat FROM species";

    try (final PreparedStatement statement = connection.prepareStatement(copySql)) {
      statement.executeUpdate();
    }
  }
}
