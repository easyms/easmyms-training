package com.easyms.training.sampleapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.easyms.training.sampleapp.model.dto.ClientDto;
import com.easyms.training.sampleapp.model.entity.Client;
import com.easyms.training.sampleapp.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public void createClient(ClientDto clientDto) {
		Client client = Client.builder().firstname(clientDto.getFirstname()).lastname(clientDto.getLastname()).build();
		clientRepository.save(client);
	}

	public List<ClientDto> getAllClients() {
		return clientRepository.findAll().stream()
				.map(t -> ClientDto.builder().firstname(t.getFirstname()).lastname(t.getLastname()).build())
				.collect(Collectors.toList());
	}

	public Optional<ClientDto> getById(long id) {
		return clientRepository.findById(id)
				.map(t -> ClientDto.builder().firstname(t.getFirstname()).lastname(t.getLastname()).build());
	}

	public void updateClient(long id, ClientDto clientDto) {
		Client client = clientRepository.findById(id).get();
		client.setFirstname(clientDto.getFirstname());
		client.setLastname(clientDto.getLastname());

		clientRepository.save(client);
	}

	public boolean deleteById(long id) {
		if (clientRepository.existsById(id)) {
			clientRepository.deleteById(id);
			return true;
		}
		return false;

	}
}
