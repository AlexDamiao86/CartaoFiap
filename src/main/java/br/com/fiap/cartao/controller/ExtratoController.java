package br.com.fiap.cartao.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.service.ExtratoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("extrato")
public class ExtratoController {

	private ExtratoService extratoService;
	
	public ExtratoController(ExtratoService extratoService) {
		this.extratoService = extratoService;
	}
	
	
	@Operation(
			summary = "Pesquisa compras autorizada no Cartão FIAP", 
			description = "Recebe extrato do mês"
			)
	@GetMapping(
			path = "{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	@ResponseStatus(HttpStatus.FOUND)
	public List<CompraDTO> geraExtratos(@PathVariable("id") Long id) {
		return dadosExtrato(id);
	}
	
	private List<CompraDTO> dadosExtrato(Long id) {
		//Getting the current date value
	      LocalDate currentdate = LocalDate.now();
	      //Getting the current month
	      int currentMonth = currentdate.getMonthValue();
	      //getting the current year
	      int currentYear = currentdate.getYear();
	      
		return (List<CompraDTO>) extratoService.buscaExtrato(id,currentMonth, currentYear);
	}
	
	private List<CompraDTO> dadosExtratoMesAnterior(Long id, int mes, int ano) {
		return (List<CompraDTO>) extratoService.buscaExtrato(id,mes, ano);
	}
	
	@Operation(
			summary = "Pesquisa compras autorizada no Cartão FIAP do mês e ano indicado", 
			description = "Informe um ID de cliente, um mês e um ano Ex: anterior/1/7/2022"
			)
	@GetMapping("anterior/{idCliente}/{mes}/{ano}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<CompraDTO> geraExtratos(
			@PathVariable("idCliente") Long id,
			@PathVariable("mes") int mes,
			@PathVariable("ano") int ano) {
		
		return (List<CompraDTO>) extratoService.buscaExtrato(id,mes,ano);

	}
	
	@Operation(
			summary = "Baixa arquivo com as compras realizadas no mês", 
			description = "Baixa arquivo com as compras realizadas no mês e o total devedor"
			)
	@GetMapping(
			  value = "/arquivo/{id}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
			public @ResponseBody byte[] getArquivoExtratoAtual(@PathVariable("id") long id) throws IOException {
				
				
				String nomeArquivo = "/tmp/extrato"+id+".cvs";
				
				
			    BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
			    writer.append("Data,valor\n");
			    BigDecimal total = BigDecimal.ZERO; 
				for (CompraDTO compra : dadosExtrato(id)) {
				    writer.append(compra.toCSV());
				    total =total.add(compra.getValorCompra());
				}
				
				writer.append("Total: "+total);
				writer.close();

				File file = new File(nomeArquivo);
				//retorna o arquivo preenchido
				InputStream is = new FileInputStream(file);
			    
				return IOUtils.toByteArray(is);
			}

	@Operation(
			summary = "Baixa arquivo com as compras realizadas no mê e ano informado e mostra o total devedor", 
			description = "O ID corresponde ao id do cliente que deseja baixar o extrato e o mes deve conter somente números e o ano deve conter 4 dígitos."
			)
	@GetMapping(
			  value = "/arquivo/anterior/{id}/{mes}/{ano}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
			public @ResponseBody byte[] getArquivoExtratoAnterior(
					@PathVariable("id") long id,
					@PathVariable("mes") int mes,
					@PathVariable("ano") int ano) throws IOException {
				
				
				String nomeArquivo = "/tmp/extrato"+id+".cvs";
				
				
			    BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
			    writer.append("Data,valor\n");
			    BigDecimal total = BigDecimal.ZERO; 
				for (CompraDTO compra : dadosExtratoMesAnterior(id,mes, ano)) {
				    writer.append(compra.toCSV());
				    total =total.add(compra.getValorCompra());
				}
				
				writer.append("Total: "+total);
				writer.close();

				File file = new File(nomeArquivo);
				//retorna o arquivo preenchido
				InputStream is = new FileInputStream(file);
			    
				return IOUtils.toByteArray(is);
			}
	

	
}
