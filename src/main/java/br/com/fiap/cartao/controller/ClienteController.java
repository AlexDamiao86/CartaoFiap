package br.com.fiap.cartao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;
import br.com.fiap.cartao.dto.LimiteDisponivelClienteDTO;
import br.com.fiap.cartao.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO insert(@RequestBody CreateUpdateClienteDTO createUpdateClienteDTO) {
		return clienteService.create(createUpdateClienteDTO);
	}
	
	@GetMapping
	public List<ClienteDTO> listClientes(
			@RequestParam(required = false) String nome
	){
		return clienteService.listAll(nome);
	}
	
	@GetMapping("{id}")
	public ClienteDTO findById(@PathVariable Long id) {
		return clienteService.findbyId(id);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
	@PutMapping("{id}")
	public ClienteDTO update(
			@PathVariable Long id,
			@RequestBody CreateUpdateClienteDTO createUpdateClienteDTO 
			) {
		return clienteService.update(id, createUpdateClienteDTO);
	}
	
	@PatchMapping("limite/{id}")
	public ClienteDTO updateLimite(
			@PathVariable Long id,
			@RequestBody LimiteDisponivelClienteDTO limiteDisponivelClienteDTO 
			) {
		return clienteService.updateLimite(id, limiteDisponivelClienteDTO);
	}

}
