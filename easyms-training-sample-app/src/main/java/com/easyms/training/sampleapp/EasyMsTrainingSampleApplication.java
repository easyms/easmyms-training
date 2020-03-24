package com.easyms.training.sampleapp;

import com.easyms.training.sampleapp.model.entity.*;
import com.easyms.training.sampleapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class EasyMsTrainingSampleApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyMsTrainingSampleApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }


    @Bean
    CommandLineRunner init(ClientRepository clientRepository, ProductRepository productRepository) {

        /* *******************************/
        Product samsungS11 = Product.builder()
                .price(1000F)
                .reference("Samsung S11")
                .color("gris")
                .build();

        Product iphone11 = Product.builder()
                .price(1500F)
                .reference("Iphone 11")
                .color("Black")
                .build();
        productRepository.saveAll(Arrays.asList(samsungS11, iphone11));


        Client clientMohamed = Client.builder()
                .firstname("Mohamed")
                .lastname("Khames")
                .email("mohamed.khames" + "@gmail.fr")
                .age(new Random().nextInt((80 - 1) + 1) + 1)
                .city("Paris")
                .build();

        Client clientWalid = Client.builder()
                .firstname("walid")
                .lastname("smida")
                .email("walid.smida@gmail.com")
                .age(new Random().nextInt((80 - 1) + 1) + 1)
                .city("Tunis")
                .build();

        Order order1Mohamed = Order.builder()
                .startDate(LocalDate.of(2017,1,1))
                .endDate(LocalDate.of(2017,5,1))
                .itemName("item1")
                .build();

        Order order2Mohamed = Order.builder()
                .startDate(LocalDate.of(2018,1,1))
                .endDate(LocalDate.of(2018,12,1))
                .itemName("item2")
                .build();

        OrderDetail orderDetail1 = OrderDetail.builder()
                .amount(2000F)
                .quanity(2)
                .product(samsungS11)
                .order(order1Mohamed)
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .amount(3000F)
                .quanity(3)
                .product(iphone11)
                .order(order2Mohamed)
                .build();

        order1Mohamed.setOrderDetails(Stream.of(orderDetail1).collect(Collectors.toSet()));
        order2Mohamed.setOrderDetails(Stream.of(orderDetail2).collect(Collectors.toSet()));

        clientMohamed.setOrders(Arrays.asList(order1Mohamed,order2Mohamed));
        order1Mohamed.setClient(clientMohamed);
        order2Mohamed.setClient(clientMohamed);

        clientRepository.saveAll(Arrays.asList(clientMohamed,clientWalid));


        return null;
    }

}