package br.com.fiap.cartao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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
	@Column(length = 30)
	private SituacaoCartao situacao;
	@ManyToOne
	private Cliente cliente;

	public PlasticoCartao(Cliente cliente) {
		this.cliente = cliente;
		this.limite = cliente.getLimiteDisponivel();
		this.situacao = SituacaoCartao.ATIVO;
	}

}
