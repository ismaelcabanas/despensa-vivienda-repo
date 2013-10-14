package org.icabanas.despensa.web.integracion.actions.catalogos.productos;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;

/**
 * Clase que realiza tests de integraci�n para comprobar la paginaci�n en las b�squedas. 
 * El n�mero de resultados por p�gina est� configurado en el fichero properties de configuraci�n de 
 * la aplicaci�n.
 * 
 * @author f009994r
 *
 */
public class PaginaBusquedaProductosIT extends AbstractProductosIT {

	@Test
	public void deberia_avanzar_pagina() throws PageException{
		// PREPARACI�N
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");	
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una b�squeda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCI�N
		// el usuario pincha sobre la p�gina siguiente (ser�a la p�gina 2)
		listaPage = listaPage.avanzaPagina();
		
		// VERIFICACI�N
		// entonces, la aplicaci�n se queda en la p�gina de b�squeda
		paginaListadoCorrecta(listaPage);
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginaci�n
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_retroceder_pagina() throws PageException{
		// PREPARACI�N
		ProductoDto productoEsperado1 = new ProductoDto(101L, "cod-1", "Leche", new MarcaDto("Pascual"));
		ProductoDto productoEsperado2 = new ProductoDto(102L, "cod-2", "Leche", new MarcaDto("Central Lechera Asturiana"));		
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una b�squeda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		listaPage = listaPage.avanzaPagina();
		
		// EJECUCI�N
		// el usuario pincha sobre la p�gina siguiente (ser�a la p�gina 2)
		listaPage = listaPage.retrocedePagina();
		
		// VERIFICACI�N
		// entonces, la aplicaci�n se queda en la p�gina de b�squeda
		paginaListadoCorrecta(listaPage);
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(5));
		// verifico el mensaje de paginaci�n
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 1 al 5."));
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_ir_a_ultima_pagina() throws PageException{
		// PREPARACI�N
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");	
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una b�squeda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCI�N
		// el usuario pincha sobre la �ltima pagina
		listaPage = listaPage.ultimaPagina();
		
		// VERIFICACI�N
		// entonces, la aplicaci�n se queda en la p�gina de b�squeda
		paginaListadoCorrecta(listaPage);
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginaci�n
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));		
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
	}
	
	@Test
	public void deberia_ir_a_primera_pagina() throws PageException{
		// PREPARACI�N
		ProductoDto productoEsperado1 = new ProductoDto(999991L, "cod-1", "Leche", new MarcaDto("Pascual"));
		ProductoDto productoEsperado2 = new ProductoDto(999992L, "cod-2", "Leche", new MarcaDto("Central Lechera Asturiana"));		
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una b�squeda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		listaPage = listaPage.ultimaPagina();
		
		// EJECUCI�N
		// el usuario pincha sobre la p�gina primera
		listaPage = listaPage.primeraPagina();
		
		// VERIFICACI�N
		// entonces, la aplicaci�n se queda en la p�gina de b�squeda
		paginaListadoCorrecta(listaPage);
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(5));
		// verifico el mensaje de paginaci�n
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 1 al 5."));		
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_ir_a_pagina2() throws PageException{
		// PREPARACI�N
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");				
		// el usuario accede a la p�gina de b�squeda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una b�squeda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCI�N
		// el usuario pincha sobre la p�gina primera
		listaPage = listaPage.irPagina(2);
		
		// VERIFICACI�N
		// entonces, la aplicaci�n se queda en la p�gina de b�squeda
		paginaListadoCorrecta(listaPage);
		// no hay ning�n mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el n�mero de registros resultado de la b�squeda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginaci�n
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));		
		// el resultado de la b�squeda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
}
