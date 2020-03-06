package com.easyms.training.sampleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyms.training.sampleapp.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
