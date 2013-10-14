package org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages;

import java.util.List;

import org.icabanas.despensa.web.integracion.actions.pages.AbstractBasePage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdicionProductoPage extends AbstractBasePage{

	private static final Logger logger = LoggerFactory.getLogger(EdicionProductoPage.class);
	private static final String URI_EDICION_PRODUCTO = "/catalogos/producto/editar";
	
	public EdicionProductoPage(WebDriver driver, String uriBase) {
		super(driver,uriBase);
		logger.debug("Accediendo a la página de edición de producto...");		
		//this.driver.get(uriBase + URI_EDICION_PRODUCTO);
	}

	public EdicionProductoPage(WebDriver driver) {
		super(driver);
	}
	

	public BusquedaProductosPage edicionSinSeleccionarProducto() {
		driver.get(uriBase + URI_EDICION_PRODUCTO);
		return new BusquedaProductosPage(driver);
	}

	public BusquedaProductosPage editaProductoQueNoExiste(long idProducto) {
		driver.get(uriBase + URI_EDICION_PRODUCTO + "?id=" + idProducto);
		return new BusquedaProductosPage(driver);
	}

	public EdicionProductoPage editaProducto(long idProducto) {
		driver.get(uriBase + URI_EDICION_PRODUCTO + "?id=" + idProducto);
		return this;
	}

	/**
	 * Método que simula la cancelación de la edición del producto.
	 * 
	 * @return
	 * @throws PageException
	 */
	public BusquedaProductosPage cancelar() throws PageException {
		WebElement btnCancelar = getWebElementById("btnCancelar");
		btnCancelar.click();
		return new BusquedaProductosPage(driver);
	}

	/**
	 * Método que simula la eliminación de un producto.
	 * 
	 * @return
	 * @throws PageException
	 */
	public BusquedaProductosPage eliminar() throws PageException {
		WebElement btnEliminar = getWebElementById("btnEliminar");
		btnEliminar.click();
		return new BusquedaProductosPage(driver);
	}

	/**
	 * Método que simula la actualización de un producto.
	 * 
	 * @param 
	 * 		nombre Nombre del producto.
	 * 
	 * @param 
	 * 		idMarca Identificador de la marca del producto.
	 * 
	 * @return Página de listado de productos.
	 * 
	 * @throws PageException
	 */
	public BusquedaProductosPage actualiza(String nombre, String idMarca) throws PageException {
		WebElement txtNombre = getWebElementById("producto.nombre");
		WebElement selMarca = getWebElementById("producto.marca.id");
		WebElement btnActualizar = getWebElementById("btnActualizar");
				
		List<WebElement> options = selMarca.findElements(By.tagName("option"));
	    for(WebElement option : options){
	        if(option.getAttribute("value").equals(idMarca)) {
	            option.click();
	            break;
	        }
	    }
	    txtNombre.clear();
	    txtNombre.sendKeys(nombre);
	    btnActualizar.submit();
	    
		return new BusquedaProductosPage(driver);
	}	
	
}
