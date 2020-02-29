package com.easyms.training.sampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyMsSampleApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyMsSampleApplication.class);
       // springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}