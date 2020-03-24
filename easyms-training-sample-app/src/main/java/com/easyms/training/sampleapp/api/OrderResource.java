package com.easyms.training.sampleapp.api;

import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.model.entity.Order;
import com.easyms.training.sampleapp.repository.OrderRepository;
import com.easyms.training.sampleapp.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderResource {

    private final OrderRepository orderRepository;

    @RequestMapping("/v1/orders")
    public Object findByOrderedItem(
            @Or({
                    @Spec(path = "startDate", params = "startDate", spec = GreaterThanOrEqual.class),
                    @Spec(path = "endDate", params = "endDate", spec = LessThanOrEqual.class)
            })

                    Specification<Order> specification) {

        return orderRepository.findAll(specification);
    }

}
