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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.service.ExtratoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("extrato")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "/extrato", description = "Emite extratos de cartões FIAP")
public class ExtratoController {

	private ExtratoService extratoService;
	
	public ExtratoController(ExtratoService extratoService) {
		this.extratoService = extratoService;
	}
	
	
	@Operation(
			summary = "Pesquisa compras pendentes de pagamento no Cartão FIAP no mês atual", 
			description = "Recebe o identificador do cliente para devolver as compras pendentes de pagamento (extrato) do mês atual"
			)
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public List<CompraDTO> geraExtratos() {
		return dadosExtrato();
	}
	
	private List<CompraDTO> dadosExtrato() {
		//Getting the current date value
	      LocalDate currentdate = LocalDate.now();
	      //Getting the current month
	      int currentMonth = currentdate.getMonthValue();
	      //getting the current year
	      int currentYear = currentdate.getYear();
	      
		return (List<CompraDTO>) extratoService.getByMonthAndYear(currentMonth, currentYear);
	}
	
	private List<CompraDTO> dadosExtratoMesAnterior(int mes, int ano) {
		return (List<CompraDTO>) extratoService.getByMonthAndYear(mes, ano);
	}
	
	@Operation(
			summary = "Pesquisa compras pendentes de pagamento no Cartão FIAP no mês/ano indicado", 
			description = "Informe um identificador de cliente, um mês e um ano Ex: extrato/1/7/2022"
			)
	@GetMapping("{mes}/{ano}")
	public List<CompraDTO> geraExtratos(
			@PathVariable("mes") int mes,
			@PathVariable("ano") int ano) {
		
		return (List<CompraDTO>) extratoService.getByMonthAndYear(mes,ano);

	}
	
	@Operation(
			summary = "Baixa arquivo com as compras realizadas no mês atual e mostra o saldo devedor", 
			description = "Baixa arquivo com as compras realizadas no mês e o saldo devedor"
			)
	@GetMapping(
			  value = "/arquivo",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
			public @ResponseBody byte[] getArquivoExtratoAtual() throws IOException {
				
				
				String nomeArquivo = "/tmp/extrato.cvs";
				
				
			    BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
			    writer.append("Data,valor\n");
			    BigDecimal total = BigDecimal.ZERO; 
				for (CompraDTO compra : dadosExtrato()) {
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
			summary = "Baixa arquivo com as compras realizadas no mês/ano informado e mostra o saldo devedor", 
			description = "Recebe o identificador do cliente e o mês/ano para baixar arquivo com compras realizadas. Ex.: extrato/arquivo/1/6/2022"
			)
	@GetMapping(
			  value = "/arquivo/{mes}/{ano}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
			public @ResponseBody byte[] getArquivoExtratoAnterior(
					@PathVariable("mes") int mes,
					@PathVariable("ano") int ano) throws IOException {
				
				
				String nomeArquivo = "/tmp/extrato.cvs";
				
				
			    BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
			    writer.append("Data,valor\n");
			    BigDecimal total = BigDecimal.ZERO; 
				for (CompraDTO compra : dadosExtratoMesAnterior(mes, ano)) {
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
