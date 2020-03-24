package com.easyms.training.sampleapp.repository;

import com.easyms.training.sampleapp.model.entity.Order;
import com.easyms.training.sampleapp.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {
}
