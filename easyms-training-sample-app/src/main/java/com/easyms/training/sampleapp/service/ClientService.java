package com.easyms.training.sampleapp.service;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import com.easyms.training.sampleapp.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(ClientDto clientDto) {
        clientRepository.createClient(clientDto);
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Optional<ClientDto> getById(long id) {
        return clientRepository.getById(id);
    }
}
