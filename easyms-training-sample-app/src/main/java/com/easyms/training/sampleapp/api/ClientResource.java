package com.easyms.training.sampleapp.api;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.service.ClientService;
import com.easyms.training.sampleapp.util.ObjectMapperUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientResource {

    private final ClientService clientService;


    @ApiOperation("returns all clients")
    @GetMapping(path = "/v1/clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAll());
    }

    @ApiOperation("returns a client by searching his id ")
    @GetMapping(path = "/v1/clients/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> getClientById(@PathVariable @Valid Long id) {
        Optional<ClientDto> clt = clientService.getById(id);
        return clt.map(clientDto -> ResponseEntity.ok().body(clientDto)).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @ApiOperation("creates a new client")
    @PostMapping(path = "/v1/clients", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDto> createClient(@RequestBody  ClientDto clientDto) {
        clientService.save(clientDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients/{id}").build().expand(clientDto.getId()).toUri();
        return ResponseEntity.created(location).body(clientDto);
    }

    @ApiOperation("updates existing client")
    @PutMapping(path = {"/v1/clients/{id}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {

        if (!clientService.getById(id).isPresent()) {
            log.error("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ObjectMapperUtils.map(clientService.save(clientDto),ClientDto.class));
    }

    @ApiOperation("delete a client")
    @DeleteMapping(path = {"/v1/clients/{id}"},produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!clientService.getById(id).isPresent()) {
            log.error("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }

        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
