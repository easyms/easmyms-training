package com.easyms.training.sampleapp.repository;

import com.easyms.training.sampleapp.model.entity.Order;
import com.easyms.training.sampleapp.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

}
