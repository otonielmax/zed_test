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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class DataSourceSingleton {

    private static DataSource instance = null;

    public DataSourceSingleton() {}

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            HikariConfig config = new HikariConfig();

            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setJdbcUrl(Optional.ofNullable(System.getenv("USERS_DB_URL"))
                    //.orElse("jdbc:mysql://localhost:3306/cpl_incidence"));
                    .orElse("jdbc:mysql://104.197.233.102:3306/cpl_incidence"));
            config.setUsername(Optional.ofNullable(
                    System.getenv("OPERATOR_JDBC_USERNAME"))
                    .orElse("incidencias"));
            //System.getenv("USERS_JDBC_USERNAME"))
            //.orElse("cpluser"));
            config.setPassword(Optional.ofNullable(
                    System.getenv("OPERATOR_JDBC_PASSWORD"))
                    .orElse("incidencias"));
            //System.getenv("USERS_JDBC_PASSWORD"))
            //.orElse("cplpassword"));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.addDataSourceProperty("characterEncoding", "UTF-8");
            config.addDataSourceProperty("useUnicode", "true");
            config.addDataSourceProperty("serverTimezone", "UTC");

            instance = new HikariDataSource(config);

            Flyway flyway = new Flyway();
            flyway.setDataSource(instance);
            flyway.setLocations("classpath:db/migration/users");
            flyway.migrate();
        }
        return instance;
    }
}

