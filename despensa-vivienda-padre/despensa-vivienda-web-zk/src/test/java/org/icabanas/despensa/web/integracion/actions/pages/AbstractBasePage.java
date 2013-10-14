package org.icabanas.despensa.web.integracion.actions.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractBasePage {

	/**
	 * URI base de la aplicaci�n.
	 */
	protected String uriBase;
	
	/**
	 * Driver que mantiene una conexi�n con el navegador
	 */
	protected WebDriver driver;

//	/**
//	 * Las propiedades de textos utilizadas en la p�gina.
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
			throw new PageException("No se encontr� el elemento con identificador " + idWebElement);
		}
		return element;
	}
	
	/**
	 * M�todo que comprueba si existe un elemento en la p�gina.
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
	 * M�todo que indica si un elemento de la p�gina est� o no habilitado.
	 * 
	 * @param 
	 * 		id Identificador del elemento.
	 * 
	 * @return True, si el elemento est� habilitado, o False, en caso contrario.
	 * @throws PageException 
	 */
	public boolean elementoHabilitado(String id) throws PageException {
		WebElement elemento = getWebElementById(id);
		return elemento.isEnabled();
	}
	/**
	 * M�todo que obtiene el t�tulo de la p�gina.
	 * 
	 * @return
	 */
	public String getTituloPagina(){
		return driver.getTitle();
	}

	/**
	 * M�todo que obtiene el valor de un elemento de la p�gina.
	 * 
	 * @param 
	 * 		idElemento Identificador del elemento de la p�gina.
	 * 
	 * @return Valor que tiene el elmento de la p�gina.
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
	 * M�todo que obtiene el valor de un elemento Select de la p�gina.
	 * 
	 * @param 
	 * 		idElemento Identificador del elemento.
	 * 
	 * @return Valor que tiene el elemento de la p�gina.
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
//	 * M�todo que comprueba que la p�gina es correcta. Una p�gina es correcta si el t�tulo de la p�gina coincide con el esperado y contiene 
//	 * los componentes esperados.
//	 * 
//	 * @return
//	 */
//	public abstract boolean esPaginaCorrecta(); 
}
