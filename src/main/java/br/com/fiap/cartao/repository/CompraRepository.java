package br.com.fiap.cartao.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.SituacaoCompra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	List<Compra> findAllByDataBetweenAndSituacao(
			LocalDate dataUltimaAtualizacaoInicio,
			LocalDate dataUltimaAtualizacaoFim,
			SituacaoCompra situacao);
}
