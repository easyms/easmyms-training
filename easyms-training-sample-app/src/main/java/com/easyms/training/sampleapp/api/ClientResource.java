package com.easyms.training.sampleapp.api;

import com.easyms.training.sampleapp.model.dto.ClientDTO;
import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.repository.ClientRepository;
import com.easyms.training.sampleapp.service.ClientService;
import com.easyms.training.sampleapp.util.ObjectMapperUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<ClientDTO>> searchForClients() {
        return ResponseEntity.ok().body(clientService.getAll());
    }

    @ApiOperation("returns a client by searching his id ")
    @GetMapping(path = "/v1/clients/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> getClientById(@PathVariable @Valid Long id) {
        Optional<ClientDTO> clt = clientService.getById(id);
        return clt.map(clientDTO -> ResponseEntity.ok().body(clientDTO)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("creates a new client")
    @PostMapping(path = "/v1/clients", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDto) {
        ClientDTO createdClient = clientService.save(clientDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients/{id}").build().expand(createdClient.getId()).toUri();
        return ResponseEntity.created(location).body(createdClient);
    }

    @ApiOperation("updates existing client")
    @PutMapping(path = {"/v1/clients/{id}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> update(@PathVariable("id") Long id, @RequestBody ClientDTO clientDto) {

        if (!clientService.getById(id).isPresent()) {
            log.error("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ObjectMapperUtils.map(clientService.save(clientDto), ClientDTO.class));
    }

    @ApiOperation("delete a client")
    @DeleteMapping(path = {"/v1/clients/{id}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!clientService.getById(id).isPresent()) {
            log.error("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }

        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/v1/clients/searchs")
    public Object findClientOrdersByDates(
            @Join(path = "orders", alias = "o")
            @Or({
                    @Spec(path = "o.startDate", params = "startDate", spec = GreaterThanOrEqual.class, config = "dd-MM-yyyy"),
                    @Spec(path = "o.endDate", params = "endDate", spec = LessThanOrEqual.class, config = "dd-MM-yyyy")
            })
                    Specification<Client> customersByOrderedItemSpec) {

        return clientService.getAllBySpecification(customersByOrderedItemSpec);
    }

    @ApiOperation("search Client By Criteria")
    @RequestMapping("/v1/clients/search")
    public Object findClientsByCriteria(
            @Or({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "firstname", params = "firstname", spec = LikeIgnoreCase.class),
                    @Spec(path = "lastname", params = "lastname", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "age", params = "age", spec = LikeIgnoreCase.class),
                    @Spec(path = "city", params = "city", spec = LikeIgnoreCase.class)

            }) Specification<Client> specification) {

        return clientService.getAllBySpecification(specification);
    }




}
