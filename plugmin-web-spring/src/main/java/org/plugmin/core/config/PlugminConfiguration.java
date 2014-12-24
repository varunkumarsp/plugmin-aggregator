package org.plugmin.core.config;

import java.io.File;

public interface PlugminConfiguration {

    String getApplicationBaseUrl();

    String getApplicationUrl(String path);

    boolean isSecurityEnabled();

    String getSecurityLogoutUrl();

    String getBackToSiteUrl();

    File getFileStorageDirectory();

    boolean isFileStreamingEnabled();

}