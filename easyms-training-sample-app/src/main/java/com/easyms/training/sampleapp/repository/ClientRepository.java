package com.easyms.training.sampleapp.repository;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClientRepository {

    private static Map<Long, ClientDto> clientDtoMap = new HashMap<>();
    private static int maxLine = 0;

    public void createClient(ClientDto clientDto) {
        clientDtoMap.put(clientDto.getId(), clientDto);
    }

    public List<ClientDto> getAllClients() {
        return new ArrayList<>(clientDtoMap.values());
    }

    public Optional<ClientDto> getById(long id) {
        return Optional.ofNullable(clientDtoMap.get(id));

    }

}
