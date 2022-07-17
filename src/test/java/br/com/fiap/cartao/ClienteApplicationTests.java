package br.com.fiap.cartao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.controller.ClienteController;
import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ClienteApplicationTests {
	
	@Autowired
	ClienteController clienteController;
	
	@InjectMocks
	CreateUpdateClienteDTO createUpdateClienteDTO;
	
	private final String NOME_CLIENTE_TESTE_UPDATED = "TesteSpring UPDATED";
	private final String NOME_CLIENTE_TESTE = "TesteSpring";
	private Long idCliente = 0l;
	
	@Test
	@Order(1)
	public void insertCliente() {
		System.out.println("insertCliente");
		createUpdateClienteDTO.setMatricula(0);
		createUpdateClienteDTO.setNome(NOME_CLIENTE_TESTE);
		ClienteDTO cliente = clienteController.insert(createUpdateClienteDTO);
		System.out.println("Id do cliente cadastrado:"+ cliente.getIdentificador());
		idCliente = cliente.getIdentificador();
		assertEquals(cliente.getNome(), createUpdateClienteDTO.getNome());
		
	}

	@Test
	@Order(2)
	public void updateCliente() {
		
		
		List<ClienteDTO> clientes = clienteController.listClientes(NOME_CLIENTE_TESTE);
		
		for (ClienteDTO clienteDTO : clientes) {
			if(clienteDTO.getNome().equals(NOME_CLIENTE_TESTE)) {
				idCliente = clienteDTO.getIdentificador();
			}
		}
		
		System.out.println("updateCliente "+idCliente);
		
		ClienteDTO cliente = clienteController.findById(idCliente);
		//verifica o nome é o mesmo do teste anterior
		assertEquals(cliente.getNome(), NOME_CLIENTE_TESTE);

		createUpdateClienteDTO.setMatricula(cliente.getMatricula());
		createUpdateClienteDTO.setNome(NOME_CLIENTE_TESTE_UPDATED);
		
		cliente = clienteController.update(idCliente, createUpdateClienteDTO);
		//verifica o nome foi atualizado
		assertEquals(cliente.getNome(), NOME_CLIENTE_TESTE_UPDATED);
		
	}
	
	@Test
	@Order(3)
	public void deleteCliente() {
		
		List<ClienteDTO> clientes = clienteController.listClientes(NOME_CLIENTE_TESTE_UPDATED);
		
		Long idCliente = 0l;
		
		for (ClienteDTO clienteDTO : clientes) {
			if(clienteDTO.getNome().equals(NOME_CLIENTE_TESTE_UPDATED)) {
				idCliente = clienteDTO.getIdentificador();
			}
		}
		
		System.out.println("deleteCliente "+idCliente);
		clienteController.delete(idCliente);
		
		try {
			ClienteDTO cliente = clienteController.findById(idCliente);

		} catch (ResponseStatusException e) {
			assertEquals(e.getMessage(), HttpStatus.NOT_FOUND.value()+ " "+HttpStatus.NOT_FOUND.name());	
		}
		
		
		 

		
	}

}
