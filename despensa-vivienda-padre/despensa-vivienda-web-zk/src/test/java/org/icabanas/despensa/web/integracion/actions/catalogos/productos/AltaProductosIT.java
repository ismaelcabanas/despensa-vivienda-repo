package org.icabanas.despensa.web.integracion.actions.catalogos.productos;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.AltaProductoPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.page.EdicionProductoPage;
import org.icabanas.despensa.web.integracion.actions.pages.ErrorPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;

public class AltaProductosIT extends AbstractProductosIT { // extends BaseDBUnitTest{

	@Test//(timeout=1000)
	public void navega_a_pagina_alta_producto() throws PageException{
		// si se navega a la página de alta de producto
//		AltaProductoPage page = new AltaProductoPage(driver, URI_WEB_APP);
//		page = navegaAltaProductos(driver);
		BusquedaProductosPage catalogoProductosPage = navegaCatalogoProductos(driver);
		AltaProductoPage page = catalogoProductosPage.clickAltaProducto();
		
		// entonces, la página debe ser la que se espera
		paginaAltaCorrecta(page);
		
		// el selector de marcas debe tener 2 elementos
		assertThat(page.getNumeroElementosSelectorMarcas(), equalTo(3));
	}
		
	@Test//(timeout=2000)
	public void cancelacion_alta_producto() throws PageException{
		// si se navega a la página de alta de producto
//		AltaProductoPage page = new AltaProductoPage(driver, URI_WEB_APP);
//		page = navegaAltaProductos(driver);
		BusquedaProductosPage catalogoProductosPage = navegaCatalogoProductos(driver);
		AltaProductoPage page = catalogoProductosPage.clickAltaProducto();
		
		// y el usuario pulsa sobre el botón cancelar
		BusquedaProductosPage listaProductosPage = page.cancelaAlta();

		// entonces, se navega a la página de listado de productos
		paginaListadoCorrecta(listaProductosPage);
		// se comprueba que el mensaje informativo es el que se espera
		assertThat(listaProductosPage.getMensajeInformativo(), notNullValue());
		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("mensaje.cancelacion")));		
	}				
	
	@Test//(timeout=3000)
	public void alta_de_producto() throws PageException{								
		// si se navega a la página de alta de producto
		BusquedaProductosPage catalogoProductosPage = navegaCatalogoProductos(driver);
		AltaProductoPage page = catalogoProductosPage.clickAltaProducto();
		
		// y el usuario da de alta un producto correctamente
		BusquedaProductosPage listaProductosPage = page.altaProducto("cod-0001","Batido","Pascual");
				
		// entonces, se navega a la página de listado de productos
		paginaListadoCorrecta(listaProductosPage);
		// se comprueba que el mensaje informativo es el que se espera
		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("dto.alta.correcto")));
		
		// y comprobamos que el producto se creó
		//BusquedaProductosPage listadoPage = new BusquedaProductosPage(driver, URI_WEB_APP);
		BusquedaProductosPage listadoPage = navegaCatalogoProductos(driver);
		listadoPage = listadoPage.buscar("cod-0001", null, null);
		// editamos el producto
		EdicionProductoPage edicionPage = listadoPage.edita("cod-0001");
		// el contenido del campo nombre debe ser "Batido"
		assertThat(edicionPage.getValorElemento("producto.nombre"), equalTo("Batido"));
		// el contenido del campo marca debe ser "Pascual"
		assertThat(edicionPage.getValorElementoSelector("producto.marca.id"), equalTo("Pascual"));				
	}
	
	@Test
	public void alta_producto_con_codigo_producto_existente() throws PageException{
		// si se navega a la página de alta de producto y se da de alta un producto
		BusquedaProductosPage catalogoProductosPage = navegaCatalogoProductos(driver);
		AltaProductoPage page = catalogoProductosPage.clickAltaProducto();
		
		// y el usuario da de alta un producto
		page.altaProducto("cod-0001","Leche","Pascual");
		
		// si damos de alta otro producto con el mismo código
		page = new AltaProductoPage(driver, URI_WEB_APP);
//		page = navegaAltaProductos(driver);
		ErrorPage errorPage = page.altaProductoError("cod-0001","Tomate","Pascual");
		
		// entonces violaríamos la integridad de la clave primara y debería navegar a la página de error		
		paginaErroresCorrecta(errorPage);
	}	
}
