package br.com.fiap.cartao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private String numero;
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
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Compra> compras = new ArrayList<>();

	public PlasticoCartao(Cliente cliente) {
		this.cliente = cliente;
		this.limite = cliente.getLimiteDisponivel();
		cliente.setLimiteDisponivel(BigDecimal.valueOf(0));
		this.situacao = SituacaoCartao.ATIVO;
	}
	
	public void adicionarCompra(Compra compra) {
		compra.setCartao(this);
		this.compras.add(compra);
	}

}
