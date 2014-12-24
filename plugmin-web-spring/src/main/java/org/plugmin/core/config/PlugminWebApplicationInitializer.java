package org.plugmin.core.config;

import static org.istage.util.AdminUtil.PLUGMIN_REST_BASE_URL;
import net.sf.ehcache.constructs.web.filter.GzipFilter;

import org.apache.commons.lang3.BooleanUtils;
import org.plugmin.core.config.context.PlugminContextConfiguration;
import org.plugmin.core.config.context.PlugminSecurityConfiguration;
import org.plugmin.core.view.TilesContainerEnrichmentFilter;
import org.plugmin.core.web.DispatcherRedirectorServlet;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ResourceServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import java.io.File;
import java.util.regex.Pattern;

import static org.apache.commons.io.FileUtils.getFile;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.plugmin.core.util.PlugminConfigurationUtils.*;
import static org.plugmin.core.web.util.WebContextUtils.servletContextAttributeName;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Order(LOWEST_PRECEDENCE)
public class PlugminWebApplicationInitializer implements WebApplicationInitializer {

    private static final Pattern BASE_URL_PATTERN = Pattern.compile("(/)|(/[\\w-]+)+");

    private static final String CHARSET_ENCODING = "UTF-8";
    
    public static String PLUGMIN_BASE_URL = "/admin";

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        if (plugminConfigurationDisabled(servletContext)) {
            servletContext.log("Plugmin Web Administration Module is disabled. Skipping.");
            return;
        }

        if (notValidBaseUrl(plugminBaseUrl(servletContext))) {
            servletContext.log("Plugmin Web Administration Module's 'baseUrl' property must match " + BASE_URL_PATTERN.pattern() + " pattern. Skipping.");
            return;
        }

        if (notValidFileStorageDirectoryDefined(servletContext)) {
            servletContext.log("Plugmin Web Administration Module's global file storage directory doesn't exist or not a directory.");
            return;
        }
        
        PLUGMIN_BASE_URL = plugminBaseUrl(servletContext);

        registerCusomResourceServlet(servletContext);

        registerPlugminDispatcher(servletContext);

        if (notRootUrl(plugminBaseUrl(servletContext))) {
            registerPlugminDispatcherRedirector(servletContext);
        }

        registerHiddenHttpMethodFilter(servletContext);

        if (plugminSecurityEnabled(servletContext)) {
            registerSpringSecurityFilter(servletContext);
        }

        registerCharsetFilter(servletContext);

        registerTilesDecorationFilter(servletContext);

