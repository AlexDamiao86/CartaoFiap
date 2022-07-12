package br.com.fiap.cartao.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.entity.Cliente;
import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.PlasticoCartao;
import br.com.fiap.cartao.repository.ClienteRepository;
import br.com.fiap.cartao.repository.CompraRepository;
import br.com.fiap.cartao.repository.PlasticoCartaoRepository;

@Service
public class CompraServiceImpl implements CompraService {
	
	private CompraRepository compraRepository;
	private ClienteRepository clienteRepository;
	private PlasticoCartaoRepository cartaoRepository;
	
	public CompraServiceImpl(
			CompraRepository compraRepository,
			ClienteRepository clienteRepository,
			PlasticoCartaoRepository cartaoRepository
		) {
		this.compraRepository = compraRepository;
		this.clienteRepository = clienteRepository;
		this.cartaoRepository = cartaoRepository;
	}


	@Override
	public CompraDTO create(CreateCompraDTO createCompraDTO) {
		
		Cliente cliente = clienteRepository
				.findById(createCompraDTO.getIdCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
		PlasticoCartao cartao = cartaoRepository
				.findById(createCompraDTO.getIdPlastico())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado"));
		
		Compra compra = new Compra(
				cliente,
				cartao,
				createCompraDTO.getValor(),
				createCompraDTO.getData()
				);
		Compra compraGravada = compraRepository.save(compra);
		return new CompraDTO(compraGravada, cliente, cartao);
	}

	@Override
	public CompraDTO cancel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
