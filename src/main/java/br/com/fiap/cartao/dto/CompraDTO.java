package br.com.fiap.cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.PlasticoCartao;
import br.com.fiap.cartao.entity.SituacaoCompra;
import lombok.Getter;

@Getter
public class CompraDTO {
	
	private Long id;
	private String nomeCliente;
	private String numeroPlastico; 
	private BigDecimal valor; 
	private LocalDate data; 
	private SituacaoCompra situacao;
	
	public CompraDTO(Compra compra, PlasticoCartao cartao) {
		this.id = compra.getId();
		this.nomeCliente = cartao.getCliente().getNome();
		this.numeroPlastico = cartao.getNumero();
		this.valor = compra.getValor();
		this.data = compra.getData();
		this.situacao = compra.getSituacao();
	}

}
