package com.easyms.training.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ComponentScan(basePackages = {"com.easyms.training.common"})
public class EasyMsTrainingAutoConfiguration {

}
