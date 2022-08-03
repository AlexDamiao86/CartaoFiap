package br.com.fiap.cartao.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SecurityScheme(
		name = "Bearer Authentication", 
		type = SecuritySchemeType.HTTP, 
		bearerFormat = "JWT",
		scheme = "bearer",
		description = "Um token JWT é obrigatório para executar algumas operações dessa API. " +
		   "Este token poderá ser obtido através da operação /usuarios/login")
@OpenAPIDefinition(
		info = @Info(
				title = "Cartão FIAP APIs",
				description = "APIs disponibilizadas para realizar as principais funcionalidades " +
				"do sistema de cartões para alunos FIAP. ",
				contact = @Contact(
						name = "GitHub", 
						url = "https://github.com/AlexDamiao86/CartaoFiap/"		
						),
				license = @License(
						name = "MIT License",
						url = "https://api.github.com/licenses/mit"
						)
				)
		)
public class OpenAPIConfiguration {

}
