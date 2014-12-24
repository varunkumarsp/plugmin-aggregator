package org.plugmin.core.web;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.ClassUtils.isAssignable;

import java.io.Serializable;

import org.plugmin.core.config.PlugminConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
@SuppressWarnings({"unused", "unchecked"})
public class ApplicationController {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    public static final String BEAN_FACTORY_KEY = "beanFactory";
    public static final String ADMINISTRATION_CONFIGURATION_KEY = "administrationConfiguration";
    public static final String DOMAIN_TYPE_ADMINISTRATION_CONFIGURATION_KEY = "domainTypeAdministrationConfiguration";


    @Autowired
    private ConfigurableApplicationContext appContext;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PlugminConfiguration plugminContext;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
    	ex.printStackTrace();
        return new ModelAndView("error-page").addObject("exception", ex);
    }

    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    @RequestMapping(value = "/page-not-found", method = RequestMethod.GET)
    public String handlePageNotFound() {
        return "page-not-found";
    }

    @ResponseStatus(FORBIDDEN)
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String handleAccessDenied() {
        return "access-denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return redirectTo("/dashboard");
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard-view";
    }

    @RequestMapping(value = "/domain/{domainType}", method = RequestMethod.GET)
    public String list(@PathVariable String domainType, Model model) {
        addDomainTypeConfigurationToModel(domainType, model);

        return "list-view";
    }

    @RequestMapping(value = "/domain/{domainTypeName}/{entityId}", method = RequestMethod.GET)
    public String show(@PathVariable String domainTypeName, @PathVariable String entityId, Model model) {
        addDomainTypeConfigurationToModel(domainTypeName, model);

        final Object entity = findEntityOfDomain(entityId, domainTypeName);
        if (entity == null) {
            return pageNotFound();
        }

        model.addAttribute("entity", entity);
        return "show-view";
    }

    private Object findEntityOfDomain(String entityId, String domainTypeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/domain/{domainTypeName}/{entityId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable String domainTypeName, @PathVariable String entityId, Model model) {
        addDomainTypeConfigurationToModel(domainTypeName, model);

        final Object entity = findEntityOfDomain(entityId, domainTypeName);
        if (entity == null) {
            return pageNotFound();
        }

        model.addAttribute("entity", entity);
        return "edit-view";
    }

    @RequestMapping(value = "/domain/{domainTypeName}/{entityId}/edit-dialog", method = RequestMethod.GET)
    public String editDialog(@PathVariable String domainTypeName, @PathVariable String entityId, Model model) {
        edit(domainTypeName, entityId, model);

        return "edit-dialog-view";
    }

    @RequestMapping(value = "/domain/{domainTypeName}/create", method = RequestMethod.GET)
    public String create(@PathVariable String domainTypeName, Model model) {
        addDomainTypeConfigurationToModel(domainTypeName, model);

        return "create-view";
    }

    @RequestMapping(value = "/domain/{domainTypeName}/create-dialog", method = RequestMethod.GET)
    public String createDialog(@PathVariable String domainTypeName, Model model) {
        create(domainTypeName, model);

        return "create-dialog-view";
    }

    private String pageNotFound() {
        return redirectTo("/page-not-found");
    }

    private void addDomainTypeConfigurationToModel(String domainTypeName, Model model) {
//        model.addAttribute(DOMAIN_TYPE_ADMINISTRATION_CONFIGURATION_KEY, configuration.forEntityName(domainTypeName));
//        model.addAttribute(BEAN_FACTORY_KEY, appContext.getAutowireCapableBeanFactory());
    }

    private String redirectTo(final String url) {
        if ("/".equals(plugminContext.getApplicationBaseUrl())) {
            return "redirect:" + url;
        }

        return String.format("redirect:%s%s", plugminContext.getApplicationBaseUrl(), url);
    }

    private <V extends Serializable> V stringToSerializable(String s, Class<V> targetType) {
        if (isAssignable(targetType, String.class)) {
            return (V) s;
        }
        return conversionService.convert(s, targetType);
    }
}