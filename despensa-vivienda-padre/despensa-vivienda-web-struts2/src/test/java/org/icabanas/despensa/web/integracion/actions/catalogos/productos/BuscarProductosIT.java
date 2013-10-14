package org.icabanas.despensa.web.integracion.actions.catalogos.productos;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuscarProductosIT extends AbstractProductosIT{

	private static Logger logger = LoggerFactory.getLogger(BuscarProductosIT.class);
	
	@Test
	public void busqueda_de_producto_por_codigo_que_existe() throws PageException{
		// preparaci�n
		ProductoDto productoEsperado = new ProductoDto(1L, "cod-1", "Leche", new MarcaDto("Pascual"));
		
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		//BusquedaProductosPage listaPage = new BusquedaProductosPage(driver,URI_WEB_APP);
		
		// si el usuario realiza una b�squeda por c�digo de producto existente
		listaPage = listaPage.buscarPorCodigo("cod-1");
		
		// entonces la aplicaci�n se queda en la p�gina de listados
		paginaListadoCorrecta(listaPage);			
		// no sale ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(1));
		// el resultado de la b�squeda es el correcto.
		assertThat(listaPage.getResultadoBusqueda(), hasItem(productoEsperado));
	}
	
	@Test
	public void busqueda_de_productos_por_nombre() throws PageException{
		// preparaci�n
		ProductoDto productoEsperado1 = new ProductoDto(1L, "cod-1", "Leche", new MarcaDto("Pascual"));
		ProductoDto productoEsperado2 = new ProductoDto(2L, "cod-2", "Leche", new MarcaDto("Central Lechera Asturiana"));
		
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		
		// si el usuario realiza una b�squeda por c�digo de producto existente
		listaPage = listaPage.buscar("","ech","");
		
		// entonces la aplicaci�n se queda en la p�gina de listados
		paginaListadoCorrecta(listaPage);	
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void busqueda_de_productos_que_no_devuelve_registros() throws PageException{
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		
		// si el usuario realiza una b�squeda que no va a devolver registros
		listaPage = listaPage.buscar("","ich","");
		
		// entonces la aplicaci�n se queda en la p�gina de listados
		paginaListadoCorrecta(listaPage);	
		// no sale ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(),equalTo(props.getProperty("mensaje.busqueda.sin.registros")));
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(0));
	}
	

}
