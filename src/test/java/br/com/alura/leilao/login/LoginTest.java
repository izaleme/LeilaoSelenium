package br.com.alura.leilao.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.alura.leilao.lance.LancesPage;

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
		paginaDeLogin.efetuarLogin("fulano", "pass");

		String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		Assertions.assertEquals("fulano", nomeUsuarioLogado);
		Assertions.assertFalse(paginaDeLogin.isPaginaAtual());
	}


	@Test
	// VERIFICA LOGIN INVÁLIDO
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.efetuarLogin("invalido", "1233");

		Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assertions.assertTrue(paginaDeLogin.isPaginaAtual());
		Assertions.assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
	}

	@Test
	//VERIFICA SE NÃO ENTROU NA PAGINA RESTRITA DE QUANDO USER ESTA LOGADO
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		LancesPage paginaDeLances = new LancesPage();

		Assertions.assertFalse(paginaDeLances.isPaginaAtual());
		Assertions.assertFalse(paginaDeLances.isTituloLeilaoVisivel());

		paginaDeLances.fechar();
	}

}
