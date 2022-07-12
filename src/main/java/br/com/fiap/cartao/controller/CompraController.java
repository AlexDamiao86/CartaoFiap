package br.com.fiap.cartao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("compras")
public class CompraController {

	private CompraService compraService;
	
	public CompraController(CompraService compraService) {
		this.compraService = compraService;
	}
	
	@Operation(
			summary = "Informa uma compra autorizada no Cartão FIAP", 
			description = "Recebe os dados de uma compra autorizada no Cartão FIAP"
			)
	@PostMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	@ResponseStatus(HttpStatus.CREATED)
	public CompraDTO insert(@RequestBody CreateCompraDTO createCompraDTO) {
		return compraService.create(createCompraDTO);
	}
	
	@Operation(
			summary = "Informa o estorno de uma compra no Cartão FIAP",
			description = "Estorna uma compra no cartão FIAP através do identificador da compra"
			)
	@PatchMapping(
			path = "{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public CompraDTO updateSituacao(@PathVariable Long id) {
		return compraService.cancel(id);
	}
	
}
