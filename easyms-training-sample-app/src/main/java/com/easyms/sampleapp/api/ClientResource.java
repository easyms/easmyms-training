package com.easyms.sampleapp.api;

import com.easyms.sampleapp.model.dto.ClientDto;
import com.easyms.sampleapp.model.dto.ClientRequest;
import com.easyms.sampleapp.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Mohamed OULAAROSS
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients")
    ResponseEntity<ClientDto> createClient(@RequestBody ClientRequest clientRequest){
        log.info("create new client");
        ClientDto clientDto = clientService.create(clientRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients").build().expand(clientDto.getId()).toUri();
        return ResponseEntity.created(location).body(clientDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto clientDto = clientService.getById(id);
        return ResponseEntity.ok().body(clientDto);
    }
}
