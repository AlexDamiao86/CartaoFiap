package br.com.fiap.cartao.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.cartao.entity.Usuario;
import br.com.fiap.cartao.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
	
	public JwtUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findFirstByNomeEquals(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(usuario.getNome(), usuario.getSenha(), new ArrayList<>());
	}

}
