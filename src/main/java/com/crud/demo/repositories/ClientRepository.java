package com.crud.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
