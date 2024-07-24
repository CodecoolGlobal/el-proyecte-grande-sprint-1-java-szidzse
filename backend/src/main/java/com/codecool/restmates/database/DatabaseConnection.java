package com.codecool.restmates.database;

import com.codecool.restmates.configuration.Configuration;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@org.springframework.context.annotation.Configuration
public class DatabaseConnection {
    private Configuration configuration;

    public DatabaseConnection(Configuration configuration) {
        this.configuration = configuration;
    }

    @Bean
    public DataSource getDataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(configuration.getDatabaseName());
        dataSource.setUser(configuration.getDatabaseUsername());
        dataSource.setPassword(configuration.getDatabasePassword());

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection closed successfully.");

        return dataSource;
    }
}
