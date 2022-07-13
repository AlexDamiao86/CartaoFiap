package br.com.fiap.cartao.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.PlasticoCartao;
import br.com.fiap.cartao.repository.CompraRepository;
import br.com.fiap.cartao.repository.PlasticoCartaoRepository;

@Service
public class CompraServiceImpl implements CompraService {
	
	private CompraRepository compraRepository;
	private PlasticoCartaoRepository cartaoRepository;
	
	public CompraServiceImpl(
			CompraRepository compraRepository,
			PlasticoCartaoRepository cartaoRepository
		) {
		this.compraRepository = compraRepository;
		this.cartaoRepository = cartaoRepository;
	}

	@Override
	public CompraDTO create(CreateCompraDTO createCompraDTO) {
		
		PlasticoCartao cartao = cartaoRepository
				.findById(createCompraDTO.getIdPlastico())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado"));
		
		Compra compra = new Compra(
				cartao,
				createCompraDTO.getValor(),
				createCompraDTO.getData()
				);
		Compra compraGravada = compraRepository.save(compra);
		return new CompraDTO(compraGravada, cartao);
	}

	@Override
	public CompraDTO cancel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
