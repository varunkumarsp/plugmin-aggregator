package org.lightadmin.core.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

@Configuration
public class LightAdminRepositoryRestConfiguration {

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public ServletResponseMethodArgumentResolver servletResponseMethodArgumentResolver() {
        return new ServletResponseMethodArgumentResolver();
    }

}