package org.icabanas.despensa.web.integracion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SeleniumWaiter {

	private WebDriver driver;

	public SeleniumWaiter(WebDriver driver) {
		this.driver = driver;
	}

	public void hastaQueTituloPaginaSea(String titulo, long tiempoEsperaEnSegundos) {
		Wait wait = new WebDriverWait(driver, tiempoEsperaEnSegundos);		
		wait.until(SeleniumWaiter.esTituloPagina(titulo));
	}
	
	public void hastaQueCondicionSeaTrue(boolean condicion, int tiempoEsperaEnSegundos) {
		Wait wait = new WebDriverWait(driver, tiempoEsperaEnSegundos);		
		wait.until(SeleniumWaiter.esCondicionTrue(condicion));		
	}

	private static Function<WebDriver, Boolean> esTituloPagina(final String titulo) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver){
				return titulo.equals(driver.getTitle());
			}
		};
	}	

	private static Function esCondicionTrue(final boolean condicion) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver){
				return condicion;
			}
		};
	}

}
