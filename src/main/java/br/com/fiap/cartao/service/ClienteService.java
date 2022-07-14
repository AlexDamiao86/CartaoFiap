package br.com.fiap.cartao.service;

import java.util.List;

import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;

public interface ClienteService {

	ClienteDTO create(CreateUpdateClienteDTO createUpdateClienteDTO);
	ClienteDTO update(Long id, CreateUpdateClienteDTO createUpdateClienteDTO);
	void delete(Long id);
	ClienteDTO findbyId(Long id);
	List<ClienteDTO> listAll(String nome);
	
}
