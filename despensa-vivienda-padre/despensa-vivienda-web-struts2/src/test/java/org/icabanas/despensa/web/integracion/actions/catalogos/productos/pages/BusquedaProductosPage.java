package org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.web.integracion.actions.pages.AbstractBasePage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusquedaProductosPage extends AbstractBasePage{

	private static final Logger logger = LoggerFactory.getLogger(BusquedaProductosPage.class);
	private static final String URI_LISTADO_PRODUCTOS = "/catalogos/producto/buscar"; 
		
	public BusquedaProductosPage(WebDriver driver) {
		super(driver);
		
//		logger.debug("URL: " + driver.getCurrentUrl());
//		SeleniumWaiter wait = new SeleniumWaiter(driver);
//		wait.hastaQueTituloPaginaSea(props.getProperty("producto.titulo.listado"), 10);
	}	
	
	public BusquedaProductosPage() {
	}

	public BusquedaProductosPage(WebDriver driver, String uriBase) {
		super(driver,uriBase);		
		this.driver.get(uriBase + URI_LISTADO_PRODUCTOS);
	}			
	
	public String getMensajeInformativo() throws PageException {
		String text;
		try{
			// accedo al elemento que contiene los mensajes
			WebElement divMensajes = getWebElementById("divMensajes");
			//return driver.findElement(By.xpath("id('textoInformativo')")).getText();
			WebElement ulElement = divMensajes.findElement(By.tagName("ul"));
			WebElement liElement = ulElement.findElement(By.tagName("li"));
			
			logger .debug("Mensaje Informativo: " + liElement.getText());
			text = liElement.getText();
		}
		catch (NoSuchElementException e){
			text = null;
		}
		
		return text;
	}

	/**
	 * Método que devuelve el número de registros de la búsqueda contando el número de filas de la 
	 * taba resultado.
	 * 
	 * @return El número de registros de la búsqueda.
	 * @throws PageException
	 */
	public int getNumeroRegistrosBusqueda() throws PageException {
		List<WebElement> filas = driver.findElements(By.xpath("id('resultado')/tbody/tr"));
		//List<WebElement> filas = driver.findElements(By.xpath("table[@id='resultado']//tr"));
		
		if(filas != null){
			return filas.size();
		}
		
		return -1;
	}

	/**
	 * Método que realiza búsquedas por código de producto.
	 * 
	 * @param 
	 * 		codigo El código de producto.
	 * 
	 * @return
	 * @throws PageException Si se produce algún error
	 */
	public BusquedaProductosPage buscarPorCodigo(String codigo) throws PageException {
		return buscar(codigo, null, null);
	}
	
	public BusquedaProductosPage buscar() throws PageException {
		return buscar(null,null,null);
	}
	
	public BusquedaProductosPage buscar(String codigo, String nombre,
			String marca) throws PageException {
		if(codigo != null){
			rellenaCampo("filtro.codigo", codigo);
		}
		if(nombre != null){
			rellenaCampo("filtro.nombre", nombre);
		}
		if(marca != null){
			rellenaSelector("filtro.marca", marca);
		}				
		WebElement btnBuscar = driver.findElement(By.id("btnBuscar"));
		
		btnBuscar.submit();		
		
		return new BusquedaProductosPage(driver);
	}
	
	/**
	 * Método que pagina a la siguiente página. Busca en la página el enlace "Siguiente" y lo ejecuta.
	 * 
	 * @return
	 */
	public BusquedaProductosPage avanzaPagina() {
		WebElement ahrefElement = driver.findElement(By.id("pagSiguiente"));
		ahrefElement.click();
				
		return new BusquedaProductosPage(driver);
	}		
	
	public BusquedaProductosPage retrocedePagina() {
		WebElement ahrefElement = driver.findElement(By.id("pagAnterior"));
		ahrefElement.click();
				
		return new BusquedaProductosPage(driver);
	}		
	
	public BusquedaProductosPage ultimaPagina() {
		WebElement ahrefElement = driver.findElement(By.id("pagUltimo"));
		ahrefElement.click();
				
		return new BusquedaProductosPage(driver);
	}
	
	public BusquedaProductosPage primeraPagina() {
		WebElement ahrefElement = driver.findElement(By.id("pagPrimero"));
		ahrefElement.click();
				
		return new BusquedaProductosPage(driver);
	}

	public BusquedaProductosPage irPagina(int pagina) {
		WebElement ahrefElement = driver.findElement(By.id("pag"+pagina));
		ahrefElement.click();
				
		return new BusquedaProductosPage(driver);
	}

	
	/**
	 * Método que edita un producto del listado de productos cuyo código sea <code>codigo</code>.
	 * 
	 * @param 
	 * 		codigo El código del producto.
	 * 
	 * @return
	 */
	public EdicionProductoPage edita(String codigo) {
		List<WebElement> td_collection = driver.findElements(By.xpath("id('resultado')/tbody/tr/td"));
		WebElement link = null;
		for(WebElement tdElement : td_collection){
			if(tdElement.getText().equals(codigo)){
				link = tdElement.findElement(By.tagName("a"));
				break;
			}
		}
		if(link != null){
			link.click();
		}
		return new EdicionProductoPage(driver);
	}

	public List<ProductoDto> getResultadoBusqueda() {
		List<ProductoDto> resultadoBusqueda = new ArrayList<ProductoDto>();
		WebElement table_element = driver.findElement(By.id("resultado"));
		List<WebElement> tr_collection=table_element.findElements(By.xpath("id('resultado')/tbody/tr"));
		for(WebElement trElement : tr_collection){
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            ProductoDto dto = new ProductoDto();
            dto.setCodigo(td_collection.get(0).getText());
            dto.setNombre(td_collection.get(1).getText());
            String marca = td_collection.get(2).getText();
            if(marca != null && !marca.equals("")){
            	dto.setMarca(new MarcaDto(td_collection.get(2).getText()));
            }            
			resultadoBusqueda.add(dto);
        }
		return resultadoBusqueda;
	}		
	
	public String getMensajePaginacion() {
		WebElement msgRegistrosEncontradosElement = driver.findElement(By.id("msgRegistrosEncontrados"));
		return msgRegistrosEncontradosElement.getText();
	}

	private void rellenaCampo(String id,String valor) throws PageException {
		WebElement webElement = getWebElementById(id);
		webElement.sendKeys(valor);
	}	

	private void rellenaSelector(String idSelector, String valor) throws PageException {
		WebElement selMarca = getWebElementById(idSelector);
		List<WebElement> options = selMarca.findElements(By.tagName("option"));
		for(WebElement option : options){
	        if(option.getText().equals(valor)) {	        	
	            option.click();
	            logger.debug("Opción selector seleccionada: " + option.getText());
	            break;
	        }
	    }
	}

	public AltaProductoPage clickAltaProducto() throws PageException {
		WebElement btnNuevo = getWebElementById("btnNuevo");
		
		btnNuevo.click();
		
		return new AltaProductoPage(driver);
	}	
	
}
