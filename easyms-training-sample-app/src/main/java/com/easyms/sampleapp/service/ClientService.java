package com.easyms.sampleapp.service;

import com.easyms.sampleapp.model.dto.ClientDto;
import com.easyms.sampleapp.model.dto.ClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ClientService {

    private Map<Long, ClientDto> clientDtoMap;

    public ClientDto getById(Long id) {
       return clientDtoMap.get(id);
    }

    public ClientDto create(ClientRequest clientRequest){
        ClientDto clientDto = new ClientDto(
                clientRequest.getId(),
                clientRequest.getFirstname(),
                clientRequest.getLastname(),
                clientRequest.getEmail());
        clientDtoMap.put(clientDto.getId(), clientDto);

        return clientDto;
    }
}
