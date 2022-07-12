package br.com.fiap.cartao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cartao.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
}
