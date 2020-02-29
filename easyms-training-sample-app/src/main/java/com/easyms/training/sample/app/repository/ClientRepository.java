package com.easyms.training.sample.app.repository;

import com.easyms.training.sample.app.model.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();
        clients.add(Client.builder() .id(1L) .firstname("Ahmed") .lastname("KMIHA")  .email("kmiha.ahmed@gmail.com") .build());
        clients.add(Client.builder() .id(2L) .firstname("Housain") .lastname("BOUHDADI")  .email("housain.bouhdaidi@gmail.com") .build());
        clients.add(Client.builder() .id(3L) .firstname("Anis") .lastname("BESSA")  .email("bessa.anis@gmail.com") .build());
        return clients;
    }

    public Optional<Client> findById(Long id) {
        Client client = Client.builder() .id(id) .firstname("Ahmed") .lastname("KMIHA")  .email("kmiha.ahmed@gmail.com") .build();
        return Optional.of(client);
    }

}
