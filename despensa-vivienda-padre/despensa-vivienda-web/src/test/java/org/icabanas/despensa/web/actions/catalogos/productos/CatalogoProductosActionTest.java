package org.icabanas.despensa.web.actions.catalogos.productos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marcas.ICatalogoMarcas;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoFiltro;
import org.icabanas.despensa.catalogos.productos.ICatalogoProductos;
import org.icabanas.despensa.web.actions.catalogos.AbstractCatalogoActionTest;
import org.icabanas.despensa.web.helper.PaginacionFactory;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoProductosActionTest extends AbstractCatalogoActionTest<Long, ProductoDto> {

	@Mock
	private ICatalogoMarcas _mockCatalogoMarcas;
	
	@Mock
	private ICatalogoProductos _mockCatalogoProductos;
	
	private CatalogoProductosAction accion;

	@Mock
	private HttpServletRequest _mockRequest;
	
	@Before
	public void configura_test(){
		accion = new CatalogoProductosAction(_mockCatalogoProductos, _mockCatalogoMarcas);
		setAccion(accion);
	}
	
	@Override
	@Test
	public void accion_alta(){
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);		
		
		// ejecución
		accion.alta();
				
		// verificación
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
	}
	
	@Override
	@Test
	public void accion_cancelar(){
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);		
		
		// ejecución
		accion.cancelar();
				
		// verificación
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("mensaje.cancelacion"));
	}
	
	@Override
	@Test
	public void accion_eliminar() throws Exception{
		// preparación		
		accion.setDto(getDto());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);	
		
		// ejecución
		accion.eliminar();
				
		// verificación
		Mockito.verify(_mockCatalogoProductos).eliminar(1L);
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.eliminar.correcto"));		
	}
	
	@Override
	@Test
	public void deberia_lanzar_excepcion_si_acccion_eliminar_y_no_existe_entidad() throws Exception{
		// preparación
		accion.setDto(getDto());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoProductos).eliminar(Mockito.anyLong());
				
		// ejecución
		try{
			accion.eliminar();
			Assert.fail("Debería lanzar excepción...");
		}
		catch(NoExisteEntidadException e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoProductos).eliminar(1L);
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.eliminar.noexiste"));		
	}	
	
	@Override
	@Test
	public void accion_guardar() throws Exception{
		// preparación		
		accion.setDto(getDtoParaRegistrar());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		
		// ejecución
		accion.guardar();
				
		// verificación
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		Mockito.verify(_mockCatalogoProductos).registrar(getDtoParaRegistrar());
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.alta.correcto"));			
	}
	
	@Override
	@Test
	public void accion_actualizar() throws Exception{
		// preparación
		accion.setDto(getDtoParaActualizar());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		
		// ejecución
		accion.actualizar();
				
		// verificación
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		Mockito.verify(_mockCatalogoProductos).actualizar(getDtoParaActualizar());
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.actualizacion.correcto"));		
		Assert.assertThat(accion.getProducto(), IsNull.nullValue());
	}
	
	@Override
	@Test
	public void deberia_lanzar_excepcion_si_acccion_actualizar_y_no_existe_entidad() throws Exception{
		// preparación
		accion.setDto(getDtoParaActualizar());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoProductos).actualizar(Mockito.any(ProductoDto.class));
				
		// ejecución
		try{
			accion.actualizar();
			Assert.fail("Debería lanzar excepción...");
		}
		catch(Exception e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoProductos).actualizar(getDtoParaActualizar());
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.editar.noexiste"));	
		Assert.assertThat(accion.getProducto(), IsNull.nullValue());
	}
	
	@Override
	@Test
	public void accion_editar() throws Exception{
		// preparación		
		accion.setDto(getDtoParaActualizar());
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		Mockito.when(_mockCatalogoProductos.buscarPorId(getDtoEsperado().getId())).thenReturn(getDtoEsperado());
				
		// ejecución
		accion.editar(getDto().getId());
				
		// verificación
		Mockito.verify(_mockCatalogoProductos).buscarPorId(getDtoEsperado().getId());
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().size(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getProducto(), IsNull.notNullValue());
		Assert.assertThat(accion.getProducto(), IsEqual.equalTo(getDtoEsperado()));
	}
	
	@Override
	@Test
	public void deberia_lanzar_excepcion_si_acccion_editar_y_no_existe_entidad() throws Exception{
		// preparación
		ProductoDto producto = new ProductoDto(1L, "cod-0001", "Leche");
		accion.setDto(producto);
		List<MarcaDto> marcasEsperadas = marcasEsperadas();
		Mockito.when(_mockCatalogoMarcas.buscarTodas()).thenReturn(marcasEsperadas);
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoProductos).buscarPorId(Mockito.anyLong());
				
		// ejecución
		try{
			accion.editar(getDto().getId());
			Assert.fail("Debería lanzar excepción...");
		}
		catch(NoExisteEntidadException e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoProductos).buscarPorId(producto.getId());
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		verificarMarcas();
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.editar.noexiste"));	
		Assert.assertThat(accion.getProducto(), IsNull.nullValue());
	}
		
	@Test
	public void deberia_lanzar_excepcion_validacion_si_valida_producto_sin_codigo() throws Exception{
		// preparación
		ProductoDto producto = getDto();
		producto.setNombre("Leche");
		accion.setDto(producto);
		ValidacionException excepcionEsperada = new ValidacionException();
		List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
		ErrorValidacion errorCodigoVacio = new ErrorValidacion("producto.codigo", "producto.codigo.requerido");
		listaExcepciones.add(errorCodigoVacio);
		excepcionEsperada.setListaExcepciones(listaExcepciones);
		Mockito.doThrow(excepcionEsperada).when(_mockCatalogoProductos).validar(producto);
		
		// ejecución
		ValidacionException testExcepcion = null;
		try{
			accion.validar();
			Assert.fail("Debería lanzar excepción de validación.");
		}
		catch(ValidacionException e){
			testExcepcion = e;
		}
		
		// verificación
		Mockito.verify(_mockCatalogoProductos).validar(producto);
		Assert.assertThat(testExcepcion, IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones(), IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones().size(), IsEqual.equalTo(1));
	}
	
	@Test
	public void deberia_lanzar_excepcion_validacion_si_valida_producto_sin_nombre() throws Exception{
		// preparación
		ProductoDto producto = getDto();
		producto.setCodigo("cod-0001");
		accion.setDto(producto);
		ValidacionException excepcionEsperada = new ValidacionException();
		List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
		ErrorValidacion errorNombreVacio = new ErrorValidacion("producto.nombre", "producto.nombre.requerido");
		listaExcepciones.add(errorNombreVacio);
		excepcionEsperada.setListaExcepciones(listaExcepciones);
		Mockito.doThrow(excepcionEsperada).when(_mockCatalogoProductos).validar(producto);
		
		// ejecución
		ValidacionException testExcepcion = null;
		try{
			accion.validar();
			Assert.fail("Debería lanzar excepción de validación.");
		}
		catch(ValidacionException e){
			testExcepcion = e;
		}
		
		// verificación
		Mockito.verify(_mockCatalogoProductos).validar(producto);
		Assert.assertThat(testExcepcion, IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones(), IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones().size(), IsEqual.equalTo(1));
	}	
	
	@Test
	public void deberia_lanzar_excepcion_validacion_si_valida_producto() throws Exception{
		// preparación
		accion.setDto(getDto());
		ValidacionException excepcionEsperada = new ValidacionException();
		List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
		ErrorValidacion errorNombreVacio = new ErrorValidacion("producto.nombre", "producto.nombre.requerido");
		listaExcepciones.add(errorNombreVacio);
		ErrorValidacion errorCodigoVacio = new ErrorValidacion("producto.codigo", "producto.codigo.requerido");
		listaExcepciones.add(errorCodigoVacio);
		excepcionEsperada.setListaExcepciones(listaExcepciones);
		Mockito.doThrow(excepcionEsperada).when(_mockCatalogoProductos).validar(getDto());
		
		// ejecución
		ValidacionException testExcepcion = null;
		try{
			accion.validar();
			Assert.fail("Debería lanzar excepción de validación.");
		}
		catch(ValidacionException e){
			testExcepcion = e;
		}
		
		// verificación
		Mockito.verify(_mockCatalogoProductos).validar(getDto());
		Assert.assertThat(testExcepcion, IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones(), IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones().size(), IsEqual.equalTo(2));
	}	
	
	@Override
	@Test
	public void accion_paginar(){		
		// preparación
		accion.setFiltro(getFiltroParaPaginacion());
		HttpSession _mockSession = Mockito.mock(HttpSession.class);
		Mockito.when(_mockRequest.getSession()).thenReturn(_mockSession );
		Mockito.when(_mockSession.getAttribute(PaginacionFactory.NUMERO_REGISTROS_POR_PAGINA)).thenReturn(2);
		Mockito.doAnswer(new Answer<Pagina<ProductoDto>>() {

			@Override
			public Pagina<ProductoDto> answer(InvocationOnMock invocation) throws Throwable {
				Pagina<ProductoDto> pagina = (Pagina<ProductoDto>) invocation.getArguments()[0];
				List<ProductoDto> datos = new ArrayList<ProductoDto>();
				datos.add(new ProductoDto("cod-0001", "Leche"));
				datos.add(new ProductoDto("cod-0002", "Yogurt"));
				pagina.setDatos(datos);
				pagina.setNumeroTotalRegistros(5);
				pagina.setNumeroTotalRegistrosPorPagina(2);
				return null;
			}
			
		}).when(_mockCatalogoProductos).paginar(Mockito.any(Pagina.class));
		
		// ejecución
		accion.paginar(_mockRequest);
		
		// verificación
		Mockito.verify(_mockCatalogoProductos).paginar(Mockito.any(Pagina.class));
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		Assert.assertThat(accion.getPagina(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos().size(), IsEqual.equalTo(2));
		Assert.assertThat(accion.getPagina().getNumeroRegistrosPorPagina(), IsEqual.equalTo(2));
		Assert.assertThat(accion.getPagina().getNumeroTotalRegistros(), IsEqual.equalTo(5));
		Assert.assertThat(accion.getPagina().getNumeroTotalPaginas(), IsEqual.equalTo(3));
//		Assert.assertThat(accion.getMensajesUsuario().size(), IsEqual.equalTo(0));
		//Assert.assertThat(accion.getMensajesUsuario().get(0), IsEqual.equalTo("producto.editar.noexiste"));	
	}
	
	@Test
	public void accion_paginar_sin_registros(){		
		// preparación
		accion.setFiltro(getFiltroParaPaginacion());
		HttpSession _mockSession = Mockito.mock(HttpSession.class);
		Mockito.when(_mockRequest.getSession()).thenReturn(_mockSession );
		Mockito.when(_mockSession.getAttribute(PaginacionFactory.NUMERO_REGISTROS_POR_PAGINA)).thenReturn(2);
		Mockito.doAnswer(new Answer<Pagina<ProductoDto>>() {

			@Override
			public Pagina<ProductoDto> answer(InvocationOnMock invocation) throws Throwable {
				Pagina<ProductoDto> pagina = (Pagina<ProductoDto>) invocation.getArguments()[0];
				List<ProductoDto> datos = new ArrayList<ProductoDto>();
				pagina.setDatos(datos);
				pagina.setNumeroTotalRegistros(0);
				pagina.setNumeroTotalRegistrosPorPagina(2);
				return null;
			}
			
		}).when(_mockCatalogoProductos).paginar(Mockito.any(Pagina.class));
		
		// ejecución
		accion.paginar(_mockRequest);
		
		// verificación
		Mockito.verify(_mockCatalogoProductos).paginar(Mockito.any(Pagina.class));
		Mockito.verify(_mockCatalogoMarcas).buscarTodas();
		Assert.assertThat(accion.getPagina(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos().size(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getPagina().getNumeroRegistrosPorPagina(), IsEqual.equalTo(2));
		Assert.assertThat(accion.getPagina().getNumeroTotalRegistros(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getPagina().getNumeroTotalPaginas(), IsEqual.equalTo(0));
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("mensaje.busqueda.sin.registros"));	
	}
	
	private void verificarMarcas() {
		Assert.assertThat(accion.getMarcas(), IsNull.notNullValue());
		Assert.assertThat(accion.getMarcas().size(), IsEqual.equalTo(2));
	}	
	
	private List<MarcaDto> marcasEsperadas() {
		List<MarcaDto> marcasEsperadas = new ArrayList<MarcaDto>();
		marcasEsperadas.add(new MarcaDto("Clesa"));
		marcasEsperadas.add(new MarcaDto("Hacendado"));
		return marcasEsperadas;
	}

	@Override
	protected ProductoDto getDto() {
		ProductoDto producto = new ProductoDto(1L, "cod-0001");
		return producto;
	}

	@Override
	protected IFiltro getFiltroParaPaginacion() {
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setNombre("leche");
		return filtro;
	}

	@Override
	protected ProductoDto getDtoEsperado() {
		ProductoDto productoEsperado = new ProductoDto(1L, "cod-0001", "Leche");
		return productoEsperado;
	}

	@Override
	protected ProductoDto getDtoParaActualizar() {
		ProductoDto producto = new ProductoDto(1L,"cod-0001", "Leche");
		return producto;
	}

	@Override
	protected ProductoDto getDtoParaRegistrar() {
		ProductoDto producto = new ProductoDto("cod-0001", "Leche");
		return producto;
	}

}
