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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public UsuarioDTO create(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
		return usuarioService.create(createUsuarioDTO);
	}
	
	@PostMapping("/login")
	public TokenDTO login(@RequestBody AuthDTO authDTO) {
		return usuarioService.login(authDTO);
	}

}
