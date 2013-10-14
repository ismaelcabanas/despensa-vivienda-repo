package org.icabanas.despensa.web.integracion.actions.catalogos.productos;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.web.integracion.AbstractBaseIT;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.AltaProductoPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.EdicionProductoPage;
import org.icabanas.despensa.web.integracion.actions.pages.IndexPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.openqa.selenium.WebDriver;

public abstract class AbstractProductosIT extends AbstractBaseIT{

	private static final String[] PROPS_PRODUCTO = {"/org/icabanas/despensa/web/actions/catalogos/productos/messages.properties","/global.properties"};
	private static final String[] DATASET = {"/data/marcas.xml","/data/productos.xml"};
	
	protected AltaProductoPage page = null;
	
	@Override
	protected String[] getDatasets() {
		return DATASET;
	}
	
	@Override
	protected String[] getFicheroPropiedades() {
		return PROPS_PRODUCTO;
	}		
	
	/**
	 * Método que navega a la página de catálogo de productos.
	 * 
	 * @param driver
	 * @return
	 * @throws PageException
	 */
	protected BusquedaProductosPage navegaCatalogoProductos(WebDriver driver) throws PageException {
		IndexPage indexPage = new IndexPage(driver, URI_WEB_APP);
		
		BusquedaProductosPage busquedaPage = indexPage.clickEnCatalogoProductos();
		
		return busquedaPage;
	}	

	protected void paginaEdicionCorrecta(final EdicionProductoPage page) throws PageException{		
		// el título de la página es el esperado
		assertThat(page.getTituloPagina(), equalTo(props.getProperty("producto.titulo.edicion")));
		
		// debe existir el campo de texto 'codigo'
		assertThat(page.existeElemento("producto.codigo"), is(true));		
		// además, el campo de texto 'codigo' debe estar deshabilitado
		assertThat(page.elementoHabilitado("producto.codigo"), is(false));		
		// debe existir el campo de texto 'nombre'
		assertThat(page.existeElemento("producto.nombre"), is(true));
		// debe existir el campo selector 'marcas'
		assertThat(page.existeElemento("producto.marca.id"), is(true));
		// debe existir el botón 'cancelar'
		assertThat(page.existeElemento("btnCancelar"), is(true));
		// debe existir el botón 'actualizar'
		assertThat(page.existeElemento("btnActualizar"), is(true));
		// debe existir el botón 'eliminar'
		assertThat(page.existeElemento("btnEliminar"), is(true));
	}
	
	protected void paginaListadoCorrecta(final BusquedaProductosPage page) throws PageException{		
		// el título de la página es el esperado
		assertThat(page.getTituloPagina(), equalTo(props.getProperty("producto.titulo.listado")));
		
		// debe existir el campo de texto 'codigo'
		assertThat(page.existeElemento("filtro.codigo"), is(true));		
		// debe existir el campo de texto 'nombre'
		assertThat(page.existeElemento("filtro.nombre"), is(true));
		// debe existir el campo selector 'marcas'
		assertThat(page.existeElemento("filtro.marca"), is(true));
		// debe existir el botón 'buscar'
		assertThat(page.existeElemento("btnBuscar"), is(true));
		// debe existir el botón 'nuevo'
		assertThat(page.existeElemento("btnNuevo"), is(true));
	}
		
	protected void paginaAltaCorrecta(final AltaProductoPage page) throws PageException{		
		// el título de la página es el esperado
		assertThat(page.getTituloPagina(), equalTo(props.getProperty("producto.titulo.alta")));
		
		// debe existir el campo de texto 'codigo'
		assertThat(page.existeElemento("producto.codigo"), is(true));		
		// debe existir el campo de texto 'nombre'
		assertThat(page.existeElemento("producto.nombre"), is(true));
		// debe existir el campo selector 'marcas'
		assertThat(page.existeElemento("producto.marca.id"), is(true));
		// debe existir el botón 'cancelar'
		assertThat(page.existeElemento("btnCancelar"), is(true));
		// debe existir el botón 'guardar'
		assertThat(page.existeElemento("btnGuardar"), is(true));
	}
}
