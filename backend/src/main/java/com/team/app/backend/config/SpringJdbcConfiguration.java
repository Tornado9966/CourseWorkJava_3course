package com.team.app.backend.config;

import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.team.app.backend")
public class SpringJdbcConfiguration {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-246-90-10.eu-west-1.compute.amazonaws.com:5432/davi2lia2gj3nk");
        dataSource.setUsername("rouzraiivzxbfi");
        dataSource.setPassword("8b80bb45c883504c162b2174e688129bcf5d4b0ec5d3bc2166aed7b4c0843fc5");
        return dataSource;
    }
    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl(getDataSource());
    }
}
