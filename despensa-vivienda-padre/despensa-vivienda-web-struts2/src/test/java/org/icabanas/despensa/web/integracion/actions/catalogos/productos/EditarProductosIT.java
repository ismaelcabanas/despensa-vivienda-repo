package org.icabanas.despensa.web.integracion.actions.catalogos.productos;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.EdicionProductoPage;
import org.icabanas.despensa.web.integracion.actions.catalogos.productos.pages.BusquedaProductosPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.Test;

public class EditarProductosIT extends AbstractProductosIT {


	@Test
	public void edicion_de_un_producto() throws PageException{
		// el usuario entra en la página de listado
		BusquedaProductosPage listadoPage = navegaCatalogoProductos(driver);
		//BusquedaProductosPage listadoPage = new BusquedaProductosPage(driver, URI_WEB_APP);

		// el usuario realiza una búsqueda para editar un producto
		listadoPage = listadoPage.buscar("cod-1", null, null);
		
		// el usuario selecciona un producto del resultado de la búsqueda para editarlo
		EdicionProductoPage page = listadoPage.edita("cod-1");		
		
		// entonces, la página debe ser la que esperamos
		paginaEdicionCorrecta(page);		
		// el contenido del campo código debe ser "cod-1"
		assertThat(page.getValorElemento("producto.codigo"), equalTo("cod-1"));
		// el contenido del campo nombre debe ser "Leche"
		assertThat(page.getValorElemento("producto.nombre"), equalTo("Leche"));
		// el contenido del campo marca debe ser "Pascual"
		assertThat(page.getValorElementoSelector("producto.marca.id"), equalTo("Pascual"));
	}
	
//	@Test
//	public void edicion_de_un_producto_sin_haberlo_seleccionado() throws PageException{
//		// si el usuario navega directamente a la página de edición sin haberse seleccionado un producto anteriormente
//		EdicionProductoPage page = new EdicionProductoPage(driver,URI_WEB_APP);
//		BusquedaProductosPage listaProductosPage = page.edicionSinSeleccionarProducto();
//		
//		// entonces, debería navegar a la página de listado y mostrar mensaje informativo al usuario
//		paginaListadoCorrecta(listaProductosPage);
//		// se comprueba que el mensaje informativo es el que se espera
//		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("producto.editar.noexiste")));				
//	}
//	
//	@Test
//	public void edicion_de_un_producto_que_no_existe() throws PageException{
//		// si el usuario navega a la página de edición de un producto que no existe
//		EdicionProductoPage page = new EdicionProductoPage(driver,URI_WEB_APP);
//		BusquedaProductosPage listaProductosPage = page.editaProductoQueNoExiste(8L);
//		
//		// entonces, debería navegar a la página de listado y mostrar mensaje informativo al usuario
//		paginaListadoCorrecta(listaProductosPage);
//		// se comprueba que el mensaje informativo es el que se espera
//		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("producto.editar.noexiste")));				
//	}		
	
	@Test
	public void si_cancela_edicion_deberia_navegar_a_listado_productos() throws PageException{
		// el usuario entra en la página de listado
		BusquedaProductosPage listadoPage = navegaCatalogoProductos(driver);

		// el usuario realiza una búsqueda para editar un producto
		listadoPage = listadoPage.buscar("cod-1", null, null);
		
		// el usuario selecciona un producto del resultado de la búsqueda para editarlo
		EdicionProductoPage page = listadoPage.edita("cod-1");
		
		// y el usuario pincha sobre el botón cancelar
		BusquedaProductosPage listaProductosPage = page.cancelar();
		
		// la página de listado es correcta
		paginaListadoCorrecta(listaProductosPage);
		// se comprueba que el mensaje informativo es el que se espera
		assertThat(listaProductosPage.getMensajeInformativo(), notNullValue());
		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("mensaje.cancelacion")));				
	}
	
	@Test
	public void si_elimina_producto_deberia_navegar_a_listado_productos() throws PageException{
		// el usuario entra en la página de listado
		BusquedaProductosPage listadoPage = navegaCatalogoProductos(driver);

		// el usuario realiza una búsqueda para editar un producto
		listadoPage = listadoPage.buscar("cod-1", null, null);
		
		// el usuario selecciona un producto del resultado de la búsqueda para editarlo
		EdicionProductoPage page = listadoPage.edita("cod-1");
		
		// y el usuario lo elimina
		BusquedaProductosPage listaProductosPage = page.eliminar();
		
		// la página de listado es correcta
		paginaListadoCorrecta(listaProductosPage);
		// se comprueba que el mensaje informativo es el que se espera
		assertThat(listaProductosPage.getMensajeInformativo(), notNullValue());
		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("dto.eliminar.correcto")));				
	}		
	
	@Test
	public void actualizacion_producto() throws PageException{
		// el usuario entra en la página de listado
		BusquedaProductosPage listadoPage = navegaCatalogoProductos(driver);

		// el usuario realiza una búsqueda para editar un producto
		listadoPage = listadoPage.buscar("cod-1", null, null);
		
		// el usuario selecciona un producto del resultado de la búsqueda para editarlo
		EdicionProductoPage page = listadoPage.edita("cod-1");
		
		// el usuario actualiza los datos del producto
		BusquedaProductosPage listaProductosPage = page.actualiza("Leche Entera","2");
		
		// la página de listado es correcta
		paginaListadoCorrecta(listaProductosPage);
		// se comprueba que el mensaje informativo es el que se espera
		assertThat(listaProductosPage.getMensajeInformativo(), notNullValue());
		assertThat(listaProductosPage.getMensajeInformativo(), equalTo(props.getProperty("dto.actualizacion.correcto")));
		
		// el producto se modificó correctamente, para ello, volvemos a buscar el producto
		listadoPage = listadoPage.buscar("cod-1", null, null);
		// editamos de nuevo el producto
		page = listadoPage.edita("cod-1");
		// el contenido del campo nombre debe ser "Leche"
		assertThat(page.getValorElemento("producto.nombre"), equalTo("Leche Entera"));
		// el contenido del campo marca debe ser "Pascual"
		assertThat(page.getValorElementoSelector("producto.marca.id"), equalTo("Central Lechera Asturiana"));
	}	
}
