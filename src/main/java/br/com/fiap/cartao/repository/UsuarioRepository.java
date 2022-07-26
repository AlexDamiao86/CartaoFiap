package br.com.fiap.cartao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cartao.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findFirstByNomeEquals(String nome);
}
