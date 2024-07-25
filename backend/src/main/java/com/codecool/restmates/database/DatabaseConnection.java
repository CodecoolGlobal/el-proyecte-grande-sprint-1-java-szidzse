package com.codecool.restmates.database;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConnection {

    @Value("${restmates.database.url}")
    private String databaseUrl;
    @Value("${restmates.database.username}")
    private String databaseUsername;
    @Value("${restmates.database.password}")
    private String databasePassword;

    public DatabaseConnection() {

    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseName() {
        String[] s = databaseUrl.split("/");
        return s[s.length - 1];
    }


    @Bean
    public DataSource getDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(getDatabaseName());
        dataSource.setUser(getDatabaseUsername());
        dataSource.setPassword(getDatabasePassword());

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection closed successfully.");

        return dataSource;
    }
}
