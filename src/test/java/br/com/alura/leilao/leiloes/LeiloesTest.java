package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastroDeLeilao;

	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		this.paginaDeLeiloes = paginaDeLogin.efetuarLogin("fulano", "pass");
		this.paginaDeCadastroDeLeilao = paginaDeLeiloes.carregarFormulario();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
		this.paginaDeCadastroDeLeilao.fechar();
	}

	@Test
	public void deveriaCadastrarLeilao() {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nomeLeilao = "Leilao do dia " + hoje;
		String valorInicial = "500.00";

		// Página de leilões recebe o retorno do método cadastrarLeilao
		this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao(nomeLeilao, valorInicial, hoje);
		Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nomeLeilao, valorInicial, hoje));
	}
	
	@Test
	public void deveriaExecutarValidacaoAoCadastrarLeilaoComDadosInvalidos() {
		this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("", "", "");  // send.keys não aceita valor nulo, portanto enviar uma string vazia

		Assertions.assertFalse(paginaDeCadastroDeLeilao.isPaginaAtual());
		Assertions.assertTrue(paginaDeLeiloes.isPaginaAtual());
		Assertions.assertTrue(paginaDeCadastroDeLeilao.isMensagensDeValidacaoVisiveis());
	}

}