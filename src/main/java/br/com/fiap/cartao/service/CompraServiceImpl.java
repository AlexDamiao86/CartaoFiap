package br.com.fiap.cartao.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.entity.Cliente;
import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.SituacaoCompra;
import br.com.fiap.cartao.repository.ClienteRepository;
import br.com.fiap.cartao.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService {
	
	private CompraRepository compraRepository;
	private ClienteRepository clienteRepository;
	
	public CompraServiceImpl(
			CompraRepository compraRepository,
			ClienteRepository clienteRepository
			) {
		this.compraRepository = compraRepository;
		this.clienteRepository = clienteRepository;
	}

	@Override
	public CompraDTO create(CreateCompraDTO createCompraDTO) {
			
		Cliente cliente = clienteRepository
				.findById(createCompraDTO.getIdentificadorCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
				
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate dataCompra = LocalDate.parse(createCompraDTO.getDataCompra(), dataFormatter);
		
		Compra compra = new Compra(cliente, dataCompra, createCompraDTO.getValorCompra());
		Compra compraGravada = compraRepository.save(compra);
		return new CompraDTO(compraGravada);
	}

	@Override
	public CompraDTO cancel(Long identificadorCompra) {
		Compra compra = compraRepository
				.findById(identificadorCompra)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra nao encontrada"));
		
		compra.setSituacao(SituacaoCompra.ESTORNADA);
		Compra compraEstornada = compraRepository.save(compra);
		return new CompraDTO(compraEstornada);
	}

}
