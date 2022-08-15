package br.com.fiap.cartao.service;

import java.util.Optional;

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
		Optional<Usuario> usuario = usuarioRepository.findByEmail(loggedUser.getName());
		if(usuario.isPresent()) {
			if(usuario.get().getCliente() != null) {
				return usuario.get().getCliente().getId();
			}
		}
		return null;
	}

}
