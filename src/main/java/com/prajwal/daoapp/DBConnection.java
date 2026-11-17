package com.prajwal.daoapp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

/** Creates one shared HikariCP connection pool for the application. */
public final class DBConnection {
    private static final HikariDataSource DATA_SOURCE = createDataSource();

    private DBConnection() {}

    private static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getenv().getOrDefault(
                "DB_URL",
                "jdbc:mysql://localhost:3306/dao_demo?useSSL=false&serverTimezone=UTC"));
        config.setUsername(System.getenv().getOrDefault("DB_USER", "root"));
        config.setPassword(System.getenv().getOrDefault("DB_PASSWORD", ""));
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setPoolName("UserDaoPool");
        config.setConnectionTimeout(30000);
        return new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static void close() {
        if (!DATA_SOURCE.isClosed()) {
            DATA_SOURCE.close();
        }
    }
}
