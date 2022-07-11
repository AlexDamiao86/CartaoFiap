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
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="cartoes")
public class PlasticoCartao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private Long numero;
	private BigDecimal limite;
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	@Enumerated(EnumType.STRING)
	private SituacaoCartao situacao;
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime dataCadastro;
	@Column(name = "data_atualizacao", nullable = false, updatable = true)
	@LastModifiedDate
	private LocalDateTime dataUltimaAtualizacao;	
	@ManyToOne
	private Cliente cliente;

	public PlasticoCartao(Cliente cliente) {
		this.cliente = cliente;
		this.limite = cliente.getLimiteDisponivel();
		this.situacao = SituacaoCartao.ATIVO;
	}

}
