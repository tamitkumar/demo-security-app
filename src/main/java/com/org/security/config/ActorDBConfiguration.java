package com.org.security.config;

import com.org.security.utils.ActorConstant;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.org.security.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "platformTransactionManager")
public class ActorDBConfiguration {

    private final DBConfig dbConfig;

    public ActorDBConfiguration(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dbConfig.dataSource());
        factoryBean.setPackagesToScan(new String [] {"com.org.security.entity"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put(ActorConstant.DIALECT_KEY, ActorConstant.DIALECT_VALUE);
        properties.put("hibernate.dialect", "com.org.security.config.DialectConfig");
        properties.put(ActorConstant.SHOW_SQL_KEY, ActorConstant.SHOW_SQL_VALUE);
        properties.put(ActorConstant.FORMAT_SQL_KEY, ActorConstant.FORMAT_SQL_VALUE);
        properties.put("spring.jpa.hibernate.ddl-auto", "create");
        return properties;
    }
}
