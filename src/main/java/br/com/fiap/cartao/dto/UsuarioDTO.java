package br.com.fiap.cartao.dto;

import br.com.fiap.cartao.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {
	
	private Integer id; 
	private String nome;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}

}
