package com.creditCardManagement.models.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String cpf;
	
}
