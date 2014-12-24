package org.plugmin.core.config;

import static org.plugmin.core.config.PlugminWebApplicationInitializer.plugminBaseUrl;
import org.apache.commons.lang3.BooleanUtils;

import javax.servlet.ServletContext;

import java.io.File;

import static org.apache.commons.io.FileUtils.getFile;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.plugmin.core.util.PlugminConfigurationUtils.*;

public class StandardPlugminConfiguration implements PlugminConfiguration {

    private final String applicationBaseUrl;
    private final String applicationBaseNoEndSeparator;
    private final boolean securityEnabled;
    private final String securityLogoutUrl;
    private final String backToSiteUrl;
    private final File fileStorageDirectory;
    private final boolean fileStreaming;

    public StandardPlugminConfiguration(ServletContext servletContext) {
        this.applicationBaseUrl = plugminBaseUrl(servletContext);
        this.applicationBaseNoEndSeparator = urlWithoutEndSeparator(this.applicationBaseUrl);
        this.backToSiteUrl = backToSiteUrl(servletContext);

        this.fileStorageDirectory = fileStorageDirectory(servletContext);
        this.fileStreaming = BooleanUtils.toBoolean(servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_FILE_STREAMING));

        this.securityEnabled = BooleanUtils.toBoolean(servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_SECURITY));
        if (securityEnabled) {
            this.securityLogoutUrl = servletContext.getContextPath() + this.applicationBaseNoEndSeparator + PLUGMIN_ADMIN_SECURITY_LOGOUT_URL_DEFAULT;
        } else {
            this.securityLogoutUrl = servletContext.getContextPath() + defaultIfBlank(servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_SECURITY_LOGOUT_URL), "#");
        }
    }

    @Override
    public File getFileStorageDirectory() {
        return fileStorageDirectory;
    }

    @Override
    public boolean isFileStreamingEnabled() {
        return fileStreaming;
    }

    @Override
    public String getBackToSiteUrl() {
        return backToSiteUrl;
    }

    @Override
    public String getApplicationBaseUrl() {
        return applicationBaseUrl;
    }

    @Override
    public String getApplicationUrl(String path) {
        return applicationBaseNoEndSeparator + path;
    }

    @Override
    public boolean isSecurityEnabled() {
        return securityEnabled;
    }

    @Override
    public String getSecurityLogoutUrl() {
        return securityLogoutUrl;
    }

    private File fileStorageDirectory(ServletContext servletContext) {
        final String fileStoragePath = servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_FILE_STORAGE_PATH);

        return isBlank(fileStoragePath) ? null : getFile(fileStoragePath);
    }

    private String backToSiteUrl(ServletContext servletContext) {
        final String backToSiteUrl = servletContext.getInitParameter(PLUGMIN_ADMINISTRATION_BACK_TO_SITE_URL);

        if (isBlank(backToSiteUrl)) {
            return "#";
        }

        if (backToSiteUrl.startsWith("/")) {
            return servletContext.getContextPath() + backToSiteUrl;
        }

        return backToSiteUrl;
    }

    private String urlWithoutEndSeparator(String url) {
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}