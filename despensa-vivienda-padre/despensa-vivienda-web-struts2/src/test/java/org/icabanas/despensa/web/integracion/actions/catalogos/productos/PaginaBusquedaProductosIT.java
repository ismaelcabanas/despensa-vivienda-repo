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
 * Clase que realiza tests de integración para comprobar la paginación en las búsquedas. 
 * El número de resultados por página está configurado en el fichero properties de configuración de 
 * la aplicación.
 * 
 * @author f009994r
 *
 */
public class PaginaBusquedaProductosIT extends AbstractProductosIT {

	@Test
	public void deberia_avanzar_pagina() throws PageException{
		// PREPARACIÓN
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");	
		// el usuario accede a la página de búsqueda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una búsqueda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCIÓN
		// el usuario pincha sobre la página siguiente (sería la página 2)
		listaPage = listaPage.avanzaPagina();
		
		// VERIFICACIÓN
		// entonces, la aplicación se queda en la página de búsqueda
		paginaListadoCorrecta(listaPage);
		// no hay ningún mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el número de registros resultado de la búsqueda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginación
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));
		// el resultado de la búsqueda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_retroceder_pagina() throws PageException{
		// PREPARACIÓN
		ProductoDto productoEsperado1 = new ProductoDto(101L, "cod-1", "Leche", new MarcaDto("Pascual"));
		ProductoDto productoEsperado2 = new ProductoDto(102L, "cod-2", "Leche", new MarcaDto("Central Lechera Asturiana"));		
		// el usuario accede a la página de búsqueda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una búsqueda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		listaPage = listaPage.avanzaPagina();
		
		// EJECUCIÓN
		// el usuario pincha sobre la página siguiente (sería la página 2)
		listaPage = listaPage.retrocedePagina();
		
		// VERIFICACIÓN
		// entonces, la aplicación se queda en la página de búsqueda
		paginaListadoCorrecta(listaPage);
		// no hay ningún mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el número de registros resultado de la búsqueda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(5));
		// verifico el mensaje de paginación
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 1 al 5."));
		// el resultado de la búsqueda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_ir_a_ultima_pagina() throws PageException{
		// PREPARACIÓN
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");	
		// el usuario accede a la página de búsqueda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una búsqueda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCIÓN
		// el usuario pincha sobre la última pagina
		listaPage = listaPage.ultimaPagina();
		
		// VERIFICACIÓN
		// entonces, la aplicación se queda en la página de búsqueda
		paginaListadoCorrecta(listaPage);
		// no hay ningún mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el número de registros resultado de la búsqueda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginación
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));		
		// el resultado de la búsqueda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
	}
	
	@Test
	public void deberia_ir_a_primera_pagina() throws PageException{
		// PREPARACIÓN
		ProductoDto productoEsperado1 = new ProductoDto(999991L, "cod-1", "Leche", new MarcaDto("Pascual"));
		ProductoDto productoEsperado2 = new ProductoDto(999992L, "cod-2", "Leche", new MarcaDto("Central Lechera Asturiana"));		
		// el usuario accede a la página de búsqueda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una búsqueda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		listaPage = listaPage.ultimaPagina();
		
		// EJECUCIÓN
		// el usuario pincha sobre la página primera
		listaPage = listaPage.primeraPagina();
		
		// VERIFICACIÓN
		// entonces, la aplicación se queda en la página de búsqueda
		paginaListadoCorrecta(listaPage);
		// no hay ningún mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el número de registros resultado de la búsqueda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(5));
		// verifico el mensaje de paginación
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 1 al 5."));		
		// el resultado de la búsqueda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
	
	@Test
	public void deberia_ir_a_pagina2() throws PageException{
		// PREPARACIÓN
		ProductoDto productoEsperado1 = new ProductoDto(106L, "cod-6", "Miel");
		ProductoDto productoEsperado2 = new ProductoDto(107L, "cod-7", "Pan");				
		// el usuario accede a la página de búsqueda de productos
		BusquedaProductosPage listaPage = navegaCatalogoProductos(driver);
		// realiza una búsqueda, de todos los registros, por ejemplo.
		listaPage = listaPage.buscar();
		
		// EJECUCIÓN
		// el usuario pincha sobre la página primera
		listaPage = listaPage.irPagina(2);
		
		// VERIFICACIÓN
		// entonces, la aplicación se queda en la página de búsqueda
		paginaListadoCorrecta(listaPage);
		// no hay ningún mensaje informativo
		assertThat(listaPage.getMensajeInformativo(), nullValue());
		// el número de registros resultado de la búsqueda debe ser el correcto
		assertThat(listaPage.getNumeroRegistrosBusqueda(), equalTo(2));
		// verifico el mensaje de paginación
		assertThat(listaPage.getMensajePaginacion(),IsEqual.equalTo("7 registros encontrados, mostrando del 6 al 7."));		
		// el resultado de la búsqueda es el correcto.
		List<ProductoDto> resultado = listaPage.getResultadoBusqueda();
		//assertThat(resultado, hasItem(productoEsperado1));
		assertThat(resultado.get(0), equalTo(productoEsperado1));
		assertThat(resultado.get(1), equalTo(productoEsperado2));
	}
}
