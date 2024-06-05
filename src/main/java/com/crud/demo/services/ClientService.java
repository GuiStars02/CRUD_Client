package com.crud.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.demo.entities.dtos.ClientDTO;
import com.crud.demo.repositories.ClientRepository;

@Service
public class ClientService {

	ClientRepository repository;

	public List<ClientDTO> findAll() {
		// TODO Auto-generated method stub
		
	}
	
}
