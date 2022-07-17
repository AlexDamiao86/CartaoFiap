package br.com.fiap.cartao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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
import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CompraDTO;
import br.com.fiap.cartao.dto.CreateCompraDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;
import br.com.fiap.cartao.entity.SituacaoCompra;
import br.com.fiap.cartao.service.CompraServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CompraApplicationTests {
	
	@Autowired
	CompraController compraController;
	@Autowired
	ClienteController clienteController;
	
	@InjectMocks
	CreateUpdateClienteDTO createUpdateClienteDTO;
	
	@InjectMocks
	CreateCompraDTO createCompraDTO;
	
	@InjectMocks
	CompraServiceImpl compraServiceImpl;
	
	private final String NOME_CLIENTE_TESTE = "TesteCompras";
	private Long idCompras = 0l; 
	
	@Test
	@Order(1)
	public void insertCancelCompra() {

		System.out.println("insert cliente");

		createUpdateClienteDTO.setMatricula(0);
		createUpdateClienteDTO.setNome(NOME_CLIENTE_TESTE);
		ClienteDTO cliente = clienteController.insert(createUpdateClienteDTO);
		
		System.out.println("insert compras");
		
		createCompraDTO.setDataCompra("17/07/2022");
		createCompraDTO.setIdentificadorCliente(cliente.getIdentificador());
		createCompraDTO.setValorCompra(new BigDecimal(10));
		CompraDTO compra = compraController.insert(createCompraDTO);
		idCompras = compra.getIdentificadorCompra();
		System.out.println("id compra inserida: "+idCompras);
		
		assertEquals(compra.getValorCompra(), createCompraDTO.getValorCompra());
		
		compra = compraController.cancel(idCompras);
		//verifica o nome é o mesmo do teste anterior
		assertEquals(compra.getSituacaoCompra(), SituacaoCompra.ESTORNADA);
		
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

	
}
