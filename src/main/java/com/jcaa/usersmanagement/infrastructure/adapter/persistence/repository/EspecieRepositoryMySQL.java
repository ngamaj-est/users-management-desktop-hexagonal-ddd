package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jcaa.usersmanagement.application.port.out.DeleteEspeciePort;
import com.jcaa.usersmanagement.application.port.out.GetAllEspeciesPort;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByNombreCientificoPort;
import com.jcaa.usersmanagement.application.port.out.SaveEspeciePort;
import com.jcaa.usersmanagement.application.port.out.UpdateEspeciePort;
import com.jcaa.usersmanagement.domain.exception.EspecieNotFoundException;
import com.jcaa.usersmanagement.domain.model.EspecieModel;
import com.jcaa.usersmanagement.domain.valueobject.EspecieId;
import com.jcaa.usersmanagement.domain.valueobject.NombreCientifico;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EspeciePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.EspeciePersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.EspeciePersistenceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class EspecieRepositoryMySQL
    implements SaveEspeciePort,
               UpdateEspeciePort,
               DeleteEspeciePort,
               GetEspecieByIdPort,
               GetEspecieByNombreCientificoPort,
               GetAllEspeciesPort {

  private static final String SQL_INSERT =
      "INSERT INTO especie (id, nombre, nombre_cientifico, descripcion, habitat, "
      + "created_at, updated_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE especie SET nombre = ?, nombre_cientifico = ?, descripcion = ?, "
      + "habitat = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre, nombre_cientifico, descripcion, habitat, created_at, updated_at "
      + "FROM especie WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_NOMBRE_CIENTIFICO =
      "SELECT id, nombre, nombre_cientifico, descripcion, habitat, created_at, updated_at "
      + "FROM especie WHERE nombre_cientifico = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre, nombre_cientifico, descripcion, habitat, created_at, updated_at "
      + "FROM especie ORDER BY nombre ASC";

  private static final String SQL_DELETE =
      "DELETE FROM especie WHERE id = ?";

  private final Connection connection;

  @Override
  public EspecieModel save(final EspecieModel especie) {
    final EspeciePersistenceDto dto = EspeciePersistenceMapper.fromModelToDto(especie);
    executeSave(dto);
    return findByIdOrFail(especie.getId());
  }

  @Override
  public EspecieModel update(final EspecieModel especie) {
    final EspeciePersistenceDto dto = EspeciePersistenceMapper.fromModelToDto(especie);
    executeUpdate(dto);
    return findByIdOrFail(especie.getId());
  }

  @Override
  public Optional<EspecieModel> getById(final EspecieId especieId) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      stmt.setString(1, especieId.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) {
        return Optional.empty();
      }
      return Optional.of(EspeciePersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseFindByIdFailed(especieId.value(), ex);
    }
  }

  @Override
  public Optional<EspecieModel> getByNombreCientifico(final NombreCientifico nombreCientifico) {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_SELECT_BY_NOMBRE_CIENTIFICO)) {
      stmt.setString(1, nombreCientifico.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) {
        return Optional.empty();
      }
      return Optional.of(EspeciePersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseFindByNombreCientificoFailed(
          nombreCientifico.value(), ex);
    }
  }

  @Override
  public List<EspecieModel> getAll() {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet rs = stmt.executeQuery();
      return EspeciePersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public void delete(final EspecieId especieId) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
      stmt.setString(1, especieId.value());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseDeleteFailed(especieId.value(), ex);
    }
  }

  // ── helpers ────────────────────────────────────────────────────────────────

  private void executeSave(final EspeciePersistenceDto dto) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString(1, dto.id());
      stmt.setString(2, dto.nombre());
      stmt.setString(3, dto.nombreCientifico());
      stmt.setString(4, dto.descripcion());
      stmt.setString(5, dto.habitat());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseSaveFailed(dto.id(), ex);
    }
  }

  private void executeUpdate(final EspeciePersistenceDto dto) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString(1, dto.nombre());
      stmt.setString(2, dto.nombreCientifico());
      stmt.setString(3, dto.descripcion());
      stmt.setString(4, dto.habitat());
      stmt.setString(5, dto.id());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw EspeciePersistenceException.becauseUpdateFailed(dto.id(), ex);
    }
  }

  private EspecieModel findByIdOrFail(final EspecieId especieId) {
    return getById(especieId)
        .orElseThrow(() -> EspecieNotFoundException.becauseIdWasNotFound(especieId.value()));
  }
}
