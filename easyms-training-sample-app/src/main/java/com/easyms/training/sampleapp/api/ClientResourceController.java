package com.easyms.training.sampleapp.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import com.easyms.training.sampleapp.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api( "Clients CRUD API operations")
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ClientResourceController {

	private final ClientService clientService;

    @ApiOperation(value = "Create a client")
	@PostMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients")
	ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
		clientService.createClient(clientDto);
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients/{id}")
				.build().expand(clientDto.getFirstname()).toUri();
		return ResponseEntity.created(location).body(clientDto);
	}
    
    @ApiOperation(value = "Get all clients")
	@GetMapping(path = "/v1/clients")
	public ResponseEntity<List<ClientDto>> getAllClients() {
		return ResponseEntity.ok().body(clientService.getAllClients());
	}

    @ApiOperation(value = "Get client by his database id")
	@GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients/{id}")
	public ResponseEntity<ClientDto> getClientById(@PathVariable @NotBlank Long id) {
		Optional<ClientDto> clt = clientService.getById(id);
		return clt.map(clientDto -> ResponseEntity.ok().body(clientDto))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

    @ApiOperation(value = "Update a client by his id")
	@PostMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/clients/{id}")
	public ResponseEntity<ClientDto> updateClientById(@RequestBody @Valid ClientDto clientDto,
			@PathVariable Long id) {
		Optional<ClientDto> clt = clientService.getById(id);
		if (!clt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		clientService.updateClient(id, clientDto);
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/clients/{id}")
				.build().expand(clientDto.getFirstname()).toUri();
		return ResponseEntity.created(location).body(clientDto);
	}

    @ApiOperation(value = "Delete a client by his id")
	@DeleteMapping("/v1/clients/{id}")
	public ResponseEntity deleteClient(@PathVariable Long id) {

		return clientService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