        registerGZipFilter(servletContext,
                resourceMapping("/styles/*", servletContext),
                resourceMapping("/scripts/*", servletContext),
                resourceMapping("/kendo-ui/*", servletContext)
        );
    }

    private void registerPlugminDispatcher(final ServletContext servletContext) {
        final AnnotationConfigWebApplicationContext webApplicationContext = plugminApplicationContext(servletContext);

        final DispatcherServlet plugminDispatcher = new DispatcherServlet(webApplicationContext);

        ServletRegistration.Dynamic plugminDispatcherRegistration = servletContext.addServlet(PLUGMIN_ADMIN_DISPATCHER_NAME, plugminDispatcher);
        plugminDispatcherRegistration.setLoadOnStartup(3);
        String baseUrlMapping = dispatcherUrlMapping(plugminBaseUrl(servletContext));
        String restUrlMapping = dispatcherUrlMapping(PLUGMIN_REST_BASE_URL);
		plugminDispatcherRegistration.addMapping(baseUrlMapping, restUrlMapping);
    }

    private void registerPlugminDispatcherRedirector(final ServletContext servletContext) {
        final DispatcherRedirectorServlet handlerServlet = new DispatcherRedirectorServlet();

        ServletRegistration.Dynamic plugminDispatcherRedirectorRegistration = servletContext.addServlet(PLUGMIN_ADMIN_DISPATCHER_REDIRECTOR_NAME, handlerServlet);
        plugminDispatcherRedirectorRegistration.setLoadOnStartup(4);
        plugminDispatcherRedirectorRegistration.addMapping(plugminBaseUrl(servletContext));
    }

    private void registerCusomResourceServlet(final ServletContext servletContext) {
        final ResourceServlet resourceServlet = new ResourceServlet();
        resourceServlet.setAllowedResources("/WEB-INF/admin/**/*.jsp");
        resourceServlet.setApplyLastModified(true);
        resourceServlet.setContentType("text/html");

        ServletRegistration.Dynamic customResourceServletRegistration = servletContext.addServlet(PLUGMIN_ADMIN_CUSTOM_RESOURCE_SERVLET_NAME, resourceServlet);
        customResourceServletRegistration.setLoadOnStartup(2);
        customResourceServletRegistration.addMapping(customResourceServletMapping(servletContext));
    }

    private void registerTilesDecorationFilter(final ServletContext servletContext) {
        final String urlMapping = urlMapping(plugminBaseUrl(servletContext));

        servletContext.addFilter("plugminTilesContainerEnrichmentFilter", TilesContainerEnrichmentFilter.class).addMappingForUrlPatterns(null, false, urlMapping);
    }

    private void registerHiddenHttpMethodFilter(final ServletContext servletContext) {
        final String urlMapping = urlMapping(plugminBaseUrl(servletContext));

        servletContext.addFilter("plugminHiddenHttpMethodFilter", HiddenHttpMethodFilter.class).addMappingForUrlPatterns(null, false, urlMapping);
    }

    private void registerSpringSecurityFilter(final ServletContext servletContext) {
        final String urlMapping = urlMapping(plugminBaseUrl(servletContext));

        servletContext.addFilter("plugminSpringSecurityFilterChain", springSecurityFilterChain()).addMappingForUrlPatterns(null, false, urlMapping);
    }

    private void registerCharsetFilter(final ServletContext servletContext) {
        final String urlMapping = urlMapping(plugminBaseUrl(servletContext));

        servletContext.addFilter("plugminCharsetFilter", characterEncodingFilter()).addMappingForServletNames(null, false, urlMapping);
    }

    private void registerGZipFilter(ServletContext servletContext, String... urlMappings) {
        GzipFilter gzipFilter = new GzipFilter();

        servletContext.addFilter("plugminGzipFilter", gzipFilter).addMappingForUrlPatterns(null, false, urlMappings);
    }

    private AnnotationConfigWebApplicationContext plugminApplicationContext(final ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();

        webApplicationContext.register(configurations(servletContext));

        webApplicationContext.setDisplayName("Plugmin WebApplicationContext");
        webApplicationContext.setNamespace("plugmin");
        return webApplicationContext;
    }

    private Class[] configurations(final ServletContext servletContext) {
        if (plugminSecurityEnabled(servletContext)) {
            return new Class[]{PlugminContextConfiguration.class, PlugminSecurityConfiguration.class};
        }
        return new Class[]{PlugminContextConfiguration.class};
    }

    private DelegatingFilterProxy springSecurityFilterChain() {
        final DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
        securityFilterChain.setContextAttribute(servletContextAttributeName());
        return securityFilterChain;
    }

    private CharacterEncodingFilter characterEncodingFilter() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CHARSET_ENCODING);
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    private boolean notValidBaseUrl(String url) {
        return !BASE_URL_PATTERN.matcher(url).matches();
    }

    private String resourceMapping(String resource, ServletContext servletContext) {
        if (rootUrl(plugminBaseUrl(servletContext))) {
            return resource;
        }

        return plugminBaseUrl(servletContext) + resource;
    }

    private String customResourceServletMapping(ServletContext servletContext) {
        if (rootUrl(plugminBaseUrl(servletContext))) {
            return "/custom";
        }

        return plugminBaseUrl(servletContext) + "/custom";
    }

    private String urlMapping(String baseUrl) {
        if (rootUrl(baseUrl)) {
            return "/*";
        }
        return baseUrl + "/*";
    }

    private String dispatcherUrlMapping(String url) {
        if (rootUrl(url)) {
            return "/";
        }
        return urlMapping(url);
    }

    private boolean rootUrl(final String url) {
        return "/".equals(url);
    }

    private boolean notRootUrl(final String url) {
        return !rootUrl(url);
    }

    private String configurationsBasePackage(final ServletContext servletContext) {
        return servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_BASE_PACKAGE);
    }

    public static String plugminBaseUrl(final ServletContext servletContext) {
        String baseUrl = servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_BASE_URL);
		return baseUrl != null ? baseUrl : PLUGMIN_BASE_URL;
    }

    private boolean plugminSecurityEnabled(final ServletContext servletContext) {
        return BooleanUtils.toBoolean(servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_SECURITY));
    }

    private String plugminGlobalFileStorageDirectory(final ServletContext servletContext) {
        return servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_FILE_STORAGE_PATH);
    }

    private boolean plugminConfigurationDisabled(final ServletContext servletContext) {
    	String disabled = servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_DISABLE);
    	return Boolean.valueOf(disabled);
    }

    private boolean notValidFileStorageDirectoryDefined(final ServletContext servletContext) {
        final String fileStorageDirectoryPath = plugminGlobalFileStorageDirectory(servletContext);

        if (isBlank(fileStorageDirectoryPath)) {
            return false;
        }

        final File fileStorageDirectory = getFile(fileStorageDirectoryPath);
        if (fileStorageDirectory.exists() && fileStorageDirectory.isDirectory()) {
            return false;
        }

        return true;
    }
}