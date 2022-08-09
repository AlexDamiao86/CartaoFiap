package br.com.fiap.cartao.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.fiap.cartao.entity.Usuario;
import br.com.fiap.cartao.repository.UsuarioRepository;

@Component
public class AutenticacaoServiceImpl implements AutenticacaoService {
	
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Long getIdClienteByUsername() {
		Authentication loggedUser = this.getAuthentication();
		Usuario usuario = usuarioRepository.findByEmail(loggedUser.getName());
		return usuario.getCliente().getId();
	}

}
