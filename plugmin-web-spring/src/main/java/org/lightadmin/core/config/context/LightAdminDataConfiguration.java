package org.lightadmin.core.config.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("org.plugmin.core.dao")
@EnableTransactionManagement
public class LightAdminDataConfiguration implements TransactionManagementConfigurer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }
}