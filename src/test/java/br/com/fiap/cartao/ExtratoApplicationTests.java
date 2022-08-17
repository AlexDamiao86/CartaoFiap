package br.com.fiap.cartao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.cartao.controller.ClienteController;
import br.com.fiap.cartao.controller.CompraController;
import br.com.fiap.cartao.controller.ExtratoController;
import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;
import br.com.fiap.cartao.service.CompraServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ExtratoApplicationTests {
	
	@Autowired
	CompraController compraController;
	@Autowired
	ClienteController clienteController;
	@Autowired
	ExtratoController extratoController;
	
	@InjectMocks
	CreateUpdateClienteDTO createUpdateClienteDTO;
	
	@InjectMocks
	CreateCompraDTO createCompraDTO;
	
	@InjectMocks
	CompraServiceImpl compraServiceImpl;
	
	private final String NOME_CLIENTE_TESTE = "TesteExtrato";
	private Long idCompras = 0l; 
	
	@Test
	@Order(1)
	public void extratoAtual() {

		System.out.println("insert cliente extrato");

		createUpdateClienteDTO.setMatricula(0);
		createUpdateClienteDTO.setNome(NOME_CLIENTE_TESTE);
		ClienteDTO cliente = clienteController.insert(createUpdateClienteDTO);
		
		System.out.println(String.format("Cliente %s inserido",cliente.getIdentificador()));
		System.out.println("insert compras extrato");
		
		DateTimeFormatter
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate));
		
		//insere um compra
		createCompraDTO.setDataCompra(dtf.format(localDate));
		createCompraDTO.setIdentificadorCliente(cliente.getIdentificador());
		createCompraDTO.setValorCompra(new BigDecimal(10));
		
		CompraDTO compra = compraController.insert(createCompraDTO);
		
		//insere outra compra
		createCompraDTO.setDataCompra(dtf.format(localDate));
		createCompraDTO.setIdentificadorCliente(cliente.getIdentificador());
		createCompraDTO.setValorCompra(new BigDecimal(10));
		
		compra = compraController.insert(createCompraDTO);
		
		idCompras = compra.getIdentificadorCompra();
		System.out.println("id compra inserida: "+idCompras);
		//cancela um compra
		compraController.cancel(idCompras);
		
		assertEquals(createCompraDTO.getValorCompra(), new BigDecimal(10));
		
	}

	@Test
	@Order(2)
	public void deleteClienteCriado() {

		System.out.println("delete cliente");
		
		Long idCliente = 0l;
		
		List<ClienteDTO> clientes = clienteController.listClientes(NOME_CLIENTE_TESTE);
		for (ClienteDTO clienteDTO : clientes) {
			if(clienteDTO.getNome().equals(NOME_CLIENTE_TESTE)) {
				idCliente = clienteDTO.getIdentificador();
			}
		}
		
		clienteController.delete(idCliente);

		System.out.println(String.format("Cliente %s apagado",idCliente));
		
	}

	@Test
	@Order(3)
	public void extratoAnterior() {

		System.out.println("insert cliente extrato");

		createUpdateClienteDTO.setMatricula(0);
		createUpdateClienteDTO.setNome(NOME_CLIENTE_TESTE);
		ClienteDTO cliente = clienteController.insert(createUpdateClienteDTO);
		
		System.out.println(String.format("Cliente %s inserido",cliente.getIdentificador()));
		System.out.println("insert compras extrato");
		
		//insere um compra
		createCompraDTO.setDataCompra("17/07/2022");
		createCompraDTO.setIdentificadorCliente(cliente.getIdentificador());
		createCompraDTO.setValorCompra(new BigDecimal(10));
		
		CompraDTO compra = compraController.insert(createCompraDTO);
		
		//insere outra compra
		createCompraDTO.setDataCompra("17/07/2022");
		createCompraDTO.setIdentificadorCliente(cliente.getIdentificador());
		createCompraDTO.setValorCompra(new BigDecimal(10));
		
		compra = compraController.insert(createCompraDTO);
		
		idCompras = compra.getIdentificadorCompra();
		System.out.println("id compra inserida: "+idCompras);
		
		//verifica se somente 1 compra foi retornada
		assertEquals(createCompraDTO.getIdentificadorCliente(), cliente.getIdentificador());
		
	}
	
	@Test
	@Order(4)
	public void deleteClienteCriado2() {

		System.out.println("delete cliente");
		
		Long idCliente = 0l;
		
		List<ClienteDTO> clientes = clienteController.listClientes(NOME_CLIENTE_TESTE);
		for (ClienteDTO clienteDTO : clientes) {
			if(clienteDTO.getNome().equals(NOME_CLIENTE_TESTE)) {
				idCliente = clienteDTO.getIdentificador();
			}
		}
		
		clienteController.delete(idCliente);

		System.out.println(String.format("Cliente %s apagado",idCliente));
		
	}
}
