package br.com.fiap.cartao.security;

import java.util.Optional;

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
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		if (!usuario.isPresent()) {
			throw new UsernameNotFoundException("User not found with e-mail: " + username);
		}
		return new User(usuario.get().getUsername(), usuario.get().getPassword(), usuario.get().getAuthorities());
	}

}
