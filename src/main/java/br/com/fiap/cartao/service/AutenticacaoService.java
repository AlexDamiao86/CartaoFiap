package br.com.fiap.cartao.service;

import org.springframework.security.core.Authentication;

public interface AutenticacaoService {
	
	Authentication getAuthentication();
	Long getIdClienteByUsername();

}
