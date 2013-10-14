package org.icabanas.despensa.web.integracion.actions.pages;

//import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.AltaProductoPage;
//import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.BusquedaProductosPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexPage extends AbstractBasePage{

	private static final String URI_INDEX = "";
	private static final Logger logger = LoggerFactory.getLogger(IndexPage.class);
	
	public IndexPage(WebDriver driver, String uriBase) {
		super(driver,uriBase);
		
		logger.debug("Accediendo al index...");
		
		driver.get(uriBase + URI_INDEX);
	}

	public String getTitulo() {
		return driver.getTitle();
	}

//	public AltaProductoPage clickEnAltaProducto() throws PageException {
//		WebElement urlCatalogoProductos = getWebElementById("linkCatalogoProductos");
//		urlCatalogoProductos.click();
//		WebElement btnAltaProducto = getWebElementById("btnNuevo");
//		btnAltaProducto.click();
//		return new AltaProductoPage(driver);
//	}
//
//	public BusquedaProductosPage clickEnBusquedaProducto() throws PageException {
//		WebElement urlListadoProducto = getWebElementById("linkCatalogoProductos");
//		urlListadoProducto.click();
//		return new BusquedaProductosPage(driver);
//	}

	/**
	 * Método que realiza un click sobre el enlace del catálogo de productos.
	 * 
	 * @return
	 * @throws PageException
	 */
	public BusquedaProductosPage clickEnCatalogoProductos() throws PageException {
		WebElement urlListadoProducto = getWebElementById("linkCatalogoProductos");
		urlListadoProducto.click();
		return new BusquedaProductosPage(driver);
	}

}
