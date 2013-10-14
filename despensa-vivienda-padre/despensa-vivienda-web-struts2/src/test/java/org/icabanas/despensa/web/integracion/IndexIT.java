package org.icabanas.despensa.web.integracion;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.AltaProductoPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.pages.IndexPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexIT extends AbstractBaseIT{

	private static final String[] PROPS_GLOBAL = {"/global.properties","/org/icabanas/despensa/web/actions/catalogos/productos/messages.properties"};
	private static final Logger logger = LoggerFactory.getLogger(IndexIT.class);		

	@Override
	protected String[] getFicheroPropiedades() {
		return PROPS_GLOBAL;
	}

//	@Test//(timeout=1000)
//	public void navega_a_index(){
//		// si se accede al index
//		IndexPage page = new IndexPage(driver, URI_WEB_APP);
//
//		// entonces, el título de la página es el esperado
//		assertThat(page.getTitulo(), equalTo(props.getProperty("texto.titulo.aplicacion")));
//				
//	}
//	
//	@Test
//	public void navega_a_alta_productos() throws PageException{
//		// si accedemos al index
//		IndexPage indexPage = new IndexPage(driver, URI_WEB_APP);
//		
//		// y pinchamos sobre el enlace de alta de productos
//		AltaProductoPage altaProductoPage = indexPage.clickEnAltaProducto();
//		
//		// entonces entramos en el alta de producto
//		assertThat(altaProductoPage.getTituloPagina(), equalTo(props.getProperty("producto.titulo.alta")));
//	}
//	
//	@Test
//	public void navega_a_busqueda_productos() throws PageException{
//		// si accedemos al index
//		IndexPage indexPage = new IndexPage(driver, URI_WEB_APP);
//		
//		// y pinchamos sobre el enlace de alta de productos
//		BusquedaProductosPage listaProductosPage = indexPage.clickEnBusquedaProducto();
//		
//		// entonces entramos en el alta de producto
//		assertThat(listaProductosPage.getTituloPagina(), equalTo(props.getProperty("producto.titulo.listado")));
//	}

	@Override
	protected String[] getDatasets() {
		return null;
	}
}
