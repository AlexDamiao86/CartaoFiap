package br.com.fiap.cartao.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.AuthDTO;
import br.com.fiap.cartao.dto.CreateUsuarioDTO;
import br.com.fiap.cartao.dto.TokenDTO;
import br.com.fiap.cartao.dto.UsuarioDTO;
import br.com.fiap.cartao.entity.Usuario;
import br.com.fiap.cartao.repository.UsuarioRepository;
import br.com.fiap.cartao.security.JwtTokenUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private AuthenticationManager authenticationManager;
	private JwtTokenUtils jwtTokenUtils;
	private PasswordEncoder passwordEncoder;
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(
			AuthenticationManager authenticationManager,
			JwtTokenUtils jwtTokenUtils, 
			PasswordEncoder passwordEncoder,
			UsuarioRepository usuarioRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtils = jwtTokenUtils;
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepository = usuarioRepository;		
	}
	
	@Override
	public UsuarioDTO create(CreateUsuarioDTO createUsuarioDTO) {
		Usuario usuario = new Usuario(
				createUsuarioDTO.getEmail(), 
				passwordEncoder.encode(createUsuarioDTO.getSenha())
				);

		Usuario novoUsuario = usuarioRepository.save(usuario);
		
		return new UsuarioDTO(novoUsuario);
	}

	@Override
	public TokenDTO login(AuthDTO authDTO) {
		
		String token = null; 
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha()));			
		
			token = jwtTokenUtils.generateToken(authentication);
		
		} catch (DisabledException disabledException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user.disabled");
		} catch (BadCredentialsException badCredentialsException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid.credentials");
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authentication.failed"); 
		}
		
		return new TokenDTO(token);
	}

}
