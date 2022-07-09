package br.com.fiap.cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.cartao.entity.Cliente;
import lombok.Getter;

@Getter
public class ClienteDTO {
	
	private Long id;
	private String nome;
	private int matricula;
	private BigDecimal limite; 
	private LocalDateTime dataCadastro;  
	private LocalDateTime dataUltimaAtualizacao;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.matricula = cliente.getMatricula();
		this.limite = cliente.getLimiteDisponivel();
		this.dataCadastro = cliente.getDataCadastro();
		this.dataUltimaAtualizacao = cliente.getDataUltimaAtualizacao();
	}

}
