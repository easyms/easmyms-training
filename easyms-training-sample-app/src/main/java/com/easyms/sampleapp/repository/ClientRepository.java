package com.easyms.sampleapp.repository;

import com.easyms.sampleapp.model.entity.ClientEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClientRepository {
    HashMap<Long,ClientEntity> listClients = new HashMap<>();
    long key = 0L;

    public void savedClient(ClientEntity clientEntity){
        listClients.put(key,clientEntity);
        key += 1L;
    }

    public HashMap<Long,ClientEntity> getAllClient(){
        return listClients;
    }

    public ClientEntity getClientByKey(long key){
        return listClients.get(key);
    }

    public void removeClientByKey(long key){
        listClients.remove(key);
    }
}
