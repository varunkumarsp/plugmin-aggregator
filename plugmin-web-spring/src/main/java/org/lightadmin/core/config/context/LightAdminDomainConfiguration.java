package org.lightadmin.core.config.context;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.lightadmin.core.config.LightAdminConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.ServletContextResourceLoader;

@Configuration
@ComponentScan("org.plugmin.core.service")
public class LightAdminDomainConfiguration {

    @Autowired
    private Environment environment;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServletContextResourceLoader servletContextResourceLoader;

    @Autowired
    private LightAdminConfiguration lightAdminConfiguration;

}
