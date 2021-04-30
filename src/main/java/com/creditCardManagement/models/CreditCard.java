package com.creditCardManagement.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "creditCard")
public class CreditCard {
	
	@Id
    @GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy ="uuid")
    private String id;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String agency;
	
	@NotBlank
	private String codeSec;
	
	@DateTimeFormat
	private Date expirationDate;
	
	@NotBlank
	private String clientOwnerCpf;
}
