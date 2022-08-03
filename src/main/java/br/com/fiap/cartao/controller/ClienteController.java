package br.com.fiap.cartao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import br.com.fiap.cartao.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "/clientes", description = "Gerencia clientes cartões FIAP")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(
			summary = "Cria um cliente do Cartão FIAP", 
			description = "Recebe nome e matricula de aluno FIAP que será cliente do Cartão FIAP"
			)
	@PostMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO insert(@RequestBody CreateUpdateClienteDTO createUpdateClienteDTO) {
		return clienteService.create(createUpdateClienteDTO);
	}
	
	
	@Operation(
			summary = "Lista os clientes do Cartão FIAP",
			description = "Lista todos os clientes do Cartão FIAP ou filtra lista por parte de nome informado"
			)
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public List<ClienteDTO> listClientes(
			@RequestParam(required = false) String nome
			){
		return clienteService.listAll(nome);
	}
	
	@Operation(
			summary = "Consulta um cliente do cartão FIAP", 
			description = "Consulta um cliente do cartão FIAP através do identificador do Cliente"
			)
	@GetMapping(
			path = "{id}", 
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ClienteDTO findById(@PathVariable Long id) {
		return clienteService.findbyId(id);
	}
	
	
	@Operation(
			summary = "Exclui um cliente do cartão FIAP",
			description = "Exclui um cliente do cartão FIAP através do identificador do Cliente"
			)
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
	@Operation(
			summary = "Altera um cliente do cartão FIAP", 
			description = "Altera o nome e matricula de um cliente do cartão FIAP através do identificador do Cliente"
			)
	@PutMapping(
			path = "{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ClienteDTO update(
			@PathVariable Long id,
			@RequestBody CreateUpdateClienteDTO createUpdateClienteDTO 
			) {
		return clienteService.update(id, createUpdateClienteDTO);
	}
	
}
