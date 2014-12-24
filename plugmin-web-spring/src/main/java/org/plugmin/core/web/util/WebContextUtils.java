package org.plugmin.core.web.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.plugmin.core.util.PlugminConfigurationUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

public abstract class WebContextUtils {

    public static WebApplicationContext getWebApplicationContext(ServletContext servletContext) {
        return WebApplicationContextUtils.getWebApplicationContext(servletContext, servletContextAttributeName());
    }

    public static String servletContextAttributeName() {
        return FrameworkServlet.SERVLET_CONTEXT_PREFIX + PlugminConfigurationUtils.PLUGMIN_ADMIN_DISPATCHER_NAME;
    }

    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
        Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
        return servletRequest;
    }
}