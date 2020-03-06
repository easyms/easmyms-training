package com.easyms.training.sampleapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "swagger")
@Configuration
public class SwaggerProperties {

    private String paths;

    private String apiInfoTitle;

    private String apiInfoDescription;

    private String apiInfoContactEmail;

    private String apiInfoContactName;

    private String apiInfoContactURL;

    private String apiInfoVersion;

    private String apiInfoLicense;

    private String apiInfoLicenseUrl;

}
