package br.com.fiap.cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.SituacaoCompra;
import lombok.Getter;

@Getter
public class CompraDTO {
	
	private Long identificadorCompra;
	private String nomeCliente;
	private int matriculaCliente; 
	private BigDecimal valorCompra; 
	private LocalDate dataCompra; 
	private SituacaoCompra situacaoCompra;
	private LocalDateTime dataInclusao;  
	private LocalDateTime dataUltimaAtualizacao;
	
	
	public CompraDTO(Compra compra) {
		this.identificadorCompra = compra.getId();
		this.nomeCliente = compra.getCliente().getNome();
		this.matriculaCliente = compra.getCliente().getMatricula();
		this.valorCompra = compra.getValor();
		this.dataCompra = compra.getData();
		this.situacaoCompra = compra.getSituacao();
		this.dataInclusao = compra.getDataInclusao();
		this.dataUltimaAtualizacao = compra.getDataUltimaAtualizacao();
	}

}
