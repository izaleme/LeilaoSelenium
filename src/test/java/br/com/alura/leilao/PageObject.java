package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
	
	protected WebDriver browser;

	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		if (browser != null) {
			this.browser = browser;
		} else {
			this.browser = new ChromeDriver();
		}

		// Para lidar com requisições AJAX ou JS que podem adiar o carregamento dos elementos da página, implementamos:
		//this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public void fechar() {
		this.browser.quit();
	}

}
