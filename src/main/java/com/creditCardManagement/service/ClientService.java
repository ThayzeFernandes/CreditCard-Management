package com.creditCardManagement.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.creditCardManagement.models.Client;
import com.creditCardManagement.models.dto.ClientDTO;
import com.creditCardManagement.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	public List<Client> getAllClients(){
		List<Client> clients = clientRepository.findAll();
		return clients;
	}
	
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}
	
	private void updateClient(Client client, ClientDTO clientDTO) {
		client.setName(clientDTO.getName());
	}

	public Client getClientById(String id) throws NotFoundException {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new NotFoundException());
	}
	
	public Client getClientByCpf(String cpf) throws NotFoundException {
		Example<Client> example = Example.of(Client.builder().cpf(cpf).build(), ExampleMatcher.matchingAny());
		Optional<Client> client = clientRepository.findOne(example);
		return client.orElseThrow(() -> new NotFoundException());
	}
	
	
	public Client changeClient(String id, @Valid ClientDTO clientDTO) throws NotFoundException {
		Client client = getClientById(id);
		updateClient(client, clientDTO);
		return createClient(client);
	}
	
	public void deleteClient(String id) throws NotFoundException {
		Client client = getClientById(id);
		clientRepository.delete(client);
	}

}
