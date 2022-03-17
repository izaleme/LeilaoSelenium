package br.com.alura.leilao.leiloes;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeiloes = paginaDeLogin.efetuarLogin();	// Devolve o objeto LeiloesPage
		CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregaFormulario();
	}
}