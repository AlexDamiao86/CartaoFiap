package br.com.fiap.cartao.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Cartão FIAP APIs",
				description = "APIs disponibilizadas para realizar as principais funcionalidades " +
				"do sistema de cartões para alunos FIAP. Inclui a manutenção dos clientes dos cartões FIAP, " + 
				"informar compras realizadas pelos alunos nos cartões FIAP, gerar extrato de compras realizadas e " + 
				"informar pagamentos de faturas em aberto nos cartões. ",
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
