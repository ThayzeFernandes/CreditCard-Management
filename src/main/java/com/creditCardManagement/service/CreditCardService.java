package com.creditCardManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.creditCardManagement.models.CreditCard;
import com.creditCardManagement.repositories.CreditCardRepository;


@Service
public class CreditCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private ClientService clientService;
	
	
	public List<CreditCard> getAllCreditCards(){
		List<CreditCard> creditCards = creditCardRepository.findAll();
		return creditCards;
	}
	
	public CreditCard createCreditCard(CreditCard creditCard) throws NotFoundException {
		clientService.getClientByCpf(creditCard.getClientOwnerCpf());

		return creditCardRepository.save(creditCard);
	}

	public CreditCard getCreditCardById(String id) throws NotFoundException {
		Optional<CreditCard> creditCard = creditCardRepository.findById(id);
		return creditCard.orElseThrow(() -> new NotFoundException());
	}
	
	public List<CreditCard> getCreditCardByCpf(String cpf) throws NotFoundException {
		Example<CreditCard> example = Example.of(CreditCard.builder().clientOwnerCpf(cpf).build(), ExampleMatcher.matchingAny());
		List<CreditCard> creditCards = creditCardRepository.findAll(example);
		return creditCards;
	}
	
	public void deleteCreditCard(String id) throws NotFoundException {
		CreditCard creditCard = getCreditCardById(id);
		creditCardRepository.delete(creditCard);
	}

}
