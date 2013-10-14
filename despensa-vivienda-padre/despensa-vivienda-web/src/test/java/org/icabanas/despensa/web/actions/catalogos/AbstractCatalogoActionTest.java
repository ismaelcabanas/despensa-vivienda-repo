package org.icabanas.despensa.web.actions.catalogos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.web.actions.catalogos.ICatalogoAction;
import org.icabanas.despensa.web.helper.PaginacionFactory;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dto.BaseDto;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class AbstractCatalogoActionTest<Id extends Serializable, Dto extends BaseDto<Id>> {
 
	protected ICatalogoAction<Id,Dto> accion;
	
	@Mock
	protected IGenericManager<Id, Dto> _mockCatalogoManager;
	
	@Mock
	private HttpServletRequest _mockRequest;
	
	@Test
	public void accion_alta(){
		
		// ejecución
		accion.alta();
				
		// verificación		
	}
	
	@Test
	public void accion_cancelar(){		
		// ejecución
		accion.cancelar();
				
		// verificación
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("mensaje.cancelacion"));
	}
	
	@Test
	public void accion_eliminar() throws Exception{
		// preparación
		accion.setDto(getDto());
		
		// ejecución
		accion.eliminar();
				
		// verificación
		Mockito.verify(_mockCatalogoManager).eliminar(getDto().getId());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.eliminar.correcto"));		
	}

	protected abstract Dto getDto();
	
	@Test
	public void deberia_lanzar_excepcion_si_acccion_eliminar_y_no_existe_entidad() throws Exception{
		// preparación
		accion.setDto(getDto());
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoManager).eliminar((Id)Mockito.anyObject());
				
		// ejecución
		try{
			accion.eliminar();
			Assert.fail("Debería lanzar excepción...");
		}
		catch(NoExisteEntidadException e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoManager).eliminar(getDto().getId());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.eliminar.noexiste"));		
	}
	
	@Test
	public void accion_guardar() throws Exception{
		// preparación
		accion.setDto(getDtoParaRegistrar());
		
		// ejecución
		accion.guardar();
				
		// verificación
		Mockito.verify(_mockCatalogoManager).registrar(getDtoParaRegistrar());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.alta.correcto"));			
	}
	
	
	@Test
	public void accion_actualizar() throws Exception{
		// preparación
		accion.setDto(getDtoParaActualizar());
		
		// ejecución
		accion.actualizar();
				
		// verificación
		Mockito.verify(_mockCatalogoManager).actualizar(getDtoParaActualizar());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.actualizacion.correcto"));		
		Assert.assertThat(accion.getDto(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_acccion_actualizar_y_no_existe_entidad() throws Exception{
		// preparación
		accion.setDto(getDtoParaActualizar());
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoManager).actualizar((Dto) Mockito.anyObject());
				
		// ejecución
		try{
			accion.actualizar();
			Assert.fail("Debería lanzar excepción...");
		}
		catch(Exception e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoManager).actualizar(getDtoParaActualizar());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.editar.noexiste"));	
		Assert.assertThat(accion.getDto(), IsNull.nullValue());
	}
	
	@Test
	public void accion_editar() throws Exception{
		// preparación
		accion.setDto(getDto());
		Mockito.when(_mockCatalogoManager.buscarPorId(getDto().getId())).thenReturn(getDtoEsperado());
				
		// ejecución
		accion.editar(getDto().getId());
				
		// verificación
		Mockito.verify(_mockCatalogoManager).buscarPorId(getDtoEsperado().getId());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().size(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getDto(), IsNull.notNullValue());
		Assert.assertThat(accion.getDto(), IsEqual.equalTo(getDtoEsperado()));
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_acccion_editar_y_no_existe_entidad() throws Exception{
		// preparación
		accion.setDto(getDto());
		Mockito.doThrow(NoExisteEntidadException.class).when(_mockCatalogoManager).buscarPorId((Id)Mockito.anyObject());
				
		// ejecución
		try{
			accion.editar(getDto().getId());
			Assert.fail("Debería lanzar excepción...");
		}
		catch(NoExisteEntidadException e){
			
		}
				
		// verificación
		Mockito.verify(_mockCatalogoManager).buscarPorId(getDtoEsperado().getId());
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("dto.editar.noexiste"));	
		Assert.assertThat(accion.getDto(), IsNull.nullValue());
	}
	
//	@Test
//	public void accion_validar() throws Exception{
//		// preparación
//		accion.setDto(getDto());
//		
//		// ejecución
//		accion.validar();
//		
//		// verificación
//		Mockito.verify(_mockCatalogoManager).validar(getDto());
//	}
	
	@Test
	public void accion_paginar(){		
		// preparación
		accion.setFiltro(getFiltroParaPaginacion());
		HttpSession _mockSession = Mockito.mock(HttpSession.class);
		Mockito.when(_mockRequest.getSession()).thenReturn(_mockSession );
		Mockito.when(_mockSession.getAttribute(PaginacionFactory.NUMERO_REGISTROS_POR_PAGINA)).thenReturn(2);
		Mockito.doAnswer(new Answer<Pagina<CategoriaDto>>() {

			@Override
			public Pagina<CategoriaDto> answer(InvocationOnMock invocation) throws Throwable {
				Pagina<CategoriaDto> pagina = (Pagina<CategoriaDto>) invocation.getArguments()[0];
				List<CategoriaDto> datos = new ArrayList<CategoriaDto>();
				datos.add(new CategoriaDto("Alimentación"));
				pagina.setDatos(datos);
				pagina.setNumeroTotalRegistros(5);
				pagina.setNumeroTotalRegistrosPorPagina(2);
				return null;
			}
			
		}).when(_mockCatalogoManager).paginar(Mockito.any(Pagina.class));
		
		// ejecución
		accion.paginar(_mockRequest);
		
		// verificación
		Mockito.verify(_mockCatalogoManager).paginar(Mockito.any(Pagina.class));
		Assert.assertThat(accion.getPagina(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos().size(), IsEqual.equalTo(1));
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
		Mockito.doAnswer(new Answer<Pagina<Dto>>() {

			@Override
			public Pagina<Dto> answer(InvocationOnMock invocation) throws Throwable {
				Pagina<Dto> pagina = (Pagina<Dto>) invocation.getArguments()[0];
				List<Dto> datos = new ArrayList<Dto>();
				pagina.setDatos(datos);
				pagina.setNumeroTotalRegistros(0);
				pagina.setNumeroTotalRegistrosPorPagina(2);
				return null;
			}
			
		}).when(_mockCatalogoManager).paginar(Mockito.any(Pagina.class));
		
		// ejecución
		accion.paginar(_mockRequest);
		
		// verificación
		Mockito.verify(_mockCatalogoManager).paginar(Mockito.any(Pagina.class));
		Assert.assertThat(accion.getPagina(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos(), IsNull.notNullValue());
		Assert.assertThat(accion.getPagina().getDatos().size(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getPagina().getNumeroRegistrosPorPagina(), IsEqual.equalTo(2));
		Assert.assertThat(accion.getPagina().getNumeroTotalRegistros(), IsEqual.equalTo(0));
		Assert.assertThat(accion.getPagina().getNumeroTotalPaginas(), IsEqual.equalTo(0));
//		Assert.assertThat(accion.getMensajesUsuario(), IsNull.notNullValue());
//		Assert.assertThat(accion.getMensajesUsuario().get(0).getI18NKeyMensaje(), IsEqual.equalTo("mensaje.busqueda.sin.registros"));	
	}
	
	/**
	 * Devuelve la instancia del filtro utilizado en el action.
	 * 
	 * @return
	 */
	protected abstract IFiltro getFiltroParaPaginacion();

	/**
	 * Devuelve la instancia del DTO esperado en la acción de editar.
	 * 
	 * @return
	 */
	protected abstract Dto getDtoEsperado();

	/**
	 * Instancia de un Dto para utilizarlo en la acción de actualizar.
	 * 
	 * @return
	 */
	protected abstract Dto getDtoParaActualizar();

	/**
	 * Instancia de un Dto para utilizarlo para la acción de registrar.
	 * 
	 * @return
	 */
	protected abstract Dto getDtoParaRegistrar();

	protected void setAccion(ICatalogoAction<Id, Dto> accion) {
		this.accion = accion;
	}
	
	
}
