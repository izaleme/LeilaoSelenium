package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/* Descrição dos testes de login:
 * Testes de Autenticação (autenticação de dados válidos ou inválidos) e
 * Testes de Autorização (acesso de recursos exclusivos de users autenticados para users não autenticados) */

public class LoginTest {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_LANCES = "http://localhost:8080/leiloes/2";

	private WebDriver browser;

	@BeforeAll
	public static void beforeAll() {	// Antes de tudo, seta a variavel do browser
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");	// Caminho do Driver
	}

	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();	// Abre o browser antes de cada teste
	}

	@AfterEach
	public void afterEach() {
		this.browser.quit();	// Fecha o browser depois de cada teste
	}

	@Test
	// VERIFICA SE EFETUOU LOGIN CORRETAMENTE
	public void deveriaEfetuarLoginComDadosValidos() {
		browser.navigate().to(URL_LOGIN);	// Define a página a ser acessada pelo browser

		browser.findElement(By.id("username")).sendKeys("fulano");	// Procura um elemento no browser e em seguida envia um valor
		browser.findElement(By.id("password")).sendKeys("pass");	// Procura o elemento password no browser e em seguida envia o valor "pass" para o elemento
		browser.findElement(By.id("login-form")).submit();			// Submete o formulário

		String nomeUsuarioLogado = browser.findElement(By.id("usuario-logado")).getText();
		Assert.assertEquals("fulano", nomeUsuarioLogado);	// Verifica se apareceu o nome do usuário logado na tela
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));	// Verifica se não está mais na página de login
	}

	@Test
	// VERIFICA LOGIN INVÁLIDO
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		browser.navigate().to(URL_LOGIN);

		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123");
		browser.findElement(By.id("login-form")).submit();
		// Verifica se está na página de erro de login
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));	// Se não encontrar o elemento, retorna uma exception, aqui verificamos se isso aconteceu. 
		Assert.assertTrue(browser.getCurrentUrl().contains(URL_LOGIN));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));	// .getPageSource() devolve uma string com todo o cod fonte da pagina
	}

	@Test
	//VERIFICA SE NÃO ENTROU NA PAGINA RESTRITA DE QUANDO USER ESTA LOGADO
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		browser.navigate().to(URL_LANCES);

		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
	}

}
