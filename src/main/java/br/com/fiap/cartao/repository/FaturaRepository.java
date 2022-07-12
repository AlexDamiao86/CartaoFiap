package br.com.fiap.cartao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cartao.entity.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
	
}
