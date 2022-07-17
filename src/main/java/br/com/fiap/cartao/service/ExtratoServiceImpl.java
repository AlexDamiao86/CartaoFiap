package br.com.fiap.cartao.service;


import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.entity.Compra;
import br.com.fiap.cartao.entity.SituacaoCompra;
import br.com.fiap.cartao.repository.ClienteRepository;
import br.com.fiap.cartao.repository.CompraRepository;

@Service
public class ExtratoServiceImpl implements ExtratoService {
	
	private CompraRepository compraRepository;
	private ClienteRepository clienteRepository;
	
	public ExtratoServiceImpl(
			CompraRepository compraRepository,
			ClienteRepository clienteRepository
			) {
		this.compraRepository = compraRepository;
		this.clienteRepository = clienteRepository;
	}

	
	@Override
	public List<CompraDTO> buscaExtrato(Long id, int mes, int ano) {
			
		
		clienteRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
				
		//informa o mes do extrato
		String date = ano+"-"+(mes<10?"0"+mes:mes)+"-01";
		//formata a data
		LocalDate mesInformado = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//pega o dia 1 do mes/ano informado 
		LocalDate dataInicio = mesInformado.with(TemporalAdjusters.firstDayOfMonth());
		//pega o dia último dia do mes/ano informado
		LocalDate dataFim = mesInformado.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("dataInicio:"+dataInicio);
		System.out.println("dataFim:"+dataFim);
		List<Compra> compras = compraRepository.findByCliente_idAndDataBetweenAndSituacao(id,dataInicio, dataFim,SituacaoCompra.AUTORIZADA);
		List<CompraDTO> extrato = new ArrayList<CompraDTO>();
		for (Compra compra : compras) {
			extrato.add(new CompraDTO(compra));
		}
		return extrato;
	}
	
	@Override
	  public Resource load(String filename) {
		
	    try {
	      Path file = java.nio.file.Paths.get("/tmp/extrato"+filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }


}
