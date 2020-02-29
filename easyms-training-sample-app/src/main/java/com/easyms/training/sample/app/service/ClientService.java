package com.easyms.training.sample.app.service;

import com.easyms.training.sample.app.converter.ClientConverter;
import com.easyms.training.sample.app.model.dto.ClientDto;
import com.easyms.training.sample.app.model.entity.Client;
import com.easyms.training.sample.app.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<ClientDto> getById(Long id, String tid) {

       log.info("[{}][ClientService][getById] find client by id ", tid);

       Optional<Client> client = clientRepository.findById(id);
       return client.map(cl -> ClientConverter.newInstance().convert(cl));
    }

    public List<ClientDto> getAll(String tid) {

        log.info("[{}][ClientService][getAll] get All clients ", tid);

        List<Client> clientDtos = clientRepository.findAll();
        return clientDtos.stream().map(client -> ClientConverter.newInstance().convert(client)).collect(Collectors.toList());
    }

    /*
       public ClientDto create(ClientRequest clientRequest){
           Client client = ClientRequestConverter.newInstance().convert(clientRequest);
           Client c = clientRepository.save(client);
           return ClientConverter.newInstance().convert(c);
       }
    */
    public ClientDto getByEmail(String email){
        // Optional<Client> client = clientRepository.findByEmail(email);
        // return ClientConverter.newInstance().convert(client.orElse(null));

        return null;
    }
}
