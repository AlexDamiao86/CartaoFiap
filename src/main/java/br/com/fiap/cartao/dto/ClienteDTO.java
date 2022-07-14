package br.com.fiap.cartao.dto;

import java.time.LocalDateTime;

import br.com.fiap.cartao.entity.Cliente;
import lombok.Getter;

@Getter
public class ClienteDTO {
	
	private Long identificador;
	private String nome;
	private int matricula;
	private LocalDateTime dataCadastro;  
	private LocalDateTime dataUltimaAtualizacao;

	public ClienteDTO(Cliente cliente) {
		this.identificador = cliente.getId();
		this.nome = cliente.getNome();
		this.matricula = cliente.getMatricula();
		this.dataCadastro = cliente.getDataCadastro();
		this.dataUltimaAtualizacao = cliente.getDataUltimaAtualizacao();
	}

}
