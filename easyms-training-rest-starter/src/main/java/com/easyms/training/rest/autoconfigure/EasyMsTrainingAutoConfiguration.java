package com.easyms.training.rest.autoconfigure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ComponentScan(basePackages = {"com.easyms.training.rest.ms"})
public class EasyMsTrainingAutoConfiguration {

}
