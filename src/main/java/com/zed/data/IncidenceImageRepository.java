package com.zed.data;

import com.zed.domain.IncidenceImage;
import com.zed.dto.IncidenceDto;
import com.zed.dto.IncidenceImageDto;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Component
public class IncidenceImageRepository {

  private final DataSource ds;

  public IncidenceImageRepository() {

    this.ds = DataSourceSingleton.getInstance();
  }

  public List<IncidenceImage> getIncidenceImageByIdIncidence(String id) {
    QueryRunner run = new QueryRunner(ds);
    try {
      String query = "SELECT * FROM cpl_incidence.incidence_image WHERE id_incidence = '" + id + "';";
      return run.query(query,
              rs -> {
                List<IncidenceImage> newClientsList = new LinkedList<>();
                while (rs.next()){
                  newClientsList.add(new IncidenceImage.Builder()
                          .setId(rs.getString(1))
                          .setUrl(rs.getString(2))
                          .setUrlDisplay(rs.getString(3))
                          .setIdIncidence(rs.getString(4))
                          .setCreatedAt(rs.getString(5))
                          .setUpdatedAt(rs.getString(6))
                          .build());
                }
                return newClientsList;
              });
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean createIncidenceImage(IncidenceImageDto incidenceImageDto) {
    QueryRunner run = new QueryRunner(ds);
    try {
      String query = "INSERT INTO cpl_incidence.incidence_image (" +
              "id, id_incidence, url_download, url_display" +
              ") VALUES (" +
              "'" + incidenceImageDto.getId() + "', '" + incidenceImageDto.getIdIncidence() + "', '" + incidenceImageDto.getUrl() + "', " +
              "'" + incidenceImageDto.getUrlDisplay() + "' " +
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
