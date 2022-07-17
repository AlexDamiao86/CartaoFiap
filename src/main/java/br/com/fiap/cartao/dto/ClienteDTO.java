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

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	
	
}
