package petz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.DriverFactory;
import util.PropertyReader;
/**
 * @author Sâmea Brazão
 *
 */
public class Petz {
	private WebDriver driver = null;
	DriverFactory factor = new DriverFactory();
	
	@Before
	public void setUp() throws Exception {
		driver = DriverFactory.driver;
		if(driver==null) {
			driver=factor.openBrowser();
		}
	}
	
	/*@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	*/
	
	@Given("Eu abri o site")
	public void eu_abri_o_site() throws Throwable {
		driver.get(new PropertyReader().readProperty("hostname"));
		Thread.sleep(1000);
	}
	
	@And("clico em gatos")
	public void clico_em_gatos() throws InterruptedException {
		WebElement linkGatos = driver.findElement(By.id("menu-item-2390760"));
		linkGatos.click();
		
	}
	
	@Then("verifica pagina")
	public boolean verifica_pagina() throws InterruptedException {
		WebElement title = driver.findElement(By.className("title"));
		Thread.sleep(1000);
		if(title.getText().contains("GATOS")) {
			return true;
		}else {
			return false;
		}
	}
	
	@Given("clico em posse responvel")
	public void clico_em_posse_responvel() {
		WebElement linkPosseResponsavel = driver.findElement(By.id("menu-item-2439257"));
		linkPosseResponsavel.click();
	}
	
	@Then("verifica posse")
	public boolean verifica_posse() throws InterruptedException{
		WebElement title = driver.findElement(By.className("title"));
		Thread.sleep(1000);
		if(title.getText().contains("Posse responsável")) {
			return true;
		}else {
			return false;
		}
	}
	
	@Given("clico em eventos")
	public void clico_em_eventos() {
		WebElement linkEventos = driver.findElement(By.id("menu-item-2436807"));
		linkEventos.click();
	}
	
	@Then("verifica eventos")
	public boolean verifica_eventos() throws InterruptedException{
		WebElement title = driver.findElement(By.className("post-title"));
		Thread.sleep(1000);
		if(title.getText().contains("Eventos - Calendário de eventos pet")) {
			return true;
		}else {
			return false;
		}
	}

}
