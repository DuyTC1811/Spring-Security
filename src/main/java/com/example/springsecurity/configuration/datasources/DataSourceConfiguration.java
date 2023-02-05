package com.example.springsecurity.configuration.datasources;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
//@EnableJpaRepositories(
////        basePackages = DataSourceConstants.BASE_PACKAGES,
////        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
//        entityManagerFactoryRef = "primaryEntityManagerFactory"
//)
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties("master.datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("master.datasource.hikari")
    public HikariDataSource masterDataSource(DataSourceProperties masterDataSourceProperties) {
        return masterDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("slave.datasource")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("slave.datasource.hikari")
    public HikariDataSource slaveDataSource(DataSourceProperties slaveDataSourceProperties) {
        return slaveDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public TransactionRoutingDataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        TransactionRoutingDataSource routingDataSource = new TransactionRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.READ_WRITE, masterDataSource);
        dataSourceMap.put(DataSourceType.READ_ONLY, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean
    public BeanPostProcessor dialectProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(@Nullable Object bean, @Nullable String beanName) throws BeansException {
                if (bean instanceof HibernateJpaVendorAdapter) {
                    Objects.requireNonNull(((HibernateJpaVendorAdapter) bean).getJpaDialect()).setPrepareConnection(false);
                }
                return bean;
            }
        };
    }
}
