package org.plugmin.core.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

@Profile("test-env")
@Configuration
public class PlugminRemoteConfiguration {


    @Bean
    public AnnotationMBeanExporter annotationMBeanExporter() {
        return new AnnotationMBeanExporter();
    }

}