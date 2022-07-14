package br.com.fiap.cartao.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class CreateCompraDTO {
	
	private Long idPlastico;
	private BigDecimal valor; 
	private String data;

}
