package com.easyms.sampleapp;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * @author Mohamed OULAAROSS
 */
@SpringBootApplication
@AllArgsConstructor
public class EasyMsTrainingSampleApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyMsTrainingSampleApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
