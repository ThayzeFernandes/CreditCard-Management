package com.creditCardManagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditCardManagement.models.Client;
import com.creditCardManagement.models.dto.ClientDTO;
import com.creditCardManagement.models.dto.ExceptionResponse;
import com.creditCardManagement.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client Controller", description = "Client Management")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	@Operation(summary = "Insert one Client", responses = {
			@ApiResponse(responseCode = "201", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))) })
	public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDTO clientDTO) {
		return new ResponseEntity<Client>(this.clientService.createClient(
				Client.builder().name(clientDTO.getName()).cpf(clientDTO.getCpf()).build()
				), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Change a Client by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))) })
	public ResponseEntity<Client> changeClient(@PathVariable String id, @Valid @RequestBody ClientDTO clientDTO)
			throws NotFoundException {
		return new ResponseEntity<Client>(clientService.changeClient(id, clientDTO), HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "Find All Clients", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))) })
	public ResponseEntity<List<Client>> getClients() {
		List<Client> clients = clientService.getAllClients();
		return ResponseEntity.ok().body(clients);
	}
	
	@GetMapping("id={id}")
	@Operation(summary = "Find a Client by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Client> getClientById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<Client>(clientService.getClientById(id), HttpStatus.OK);
	}
	
	@GetMapping("cpf={cpf}")
	@Operation(summary = "Find a Client by cpf ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Client> getClientByCpf(@PathVariable String cpf) throws NotFoundException {
		return new ResponseEntity<Client>(clientService.getClientByCpf(cpf), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Delete a Client by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Client> deleteClientById(@PathVariable String id) throws NotFoundException {
		clientService.deleteClient(id);
		return new ResponseEntity<Client>(HttpStatus.OK);
	}

}
