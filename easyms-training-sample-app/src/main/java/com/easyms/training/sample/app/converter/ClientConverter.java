package com.easyms.training.sample.app.converter;

import com.easyms.training.sample.app.model.dto.ClientDto;
import com.easyms.training.sample.app.model.entity.Client;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;


@Data(staticConstructor = "newInstance")
public class ClientConverter implements Converter<Client, ClientDto> {

    @Override
    public ClientDto convert(Client client) {
        if(Objects.isNull(client)){
            return null;
        }

        return ClientDto.builder() .id(client.getId()) .firstname(client.getFirstname()) .lastname(client.getLastname())  .email(client.getEmail()) .build();
    }
}
