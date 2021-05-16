package com.ufcg.psoft.vacineja;

import com.ufcg.psoft.vacineja.service.notificacao.EnviarEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTests {

	@Autowired
	EnviarEmailService enviarEmailService;

	@Test
	public void testEnvioEmail() {
		enviarEmailService.enviar(
				"pedrohmnobrega@gmail.com",
				"Teste Email",
				"Testando o envio do email do app"
		);
	}
}
