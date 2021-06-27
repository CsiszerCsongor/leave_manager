package com.leave.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class PostgresqlDatasource {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource postgresDataSource(@Qualifier("postgresDataSourceProperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager(@Qualifier("postgresDataSource") DataSource postgresDataSource,
                                                                        EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return entityManagerFactoryBuilder
                .dataSource(postgresDataSource)
                .packages("com.leave.manager.entity")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager postresqlTransactionManager(EntityManagerFactory postgresEntityManagerFactory){
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }

}