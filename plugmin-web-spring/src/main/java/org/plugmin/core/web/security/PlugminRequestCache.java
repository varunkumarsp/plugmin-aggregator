package org.plugmin.core.web.security;

import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This implementation of {@code RequestCache} stores request in HttpSession as
 * LigtAdmin-specific attribute.
 */
public class PlugminRequestCache extends HttpSessionRequestCache {

    protected final String savedRequestKey = "plugmin:SPRING_SECURITY_SAVED_REQUEST";
    protected final PortResolver portResolver = new PortResolverImpl();

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, portResolver);
        request.getSession().setAttribute(savedRequestKey, savedRequest);
        logger.debug("DefaultSavedRequest added to Session: " + savedRequest);
    }

    @Override
    public SavedRequest getRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            return (DefaultSavedRequest) session.getAttribute(savedRequestKey);
        }
        return null;
    }

    @Override
    public void removeRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            logger.debug("Removing DefaultSavedRequest from session if present");
            session.removeAttribute(savedRequestKey);
        }
    }

}
