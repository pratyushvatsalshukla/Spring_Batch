package com.springBatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
@Configuration
public class DataSourceConfig {
    @Bean
    @Primary // spring batch should use this for metadata-store
    @ConfigurationProperties(prefix = "spring.datasource") // chcek app.props prefixes.
    public DataSource dataSource(){
        // Hae to create bean using this. As we have used custom datasource. Cant use the above one
        return DataSourceBuilder.create().build() ;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.universitydatasource")
    public DataSource universityDataSource(){
        // Hae to create bean using this. As we have used custom datasource. Cant use the above one
        return DataSourceBuilder.create().build() ;
    }
}
