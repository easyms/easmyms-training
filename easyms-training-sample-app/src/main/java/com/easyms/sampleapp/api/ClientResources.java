package com.easyms.sampleapp.api;

import com.easyms.sampleapp.model.dto.ClientDto;
import com.easyms.sampleapp.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/clients")
@Validated
@AllArgsConstructor
public class ClientResources {

    private final ClientService clientService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto){
        clientService.saveClient(clientDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/clients").build().expand(clientDto.getId()).toUri();
        return ResponseEntity.created(location).body(clientDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<HashMap<Long,ClientDto>> getListClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE,path = "/api/clients/{key}")
    ResponseEntity<ClientDto> getClientByKey(@PathVariable long key){
        return ResponseEntity.ok().body(clientService.getClientByKey(key));
    }

    @DeleteMapping
    ResponseEntity removeClient(@PathVariable long key){
        clientService.removeClient(key);
        return ResponseEntity.ok().build();
    }
}
