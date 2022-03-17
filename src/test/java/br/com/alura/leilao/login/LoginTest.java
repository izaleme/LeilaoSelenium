package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* Descrição dos testes de login:
 * Testes de Autenticação (autenticação de dados válidos ou inválidos) e
 * Testes de Autorização (acesso de recursos exclusivos de users autenticados para users não autenticados) */

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	// VERIFICA SE EFETUOU LOGIN CORRETAMENTE
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuaLogin();

		Assert.assertEquals("fulano", paginaDeLogin.getUsuarioLogado());
		Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
	}

	@Test
	// VERIFICA LOGIN INVÁLIDO
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuaLogin();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		Assert.assertNull(paginaDeLogin.getUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
	}

	@Test
	//VERIFICA SE NÃO ENTROU NA PAGINA RESTRITA DE QUANDO USER ESTA LOGADO
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		paginaDeLogin.navegaParaPaginaDeLances();

		Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}

}
