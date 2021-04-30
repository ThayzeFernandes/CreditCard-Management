package com.creditCardManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.creditCardManagement.models.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String>, JpaSpecificationExecutor<CreditCard>{

}
