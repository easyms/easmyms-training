package com.easyms.training.sampleapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import lombok.AllArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author abessa
 */
@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties properties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select().paths(s -> Predicates.containsPattern(properties.getPaths()).apply(s))
                .build()
                .apiInfo(getApiInfo());
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getApiInfoTitle())
                .description(properties.getApiInfoDescription())
                .version(properties.getApiInfoVersion())
                .license(properties.getApiInfoLicense())
                .licenseUrl(properties.getApiInfoLicenseUrl())
                .contact(new Contact(properties.getApiInfoContactName(), properties.getApiInfoContactURL(), properties.getApiInfoContactEmail()))
                .build();
    }
}