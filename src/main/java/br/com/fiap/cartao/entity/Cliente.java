package br.com.fiap.cartao.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	private int matricula;
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime dataCadastro;
	@Column(name = "data_atualizacao", nullable = false, updatable = true)
	@LastModifiedDate
	private LocalDateTime dataUltimaAtualizacao;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Compra> compras;
	
	public Cliente(String nome, int matricula) {
		this.nome = nome; 
		this.matricula = matricula;
		this.compras = new ArrayList<>();
	}
	
	public Cliente(Long id) {
		this.id = id;
		this.compras = new ArrayList<>();
	}
	
	public void adicionarCompra(Compra compra) {
		this.compras.add(compra);
	}
	
}
