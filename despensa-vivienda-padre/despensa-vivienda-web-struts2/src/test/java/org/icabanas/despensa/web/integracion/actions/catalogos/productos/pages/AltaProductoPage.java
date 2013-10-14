package org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages;

import java.util.List;

import org.icabanas.despensa.web.integracion.actions.pages.AbstractBasePage;
import org.icabanas.despensa.web.integracion.actions.pages.ErrorPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AltaProductoPage extends AbstractBasePage{

	private static final Logger logger = LoggerFactory.getLogger(AltaProductoPage.class);
	private static final String URI_ALTA_PRODUCTO = "/catalogos/producto/alta";	
		
	public AltaProductoPage() {
		super();
	}
	
	public AltaProductoPage(WebDriver driver) {
		super(driver);
	}

	public AltaProductoPage(WebDriver webdriver, String uriBase) throws PageException {
		super(webdriver,uriBase);		
		logger.debug("Accediendo a la página de alta de producto...");		
		this.driver.get(uriBase + URI_ALTA_PRODUCTO);
		
//		SeleniumWaiter wait = new SeleniumWaiter(this.driver);
//		wait.hastaQueCondicionSeaTrue((getNumeroElementosSelectorMarcas() == 3),10);				
	}	

	public AltaProductoErroresValidacionPage altaProductoInvalido(String codigo,
			String nombre, String marca) throws PageException {
		alta(codigo,nombre,marca);
		return new AltaProductoErroresValidacionPage(driver);
	}
	
	public BusquedaProductosPage altaProducto(String codigo, String nombre, String idMarca) throws PageException{
		alta(codigo,nombre,idMarca);
		return new BusquedaProductosPage(driver);
	}
		
	public ErrorPage altaProductoError(String codigo, String nombre,
			String marca) throws PageException {
		alta(codigo,nombre,marca);
		return new ErrorPage(driver,uriBase);
	}
	
	public BusquedaProductosPage cancelaAlta() throws PageException {
		WebElement btnCancelar = getWebElementById("btnCancelar");
		btnCancelar.click();
		return new BusquedaProductosPage(driver);
	}		
		
	public Integer getNumeroElementosSelectorMarcas() throws PageException {
		WebElement selMarca = getWebElementById("producto.marca.id");
		List<WebElement> options = selMarca.findElements(By.tagName("option"));
		return options.size();
	}
	
			
	private void alta(String codigo, String nombre, String marca) throws PageException {
		WebElement txtCodigo = getWebElementById("producto.codigo");
		WebElement txtNombre = getWebElementById("producto.nombre");
		WebElement selMarca = getWebElementById("producto.marca.id");
		WebElement btnGuardar = getWebElementById("btnGuardar");
		
		// he tenido que cambiar el orden en el que accedo a los valores de los elementos 
		// del formulario. El select he tendi
		List<WebElement> options = selMarca.findElements(By.tagName("option"));
	    for(WebElement option : options){
	        if(option.getText().equals(marca)) {
	            option.click();
	            break;
	        }
	    }
		txtCodigo.sendKeys(codigo);
		txtNombre.sendKeys(nombre);		
		
	    logger.debug("Enviando el formulario de alta de producto...");	    
		btnGuardar.submit();
	}
	
	
}
