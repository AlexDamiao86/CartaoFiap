package br.com.fiap.cartao;


import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.cartao.controller.UsuarioController;
import br.com.fiap.cartao.dto.AuthDTO;
import br.com.fiap.cartao.dto.TokenDTO;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class LoginApplicationTests {
	
	@Autowired
	UsuarioController usuarioController;
	
	@InjectMocks
	AuthDTO authDTO;
	
	@Test
	@Order(1)
	public void login() {
		System.out.println("Login");
		
		authDTO.setEmail("gestor@fiap.com.br");
		authDTO.setSenha("123456");

		TokenDTO token = usuarioController.login(authDTO);
		
		assertNotEquals("", token.getToken());
	}

}
