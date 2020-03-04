package com.easyms.training.sampleapp;

import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class EasyMsTrainingSampleApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyMsTrainingSampleApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    @Bean
    CommandLineRunner init(ClientRepository clientRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                Client client = new Client();
                client.setFirstname(name);
                client.setEmail("test@hotmail.fr");
                clientRepository.save(client);
            });
            clientRepository.findAll().forEach(System.out::println);
        };
    }
}