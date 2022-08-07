package br.com.fiap.cartao.security;

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
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found with e-mail: " + username);
		}
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}

}
