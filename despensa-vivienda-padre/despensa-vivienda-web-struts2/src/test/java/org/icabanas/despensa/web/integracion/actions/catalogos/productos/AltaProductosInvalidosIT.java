package org.icabanas.despensa.web.integracion.actions.catalogos.productos;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.AltaProductoErroresValidacionPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.AltaProductoPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Clase que realiza los test de integración para los casos de uso en el que se dan de alta productos inválidos, es decir, 
 * aquellos productos que al dar de alta se producen errores de validación.
 * 
 * @author f009994r
 *
 */
@RunWith(value=Parameterized.class)
public class AltaProductosInvalidosIT extends AbstractProductosIT{	
	
	private String codigo;
	private String nombre;
	private int erroresValidacion;		
	
	public AltaProductosInvalidosIT(String codigo, String nombre,
			int erroresValidacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.erroresValidacion = erroresValidacion;
	}

	@Parameters
	public static Collection<Object[]> data(){
		Object[][] datos = new Object[][] {{"","Leche",1},{"cod-0001","",1},{"","",2}};
		return Arrays.asList(datos);
	}
		
	@Test//(timeout=2000)
	public void deberia_producir_errores_validacion_en_alta_producto_si_usuario_no_introduce_datos_correctos() 
			throws PageException{
		// se navega a la página de alta de producto
//		AltaProductoPage page = new AltaProductoPage(driver, URI_WEB_APP);
		BusquedaProductosPage catalogoProductosPage = navegaCatalogoProductos(driver);
		AltaProductoPage page = catalogoProductosPage.clickAltaProducto();
		
		// si damos de alta un producto inválido
		AltaProductoErroresValidacionPage errorValidacionPage = page.altaProductoInvalido(codigo,nombre,"");
		
		// entonces, la página cargada debe ser la esperada
		paginaAltaCorrecta(errorValidacionPage);
		// el número de errores de validación debe ser 1
		assertThat(errorValidacionPage.getNumeroErrores(),equalTo(erroresValidacion));
		// el valor del campo de texto nombre debe ser Leche
		assertThat(errorValidacionPage.getNombreProducto(), equalTo(nombre));
		// el valor del campo de texto nombre debe ser Leche
		assertThat(errorValidacionPage.getCodigoProducto(), equalTo(codigo));
		// el valor del elemento seleccionado del selector de marcas debe ser Pascual
		assertThat(errorValidacionPage.getMarcaSeleccionada(), equalTo(""));
	}
	
}
