package br.com.fiap.cartao.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.dto.AuthDTO;
import br.com.fiap.cartao.dto.CreateUsuarioDTO;
import br.com.fiap.cartao.dto.TokenDTO;
import br.com.fiap.cartao.dto.UsuarioDTO;
import br.com.fiap.cartao.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "/usuarios", description = "Cria e autentica usuarios API Cartão FIAP")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Operation(
			summary = "Criar usuário da API Cartão FIAP", 
			description = "Criar usuário da API Cartão FIAP"
			)
	@PostMapping
	@SecurityRequirement(name = "Bearer Authentication")
	public UsuarioDTO create(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
		return usuarioService.create(createUsuarioDTO);
	}
	
	@Operation(
			summary = "Autenticar usuário na API Cartão FIAP", 
			description = "Recebe e-mail e senha do usuário da API Cartão FIAP e devolve JWT Token"
			)
	@PostMapping("/login")
	public TokenDTO login(@RequestBody AuthDTO authDTO) {
		return usuarioService.login(authDTO);
	}

}
