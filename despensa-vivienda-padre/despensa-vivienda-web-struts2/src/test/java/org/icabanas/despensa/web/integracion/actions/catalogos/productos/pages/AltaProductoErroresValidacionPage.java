package org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages;

import java.util.List;

import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AltaProductoErroresValidacionPage extends AltaProductoPage {

	private WebElement divErrores;
	
	public AltaProductoErroresValidacionPage(WebDriver driver) {
		super(driver);
		try{
			divErrores = driver.findElement(By.xpath("id('divErrores')"));
		}
		catch(Exception e){
			System.out.println("LLLLL");
		}
	}
	
	public Integer getNumeroErrores() throws PageException {
		WebElement ul = divErrores.findElement(By.tagName("ul"));
		if(ul!= null){
			List<WebElement> listLi = ul.findElements(By.tagName("li"));
			if(listLi != null){
				return listLi.size();
			}
			else{
				throw new PageException("No existe elemento LI en la página.");
			}
		}
		else{
			throw new PageException("No existe elemento UL en la página.");
		}		
	}

	public String getMarcaSeleccionada() throws PageException {
		WebElement selMarca = getWebElementById("producto.marca.id");
		Select selectMarca = new Select(selMarca);
		WebElement marcaSeleccionada = selectMarca.getFirstSelectedOption();
		return marcaSeleccionada.getText();
	}

	public String getNombreProducto() throws PageException {
		WebElement txtNombre = getWebElementById("producto.nombre");
		return txtNombre.getValue();
	}

	public String getCodigoProducto() throws PageException {
		WebElement txtCodigo = getWebElementById("producto.codigo");
		return txtCodigo.getValue();
	}

}
