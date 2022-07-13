package br.com.fiap.cartao.entity;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="faturas")
public class Fatura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento; 
	@Enumerated(EnumType.STRING)
	private SituacaoFatura situacao;
	@Column(name = "data_inclusao", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime dataInclusao;
	@Column(name = "data_atualizacao", nullable = false, updatable = true)
	@LastModifiedDate
	private LocalDateTime dataUltimaAtualizacao;	
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL)
	private List<Compra> compras;
	
	public Fatura(Cliente cliente) {
		this.cliente = cliente;
		this.situacao = SituacaoFatura.EM_ABERTO;
		this.compras = new ArrayList<>();
	}
	
	public void adicionarCompra(Compra compra) {
		compra.setFatura(this);
		this.compras.add(compra);
	}
	
}
