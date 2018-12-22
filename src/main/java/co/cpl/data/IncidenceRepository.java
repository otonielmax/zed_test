package co.cpl.data;

import co.cpl.domain.Incidence;
import co.cpl.domain.Incidence;
import co.cpl.dto.IncidenceDto;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class IncidenceRepository {

    private final DataSource ds;

    public IncidenceRepository() {

        this.ds = DataSourceSingleton.getInstance();
    }

    public Optional<Incidence> findIncidenceById(String Id) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_incidence.incidence WHERE id = '" + Id + "';";
            Optional<Incidence> incidence = run.query(query,
                    rs -> {
                        if (!rs.next()) {
                            Optional<Object> empty = Optional.empty();
                            return Optional.empty();
                        }
                        rs.last();
                        return Optional.ofNullable(new Incidence.Builder()
                                .setId(rs.getString(1))
                                .setTitle(rs.getString(2))
                                .setDescription(rs.getString(3))
                                .setPlaca(rs.getString(4))
                                .setDateDevice(rs.getString(5))
                                .setDateUser(rs.getString(6))
                                .setDirectionGPS(rs.getString(7))
                                .setDirectionUser(rs.getString(8))
                                .setStatus(rs.getString(9))
                                .setIdUser(rs.getString(10))
                                .setCreatedAt(rs.getString(11))
                                .setUpdatedAt(rs.getString(12))
                                .build());
                    });
            return incidence;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Incidence> findIncidenceByIdUser(String id) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_incidence.incidence WHERE id_user = '" + id + "';";
            return run.query(query,
                    rs -> {
                        List<Incidence> newClientsList = new LinkedList<>();
                        while (rs.next()){
                            newClientsList.add(new Incidence.Builder()
                                    .setId(rs.getString(1))
                                    .setTitle(rs.getString(2))
                                    .setDescription(rs.getString(3))
                                    .setPlaca(rs.getString(4))
                                    .setDateDevice(rs.getString(5))
                                    .setDateUser(rs.getString(6))
                                    .setDirectionGPS(rs.getString(7))
                                    .setDirectionUser(rs.getString(8))
                                    .setStatus(rs.getString(9))
                                    .setIdUser(rs.getString(10))
                                    .setCreatedAt(rs.getString(11))
                                    .setUpdatedAt(rs.getString(12))
                                    .build());
                        }
                        return newClientsList;
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Incidence> getIncidences(int limit, int offset) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_incidence.incidence LIMIT " + limit + " OFFSET " + offset + ";";
            return run.query(query,
                    rs -> {
                        List<Incidence> newClientsList = new LinkedList<>();
                        while (rs.next()){
                            newClientsList.add(new Incidence.Builder()
                                    .setId(rs.getString(1))
                                    .setTitle(rs.getString(2))
                                    .setDescription(rs.getString(3))
                                    .setPlaca(rs.getString(4))
                                    .setDateDevice(rs.getString(5))
                                    .setDateUser(rs.getString(6))
                                    .setDirectionGPS(rs.getString(7))
                                    .setDirectionUser(rs.getString(8))
                                    .setStatus(rs.getString(9))
                                    .setIdUser(rs.getString(10))
                                    .setCreatedAt(rs.getString(11))
                                    .setUpdatedAt(rs.getString(12))
                                    .build());
                        }
                        return newClientsList;
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  public List<Incidence> getLastIdIncidence() {
    QueryRunner run = new QueryRunner(ds);
    try {
      String query = "SELECT * FROM cpl_incidence.incidence ORDER BY create_date DESC LIMIT 1;";
      return run.query(query,
              rs -> {
                List<Incidence> newClientsList = new LinkedList<>();
                while (rs.next()){
                  newClientsList.add(new Incidence.Builder()
                          .setId(rs.getString(1))
                          .setTitle(rs.getString(2))
                          .setDescription(rs.getString(3))
                          .setPlaca(rs.getString(4))
                          .setDateDevice(rs.getString(5))
                          .setDateUser(rs.getString(6))
                          .setDirectionGPS(rs.getString(7))
                          .setDirectionUser(rs.getString(8))
                          .setStatus(rs.getString(9))
                          .setIdUser(rs.getString(10))
                          .setCreatedAt(rs.getString(11))
                          .setUpdatedAt(rs.getString(12))
                          .build());
                }
                return newClientsList;
              });
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

    public Boolean createIncidence(IncidenceDto IncidenceDto) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "INSERT INTO cpl_incidence.incidence (" +
                    "id, title, description, placa, date_device, date_user, direction_gps, direction_user, status, id_user, type" +
                    ") VALUES (" +
                    "'" + UUID.randomUUID().toString() + "', '" + IncidenceDto.getTitle() + "', '" + IncidenceDto.getDescription() + "', '" + IncidenceDto.getPlaca() + "', " +
                    "'" + IncidenceDto.getDateDevice() + "', " + "'" + IncidenceDto.getDateUser() + "', '" + IncidenceDto.getDirectionGps() + "', " +
                    "'" + IncidenceDto.getDirectionUser() + "', '" + IncidenceDto.getStatus() + "', '" + IncidenceDto.getIdUser() + "', '" + IncidenceDto.getType() + "' " +
                    ");";

            int process = run.update(ds.getConnection(), query);
            return process > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean updateIncicence(IncidenceDto IncidenceDto) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "UPDATE cpl_incidence.incidence " +
                    "SET status = '" + IncidenceDto.getStatus() + "' " +
                    "WHERE id = '" + IncidenceDto.getId() + "'";

            int process = run.update(ds.getConnection(), query);
            System.out.println(process);
            return process > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteIncidence(String incidenceId) {
        try {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            try {
                String delete = "DELETE FROM cpl_incidence.incidence " +
                        "WHERE " +
                        "id = '" + incidenceId + "';";
                stmt.execute(delete);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            } finally {
                DbUtils.close(conn);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
