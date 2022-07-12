package br.com.fiap.cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class CreateCompraDTO {
	
	private Long idCliente;
	private Long idPlastico;
	private BigDecimal valor; 
	private LocalDate data;

}
