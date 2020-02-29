package com.easyms.training.sample.app.api;


import com.easyms.training.sample.app.model.dto.ClientDto;
import com.easyms.training.sample.app.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
public class ClientResource {

    private final ClientService clientService;

   @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients/{id}")
   public ResponseEntity<ClientDto> findById(@PathVariable Long id) {

       String tid = UUID.randomUUID().toString();

       log.info("[{}][ClientResource][findById] find client by id ", tid);

        Optional<ClientDto> clientDto = clientService.getById(id, tid);
        return clientDto.map(clDto -> ResponseEntity.ok().body(clDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients")
    ResponseEntity<List<ClientDto>> getAllClients() {

        String tid = UUID.randomUUID().toString();

        log.info("[{}][ClientResource][getAllClients] getAll client ", tid);

        List<ClientDto> clientDtos = clientService.getAll(tid);
        return ResponseEntity.ok().body(clientDtos);
    }

}
