package org.plugmin.core.config.context;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.plugmin.core.config.PlugminConfiguration;
import org.plugmin.core.config.StandardPlugminConfiguration;
import org.plugmin.core.config.context.json.ViewAwareJsonMessageConverter;
import org.plugmin.core.view.PlugminSpringTilesInitializer;
import org.plugmin.core.view.SeparateContainerTilesView;
import org.plugmin.core.web.ApplicationController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;

@Configuration
@ComponentScan("org.plugmin.core.controller")
@Import({
        PlugminDaoConfiguration.class, PlugminServiceConfiguration.class, PlugminRemoteConfiguration.class, PlugminRepositoryRestConfiguration.class, PlugminViewConfiguration.class
})
@EnableWebMvc
public class PlugminContextConfiguration extends WebMvcConfigurerAdapter implements BeanFactoryAware {

    @Autowired(required = false)
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired(required = false)
    private SessionFactory sessionFactory;
    
	private BeanFactory parentBeanFactory;
    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addWebRequestInterceptor(openSessionOrEntityMgrInViewInterceptor());
    }

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/**").addResourceLocations("classpath:/META-INF/resources/styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/META-INF/resources/scripts/");
        registry.addResourceHandler("/kendo-ui/**").addResourceLocations("classpath:/META-INF/resources/kendo-ui/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/META-INF/resources/images/").setCachePeriod(31556926);
    }

    @Bean
    @Autowired
    public PlugminConfiguration plugminConfiguration(ServletContext servletContext) {
        return new StandardPlugminConfiguration(servletContext);
    }

    @Bean
    @Autowired
    public ServletContextResourceLoader servletContextResourceLoader(ServletContext servletContext) {
        return new ServletContextResourceLoader(servletContext);
    }

    @Bean
    public WebRequestInterceptor openSessionOrEntityMgrInViewInterceptor() {
    	WebRequestInterceptor webRequestInterceptor = null;
    	if(entityManagerFactory != null) {
    		OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor = new OpenEntityManagerInViewInterceptor();
            openEntityManagerInViewInterceptor.setEntityManagerFactory(entityManagerFactory);
            webRequestInterceptor = openEntityManagerInViewInterceptor;
    	} else {
    		OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
            openSessionInViewInterceptor.setSessionFactory(sessionFactory);
            webRequestInterceptor = openSessionInViewInterceptor;
    	}
		return webRequestInterceptor;
	}

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        ExceptionHandlerExceptionResolver exceptionHandlerResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerResolver.afterPropertiesSet();

        exceptionResolvers.add(exceptionHandlerResolver);
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        validator.afterPropertiesSet();
        return validator;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public ApplicationController applicationController() {
        return new ApplicationController();
    }

    @Bean
    public ViewResolver viewResolver() {
        final UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(SeparateContainerTilesView.class);
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final String[] definitions = {"classpath*:META-INF/tiles/**/*.xml"};

        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setTilesInitializer(plugminSpringTilesInitializer(definitions));
        configurer.setDefinitions(definitions);
        configurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        configurer.setCheckRefresh(true);
        return configurer;
    }

    private PlugminSpringTilesInitializer plugminSpringTilesInitializer(String[] definitions) {
        final PlugminSpringTilesInitializer plugminSpringTilesInitializer = new PlugminSpringTilesInitializer();
        plugminSpringTilesInitializer.setCheckRefresh(true);
        plugminSpringTilesInitializer.setDefinitions(definitions);
        plugminSpringTilesInitializer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        return plugminSpringTilesInitializer;
    }

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(viewAwareJsonMessageConverter());
	}

	@Bean
	public HttpMessageConverter<?> viewAwareJsonMessageConverter() {
		HttpMessageConverter<?> converter = new ViewAwareJsonMessageConverter();
		return converter;
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		DefaultListableBeanFactory bf = (DefaultListableBeanFactory) beanFactory;
		parentBeanFactory = bf.getParentBeanFactory();
	}

}