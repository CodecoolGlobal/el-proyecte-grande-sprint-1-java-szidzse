package com.codecool.restmates.configuration;


import com.codecool.restmates.database.DatabaseConnection;
import com.codecool.restmates.service.DAO.MemberDAO;
import com.codecool.restmates.service.DAO.MemberDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootConfiguration
public class Configuration {

    private DatabaseConnection databaseConnection;

    @Value("${restmates.database.url}")
    private String databaseUrl;
    @Value("${restmates.database.username}")
    private String databaseUsername;
    @Value("${restmates.database.password}")
    private String databasePassword;

    public Configuration() {
        this.databaseConnection = new DatabaseConnection(this);
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
    public MemberDAO memberDAO() throws SQLException {
        return new MemberDaoImpl(databaseConnection.getDataSource());
    }

}
