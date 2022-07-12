package br.com.fiap.cartao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name="compras")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private PlasticoCartao cartao; 
	@ManyToOne(optional = true)
	private Fatura fatura;
	private BigDecimal valor; 
	private LocalDate data; 
	@Enumerated(EnumType.STRING)
	private SituacaoCompra situacao;
	@Column(name = "data_inclusao", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime dataInclusao;
	@Column(name = "data_atualizacao", nullable = false, updatable = true)
	@LastModifiedDate
	private LocalDateTime dataUltimaAtualizacao;	
	
	public Compra(Cliente cliente, PlasticoCartao cartao, BigDecimal valor, LocalDate data) {
		this.cliente = cliente; 
		this.cartao = cartao;
		this.valor = valor;
		this.data = data;
		this.situacao = SituacaoCompra.AUTORIZADA;
	}
}
