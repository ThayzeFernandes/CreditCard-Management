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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditCardManagement.models.CreditCard;
import com.creditCardManagement.models.dto.CreditCardDTO;
import com.creditCardManagement.models.dto.ExceptionResponse;
import com.creditCardManagement.service.CreditCardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(name = "/creditcards")
@Tag(name = "CreditCard Controller", description = "CreditCard Management")
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditCardService;
	
	
	@PostMapping
	@Operation(summary = "Insert one CreditCard", responses = {
			@ApiResponse(responseCode = "201", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))),
			@ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<CreditCard> createCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) throws NotFoundException {
		return new ResponseEntity<CreditCard>(
				this.creditCardService.createCreditCard(CreditCard.builder().number(creditCardDTO.getNumber())
				.agency(creditCardDTO.getAgency())
				.codeSec(creditCardDTO.getCodeSec())
				.expirationDate(creditCardDTO.getExpirationDate())
				.clientOwnerCpf(creditCardDTO.getClientOwnerCpf()).build()), HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Find All CreditCard", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))) })
	public ResponseEntity<List<CreditCard>> getCreditCard() {
		List<CreditCard> creditCard = creditCardService.getAllCreditCards();
		return ResponseEntity.ok().body(creditCard);
	}
	
	@GetMapping("id={id}")
	@Operation(summary = "Find a CreditCard by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<CreditCard> getClientById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<CreditCard>(creditCardService.getCreditCardById(id), HttpStatus.OK);
	}
	
	@GetMapping("cpf={cpf}")
	@Operation(summary = "Find a CreditCard by cpf ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<List<CreditCard>> getCreditCardByCpf(@PathVariable String cpf) throws NotFoundException {
		return ResponseEntity.ok().body(creditCardService.getCreditCardByCpf(cpf));
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Delete a CreditCard by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<CreditCard> deleteCreditCardById(@PathVariable String id) throws NotFoundException {
		creditCardService.deleteCreditCard(id);
		return new ResponseEntity<CreditCard>(HttpStatus.OK);
	}
	
}
