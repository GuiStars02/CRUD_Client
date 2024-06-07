package com.crud.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.demo.entities.Client;
import com.crud.demo.entities.dtos.ClientDTO;
import com.crud.demo.repositories.ClientRepository;
import com.crud.demo.services.exceptions.NotFoundResourceException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();

		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}

	public ClientDTO findById(Long id) {
		Optional<Client> optional = repository.findById(id);
		Client client = optional.orElseThrow(() -> new NotFoundResourceException("Resource not found!"));
		return new ClientDTO(client);
	}

	public ClientDTO insert(ClientDTO body) {
		Client client = new Client();
		client = clientSets(client, body);
		
		repository.save(client);
		return new ClientDTO(client);
	}

	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = repository.getReferenceById(id);
			Client clientUpdated = clientSets(client, dto);
			
			repository.save(clientUpdated);
			return new ClientDTO(client);
		}
		catch(EntityNotFoundException e) {
			throw new NotFoundResourceException("Resource not found");
		}
	}
	
	private Client clientSets(Client client, ClientDTO dto) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		return client;
	}
	
}
