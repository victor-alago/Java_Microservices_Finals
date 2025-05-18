package titanic.config;

//package com.victor.core;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import titanic.services.PassengerService;
import titanic.services.dao.PassengerDAO;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "titanic")
public class AppConfig {

    @Bean
    public String message() {
        return "Spring Dependency Injection for Titanic!";
    }

    @Bean
    public PassengerService passengerService() {
        return new PassengerService();
    }

    @Bean
    public PassengerDAO passengerDAO(javax.sql.DataSource dataSource) {
        return new PassengerDAO(dataSource);
    }


    @Bean
    public DataSource dataSource() {
        org.springframework.jdbc.datasource.DriverManagerDataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:titanic;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("titanic"); // Scan for entities in this package

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true); // Show SQL queries
        vendorAdapter.setGenerateDdl(true); // Auto-generate schema
        emf.setJpaVendorAdapter(vendorAdapter);

        // Additional Hibernate properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update"); // Create/Update tables automatically
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        emf.setJpaPropertyMap(properties);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}