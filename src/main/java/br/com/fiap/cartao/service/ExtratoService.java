package br.com.fiap.cartao.service;

import java.util.List;

import org.springframework.core.io.Resource;

import br.com.fiap.cartao.dto.CompraDTO;

public interface ExtratoService {

	List<CompraDTO> buscaExtrato(Long id, int mes, int ano);
	Resource load(String filename);
	
}
