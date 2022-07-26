package br.com.fiap.cartao.service;

import br.com.fiap.cartao.dto.AuthDTO;
import br.com.fiap.cartao.dto.CreateUsuarioDTO;
import br.com.fiap.cartao.dto.TokenDTO;
import br.com.fiap.cartao.dto.UsuarioDTO;

public interface UsuarioService {
	
	public UsuarioDTO create(CreateUsuarioDTO createUsuarioDTO);
	public TokenDTO login(AuthDTO authDTO);

}
