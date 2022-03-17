package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastroLeilaoPage extends PageObject {

	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);
	}

	// Preenche e submete o formulário de cadastro de leilão	
	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
	    this.browser.findElement(By.id("nome")).sendKeys(nome);
	    this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
	    this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
	    this.browser.findElement(By.id("button-submit")).submit();

	    return new LeiloesPage(browser); // Usa a mesma página do browser
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
	}

	public boolean isMensagensDeValidacaoVisiveis() {
		String pageSource = browser.getPageSource();
		return pageSource.contains("minimo 3 caracteres")
				&& pageSource.contains("não deve estar em branco")
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");

	}

}
