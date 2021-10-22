package com.epam.esm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ProdConfiguration {
    private final Environment environment;

    private final String DATABASE_URL = "database.url";
    private final String DATABASE_USERNAME = "database.username";
    private final String DATABASE_PASSWORD = "database.password";
    private final String DATABASE_DRIVER = "database.driver";

    @Autowired
    public ProdConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty(DATABASE_DRIVER));
        dataSource.setUrl(environment.getProperty(DATABASE_URL));
        dataSource.setUsername(environment.getProperty(DATABASE_USERNAME));
        dataSource.setPassword(environment.getProperty(DATABASE_PASSWORD));

        return dataSource;
    }
}
