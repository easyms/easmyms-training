package com.easyms.training.sample.app.repository;

import com.easyms.training.sample.app.model.entity.Client;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    Map<Long, Client> clients = null;

    @PostConstruct
    public void init() {
        clients = new HashMap<>();
        clients.put(1L, Client.builder() .id(1L) .firstname("Ahmed") .lastname("KMIHA")  .email("kmiha.ahmed@gmail.com") .build());
        clients.put(2L, Client.builder() .id(2L) .firstname("Housain") .lastname("BOUHDADI")  .email("housain.bouhdaidi@gmail.com") .build());
        clients.put(3L, Client.builder() .id(3L) .firstname("Anis") .lastname("BESSA")  .email("bessa.anis@gmail.com") .build());
    }

    public List<Client> findAll() {
        return clients.values().stream().collect(Collectors.toList());
    }

    public Optional<Client> findById(Long id) {
        return Optional.of(clients.get(id));
    }
}
