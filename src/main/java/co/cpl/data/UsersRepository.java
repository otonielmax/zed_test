/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.data;

import co.cpl.domain.Users;
import co.cpl.dto.UsersDto;
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

import static javax.swing.text.html.HTML.Tag.I;

@Component
public class UsersRepository {

    private final DataSource ds;

    public UsersRepository() {

        this.ds = DataSourceSingleton.getInstance();
    }

    public Optional<Users> findUserById(String Id) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users WHERE id = '" + Id + "';";
            Optional<Users> users = run.query(query,
                    rs -> {
                        if (!rs.next()) {
                            Optional<Object> empty = Optional.empty();
                            return Optional.empty();
                        }
                        rs.last();
                        return Optional.ofNullable(new Users.Builder()
                                .setId(rs.getString(1))
                                .setDocumentType(rs.getString(2))
                                .setDocumentNumber(rs.getString(3))
                                .setName(rs.getString(4))
                                .setLastName(rs.getString(5))
                                .setCountry(rs.getString(6))
                                .setCity(rs.getString(7))
                                .setImei(rs.getString(8))
                                .setType(rs.getString(9))
                                .setStatus(rs.getString(10))
                                .setPhone(rs.getString(11))
                                .setEmail(rs.getString(12))
                                .setPassword(rs.getString(13))
                                .setCreatedAt(rs.getString(14))
                                .setUpdatedAt(rs.getString(15))
                                .build());
                    });
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Users> getUsers(int limit, int offset) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users LIMIT " + limit + " OFFSET " + offset + ";";
            return run.query(query,
                    rs -> {
                        List<Users> newClientsList = new LinkedList<>();
                        while (rs.next()){
                            newClientsList.add(new Users.Builder()
                                    .setId(rs.getString(1))
                                    .setDocumentType(rs.getString(2))
                                    .setDocumentNumber(rs.getString(3))
                                    .setName(rs.getString(4))
                                    .setLastName(rs.getString(5))
                                    .setCountry(rs.getString(6))
                                    .setCity(rs.getString(7))
                                    .setImei(rs.getString(8))
                                    .setType(rs.getString(9))
                                    .setStatus(rs.getString(10))
                                    .setPhone(rs.getString(11))
                                    .setEmail(rs.getString(12))
                                    .setPassword(rs.getString(13))
                                    .setCreatedAt(rs.getString(14))
                                    .setUpdatedAt(rs.getString(15))
                                    .build());
                        }
                        return newClientsList;
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean saveUser(UsersDto usersDto) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "INSERT INTO cpl_users.users (" +
                        "name, last_name, phone, email, document_type, document_number, country, city, imei, type, status, id, password" +
                    ") VALUES (" +
                        "'" + usersDto.getName() + "', '" + usersDto.getLast_name() + "', '" + usersDto.getPhone() + "', '" + usersDto.getEmail() + "', " +
                        "'" + usersDto.getDocument_type() + "', '" + usersDto.getDocument_number() + "', '" + usersDto.getCountry() + "', " +
                        "'" + usersDto.getCity() + "', '" + usersDto.getImei() + "', '" + usersDto.getType() + "', '" + usersDto.getStatus() + "', " +
                        "'" + usersDto.getId() + "', '" + usersDto.getPassword() + "' " +
                    ");";

            int process = run.update(ds.getConnection(), query);
            System.out.println(process);
            return process > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean updateUser(UsersDto usersDto) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "UPDATE cpl_users.users " +
                    "SET imei = '" + usersDto.getImei() + "', status = '" + usersDto.getStatus() + "' " +
                    "WHERE id = '" + usersDto.getId() + "'";

            int process = run.update(ds.getConnection(), query);
            System.out.println(process);
            return process > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String userId) {
        try {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            try {
                String delete = "DELETE FROM cpl_users.users " +
                        "WHERE " +
                        "id = '" + userId + "';";
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

    public Optional<Users> login(UsersDto usersDto) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users " +
                    "WHERE email = '" + usersDto.getEmail() + "' AND password = '" + usersDto.getPassword() + "';";
            Optional<Users> users = run.query(query,
                    rs -> {
                        if (!rs.next()) {
                            Optional<Object> empty = Optional.empty();
                            return Optional.empty();
                        }
                        rs.last();
                        return Optional.ofNullable(new Users.Builder()
                                .setId(rs.getString(1))
                                .setDocumentType(rs.getString(2))
                                .setDocumentNumber(rs.getString(3))
                                .setName(rs.getString(4))
                                .setLastName(rs.getString(5))
                                .setCountry(rs.getString(6))
                                .setCity(rs.getString(7))
                                .setImei(rs.getString(8))
                                .setType(rs.getString(9))
                                .setStatus(rs.getString(10))
                                .setPhone(rs.getString(11))
                                .setEmail(rs.getString(12))
                                .setPassword(rs.getString(13))
                                .setCreatedAt(rs.getString(14))
                                .setUpdatedAt(rs.getString(15))
                                .build());
                    });
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
