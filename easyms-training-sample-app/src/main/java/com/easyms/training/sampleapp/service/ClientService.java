package com.easyms.training.sampleapp.service;

import com.easyms.training.sampleapp.model.dto.ClientDTO;
import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.repository.ClientRepository;
import com.easyms.training.sampleapp.util.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Optional<ClientDTO> getById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        Optional<ClientDTO> dto = Optional.ofNullable(ObjectMapperUtils.map(client.get(), ClientDTO.class));
        return dto;


    }
    public List<Client> getAllBySpecification(final Specification<Client> specification) {
        return clientRepository.findAll(specification);
    }


    public ClientDTO getClientByEmail(String email)
    {
        return ObjectMapperUtils.map(clientRepository.findClientByEmailIgnoreCase(email), ClientDTO.class);
    }

    public List<ClientDTO> getAll() {
        List<Client> clientList = clientRepository.findAll();
        return ObjectMapperUtils.mapAll(clientList, ClientDTO.class);

    }
    public ClientDTO save(ClientDTO dto) {
        return ObjectMapperUtils.map(clientRepository.save(ObjectMapperUtils.map(dto,Client.class)), ClientDTO.class);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}

