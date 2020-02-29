package com.easyms.training.sampleapp.api;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import com.easyms.training.sampleapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api")
public class EasyMsController {

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/get-hello")
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok().body("Bonjour Monde ");
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients")
    ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
        clientService.createClient(clientDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients").build().expand(clientDto.getId()).toUri();
        return ResponseEntity.created(location).body(clientDto);
    }

    @GetMapping(path = "/v1/clients")
    public ResponseEntity<List<ClientDto>> getClientById() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Optional<ClientDto> clt = clientService.getById(id);
        return clt.map(clientDto -> ResponseEntity.ok().body(clientDto)).orElseGet(()-> ResponseEntity.notFound().build());

    }


}
