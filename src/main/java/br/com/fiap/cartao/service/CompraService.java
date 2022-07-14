package br.com.fiap.cartao.service;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;

public interface CompraService {

	CompraDTO create(CreateCompraDTO createCompraDTO);
	CompraDTO cancel(Long identificadorCompra);
	
}
