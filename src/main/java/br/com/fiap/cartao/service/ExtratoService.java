package br.com.fiap.cartao.service;

import java.util.List;

import org.springframework.core.io.Resource;

import br.com.fiap.cartao.dto.CompraDTO;

public interface ExtratoService {

	List<CompraDTO> getByMonthAndYear(int mes, int ano);
	List<CompraDTO> listAll();
	Resource load(String filename);
	
}
