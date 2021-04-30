package com.creditCardManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.creditCardManagement.models.Client;

public interface ClientRepository extends JpaRepository<Client, String>, JpaSpecificationExecutor<Client>{

}
