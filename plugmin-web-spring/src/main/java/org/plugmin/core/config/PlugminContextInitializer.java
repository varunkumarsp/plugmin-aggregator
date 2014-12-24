package org.plugmin.core.config;

import org.plugmin.core.config.context.PlugminContextConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class PlugminContextInitializer implements ApplicationContextInitializer<AnnotationConfigWebApplicationContext> {

    @Override
    public void initialize(AnnotationConfigWebApplicationContext applicationContext) {
        applicationContext.register(PlugminContextConfiguration.class);
    }

}
