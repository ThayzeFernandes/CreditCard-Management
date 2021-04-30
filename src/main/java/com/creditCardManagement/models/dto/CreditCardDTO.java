package com.creditCardManagement.models.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {
	
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
