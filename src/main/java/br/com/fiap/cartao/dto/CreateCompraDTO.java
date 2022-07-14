package br.com.fiap.cartao.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateCompraDTO {
	
	private Long identificadorCliente;
	private BigDecimal valorCompra; 
	@Schema(description = "Data da compra no cartão FIAP", format = "String", example = "13/07/2022")
	private String dataCompra;

}
