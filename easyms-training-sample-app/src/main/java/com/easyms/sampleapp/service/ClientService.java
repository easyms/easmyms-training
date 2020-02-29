package com.easyms.sampleapp.service;

import com.easyms.sampleapp.model.dto.ClientDto;
import com.easyms.sampleapp.model.entity.ClientEntity;
import com.easyms.sampleapp.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void saveClient(ClientDto clientDto){
        ClientEntity clientEntity = new ClientEntity(clientDto.getId(),clientDto.getFullName());
        clientRepository.savedClient(clientEntity);
    }

    public ClientDto getClientByKey(long key){
        ClientEntity clientEntity = clientRepository.getClientByKey(key);
        return new ClientDto(clientEntity.getId(),clientEntity.getFullName());
    }

    public HashMap<Long,ClientDto> getAllClients(){
        HashMap<Long,ClientEntity> listClients = clientRepository.getAllClient();
        HashMap<Long,ClientDto> clientsDto = new HashMap<>();
        Iterator it = listClients.keySet().iterator();
        while(it.hasNext()){
            long key = (Long) it.next();
            clientsDto.put(key,new ClientDto(listClients.get(key).getId(),listClients.get(key).getFullName()));
        }
        return clientsDto;
    }

    public void removeClient(long key){
        clientRepository.removeClientByKey(key);
    }

}
