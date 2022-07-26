package br.com.fiap.cartao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "usuarios")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String nome; 
	private String senha; 
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	@Column(nullable = false, updatable = false, name = "data_criacao")
	@CreatedDate
	private Date dataCriacao;
	
	@Column(nullable = false, updatable = true, name = "data_alteracao")
	@LastModifiedDate
	private Date dataUltimaAlteracao;
	
}
