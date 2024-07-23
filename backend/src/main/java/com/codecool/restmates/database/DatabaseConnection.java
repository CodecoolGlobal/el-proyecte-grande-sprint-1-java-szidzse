package com.codecool.restmates.database;

import com.codecool.restmates.configuration.Configuration;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseConnection {
    private Configuration configuration;

    public DatabaseConnection(Configuration configuration) {
        this.configuration = configuration;
    }

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
