package com.easyms.training.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
