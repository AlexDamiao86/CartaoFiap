package br.com.fiap.cartao.dto;

import br.com.fiap.cartao.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {
	
	private Long id; 
	private String username;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getUsername();
	}

}
