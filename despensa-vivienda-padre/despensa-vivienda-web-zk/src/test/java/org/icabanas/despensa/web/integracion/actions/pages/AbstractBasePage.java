package org.icabanas.despensa.web.integracion.actions.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractBasePage {

	/**
	 * URI base de la aplicación.
	 */
	protected String uriBase;
	
	/**
	 * Driver que mantiene una conexión con el navegador
	 */
	protected WebDriver driver;

//	/**
//	 * Las propiedades de textos utilizadas en la página.
//	 */
//	protected Properties props;
	
	
	public AbstractBasePage() {		
	}


	public AbstractBasePage(WebDriver driver, String uriBase) {
		this.driver = driver;
		this.uriBase = uriBase;
	}


	public AbstractBasePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	public AbstractBasePage(WebDriver driver, String uriBase, Properties props) {
//		this(driver,uriBase);
//		this.props = props;
//	}

//	public AbstractBasePage(WebDriver driver, Properties props) {
//		this(driver);
//		this.props = props;
//	}


	/**
	 * 
	 * @param idWebElement
	 * 		Identificador del elemento en el HTML.
	 * 
	 * @return {@link WebElement} Objeto HTML.
	 * @throws PageException 
	 */
	protected WebElement getWebElementById(String idWebElement) throws PageException{
		WebElement element = this.driver.findElement(By.xpath("id('" + idWebElement + "')"));
		if(element == null){
			throw new PageException("No se encontró el elemento con identificador " + idWebElement);
		}
		return element;
	}
	
	/**
	 * Método que comprueba si existe un elemento en la página.
	 * 
	 * @param 
	 * 		id Identificador del elemento.
	 * 
	 * @return
	 * 		true si existe el elemento, false en caso contrario.
	 * 
	 * @throws PageException
	 */
	public boolean existeElemento(String id) throws PageException{
		return (getWebElementById(id) != null);
	}
	
	/**
	 * Método que indica si un elemento de la página está o no habilitado.
	 * 
	 * @param 
	 * 		id Identificador del elemento.
	 * 
	 * @return True, si el elemento está habilitado, o False, en caso contrario.
	 * @throws PageException 
	 */
	public boolean elementoHabilitado(String id) throws PageException {
		WebElement elemento = getWebElementById(id);
		return elemento.isEnabled();
	}
	/**
	 * Método que obtiene el título de la página.
	 * 
	 * @return
	 */
	public String getTituloPagina(){
		return driver.getTitle();
	}

	/**
	 * Método que obtiene el valor de un elemento de la página.
	 * 
	 * @param 
	 * 		idElemento Identificador del elemento de la página.
	 * 
	 * @return Valor que tiene el elmento de la página.
	 * @throws PageException 
	 */
	public String getValorElemento(String idElemento) throws PageException {
		WebElement elemento = getWebElementById(idElemento);
		if(elemento != null){
			return elemento.getValue();
		}
		return null;
	}

	/**
	 * Método que obtiene el valor de un elemento Select de la página.
	 * 
	 * @param 
	 * 		idElemento Identificador del elemento.
	 * 
	 * @return Valor que tiene el elemento de la página.
	 * @throws PageException 
	 */
	public String getValorElementoSelector(String idElemento) throws PageException {
		WebElement elemento = getWebElementById(idElemento);
		if(elemento != null){
			List<WebElement> options = elemento.findElements(By.tagName("option"));
		    for(WebElement option : options){
		        if(option.isSelected()) {
		            return option.getText();
		        }
		    }
		}
		return null;
	}	

//	/**
//	 * Método que comprueba que la página es correcta. Una página es correcta si el título de la página coincide con el esperado y contiene 
//	 * los componentes esperados.
//	 * 
//	 * @return
//	 */
//	public abstract boolean esPaginaCorrecta(); 
}
