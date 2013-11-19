package org.icabanas.despensa.catalogos;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.modelo.EntidadBase;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
	
public abstract class CRUDTest<Dto extends Serializable, Id extends Serializable, Entidad extends EntidadBase> {

	public static final String KEY_DTO_A_REGISTRAR = "dto_a_registrar";
	public static final String KEY_DTO_NO_VALIDO = "dto_no_valido";
	public static final String KEY_DTO_A_MODIFICAR = "dto_a_modificar";
	public static final String KEY_ENTIDAD_A_MODIFICAR = "entidad_a_modificar";
	public static final String KEY_ENTIDAD_MODIFICADA = "entidad_modificada";
	public static final String KEY_DTO_ESPERADO_REGISTRAR = "dto_esperado_registrar";
	public static final String KEY_DTO_ESPERADO_MODIFICAR = "dto_esperado_modificar";
	public static final String KEY_DTO_NO_VALIDO_PARA_MODIFICACION = "dto_no_valido_modificacion";
	public static final String KEY_ENTIDAD_A_ELIMINAR = "entidad_a_eliminar";
	public static final String KEY_DTO_ESPERADO_BUSCAR = "dto_esperado_busqueda";
	public static final String KEY_ENTIDAD_BUSQUEDA = "entidad_busqueda";

	@Test
	public void deberia_dar_de_ata_una_entidad_correcta(){
		// preparaci�n
		Dto dtoRegistrar = getTestDtos().get(KEY_DTO_A_REGISTRAR);
		Dto dtoEsperado = getTestDtoEsperados().get(KEY_DTO_ESPERADO_REGISTRAR);
		Mockito.doAnswer(new Answer<Entidad>() {

			@Override
			public Entidad answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Entidad entidad = (Entidad) args[0];
				entidad.setId(1L);
				return null;
			}
			
		}).when(getMockDao()).crear((Entidad) Mockito.any());
		
		// ejecuci�n
		Dto dtoRegistrado = null;
		try {
			dtoRegistrado = getServicio().registrar(dtoRegistrar);
		} catch (RegistrarException e) {
			fail("Deber�a dar de alta la entidad correctamente. " + e.getMessage());
		}
						
		// verificaci�n
		//Entidad entidad = getEntidadAVerificar();
		Mockito.verify(getMockDao()).crear((Entidad) Mockito.any());
		assertThat(dtoRegistrado, IsEqual.equalTo(dtoEsperado));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_dar_excepcion_si_se_da_alta_una_entidad_nula(){
		// preparaci�n		
		
		// ejecuci�n		
		try {
			getServicio().registrar(null);
		} catch (RegistrarException e) {
			fail("Deber�a producirse una IllegalArgumentException.");
		}
		
		// verificaci�n		
		
	}

	@Test
	public void deberia_dar_codigo_mensaje_error_si_entidad_no_valida(){
		// preparaci�n	
		Dto dtoNoValido = getTestDtos().get(KEY_DTO_NO_VALIDO);	
				
		// ejecuci�n
		RegistrarException testException = null;
		try {
			getServicio().registrar(dtoNoValido);
		} catch (RegistrarException e) {
			testException = e;
		}
		
		// verificaci�n		
		assertThat(testException, notNullValue());
		assertThat(testException.getListaExcepciones(), notNullValue());
		Assert.assertTrue(testException.getListaExcepciones().size() > 0);
	}
	
	@Test
	public void deberia_modificar_entidad(){
		// preparaci�n	
		Dto dtoAModificar = getTestDtos().get(KEY_DTO_A_MODIFICAR);
		Dto dtoEsperado = getTestDtoEsperados().get(KEY_DTO_ESPERADO_MODIFICAR);		
		Entidad entidad = getTestEntidades().get(KEY_ENTIDAD_A_MODIFICAR);
		Mockito.when(getMockDao().buscarPorId((Id) Mockito.any())).thenReturn(entidad);
		Entidad entidadModificada = getTestEntidades().get(KEY_ENTIDAD_MODIFICADA);
		Mockito.when(getMockDao().modificar((Entidad) Mockito.any())).thenReturn(entidadModificada);
		
		// ejecuci�n		
		Dto dtoModificado = null;
		try {			
			dtoModificado = getServicio().actualizar(dtoAModificar);
		} catch (ModificarException e) {
			fail("Deber�a modificar la entidad correctamente.");
		}catch (NoExisteEntidadException e) {
			fail("La entidad debe exisir.");
		}
		
		// verificaci�n		
		Mockito.verify(getMockDao()).buscarPorId((Id) Mockito.any());		
		Mockito.verify(getMockDao()).modificar((Entidad) Mockito.any());
		assertThat(dtoModificado, notNullValue());
		assertThat(dtoModificado, equalTo(dtoEsperado));		
	}
	
	@Test(expected=NoExisteEntidadException.class)
	public void deberia_lanzar_excepcion_al_modificar_entidad_si_no_existe() throws Exception{
		// preparaci�n	
		Dto dtoAModificar = getTestDtos().get(KEY_DTO_A_MODIFICAR);
		Mockito.when(getMockDao().buscarPorId((Id) Mockito.any())).thenReturn(null);
		
		// ejecuci�n		
		Dto dtoModificado = getServicio().actualizar(dtoAModificar);		
		
		// verificaci�n		
	}
	
	@Test
	public void deberia_lanzar_excepcion_al_modificar_entidad_no_valida(){
		// preparaci�n	
		Dto dtoAModificar = getTestDtos().get(KEY_DTO_NO_VALIDO_PARA_MODIFICACION);
		Dto dtoEsperado = getTestDtoEsperados().get(KEY_DTO_ESPERADO_MODIFICAR);		
		Entidad entidad = getTestEntidades().get(KEY_ENTIDAD_A_MODIFICAR);
		Mockito.when(getMockDao().buscarPorId((Id) Mockito.any())).thenReturn(entidad);
		
		// ejecuci�n
		Dto dtoModificado = null;
		ModificarException testException = null;
		try {
			dtoModificado = getServicio().actualizar(dtoAModificar);
		} catch (ModificarException e) {
			testException = e;
		} catch (NoExisteEntidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// verificaci�n		
		assertThat(testException, notNullValue());
		Assert.assertTrue(testException.getListaExcepciones().size()>0);
	}
	
	@Test
	public void deberia_eliminar_entidad(){
		// preparaci�n		
		Entidad entidad = getTestEntidades().get(KEY_ENTIDAD_A_ELIMINAR);
		Id id = (Id) entidad.getId();
		Mockito.when(getMockDao().buscarPorId(id)).thenReturn(entidad);
		
		// ejecuci�n
		try {
			getServicio().eliminar(id);
		} catch (NoExisteEntidadException e) {
			fail("Deber�a eliminar el producto.");
		} catch (EliminarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// verificaci�n		
		Mockito.verify(getMockDao()).buscarPorId(id);
		Mockito.verify(getMockDao()).eliminar(entidad);
	}
	
	@Test(expected=NoExisteEntidadException.class)
	public void deberia_lanzar_excepcion_si_elimina_entidad_que_no_existe() throws NoExisteEntidadException, EliminarException{
		// preparaci�n	
		Entidad entidad = getTestEntidades().get(KEY_ENTIDAD_A_ELIMINAR);
		Id id = (Id) entidad.getId();
		Mockito.when(getMockDao().buscarPorId(id)).thenReturn(null);
		
		// ejecuci�n		
		getServicio().eliminar(id);		
		
		// verificaci�n		
	}
	
	@Test
	public void deberia_buscar_entidad_por_identificador() throws Exception{
		// preparaci�n
		Dto dtoEsperado = getTestDtoEsperados().get(KEY_DTO_ESPERADO_BUSCAR);
		Entidad entidad = getTestEntidades().get(KEY_ENTIDAD_BUSQUEDA);
		Id id = (Id) entidad.getId();
		when(getMockDao().buscarPorId(id)).thenReturn(entidad);
		
		// ejecuci�n
		Dto dtoBusqueda = getServicio().buscarPorId(id);
		
		// verificaci�n
		Mockito.verify(getMockDao()).buscarPorId(id);
		assertThat(dtoBusqueda, equalTo(dtoEsperado));
	}
	
	@Test(expected=NoExisteEntidadException.class)
	public void deberia_lanzar_excepcion_si_busca_entidad_por_identificador_nulo() throws Exception{
		// preparaci�n
		Id id = null;
		
		// ejecuci�n
		Dto dtoBusqueda = getServicio().buscarPorId(id);
		
		// verificaci�n
	}
	
	@Test
	public void deberia_realizar_busqueda_pagina_por_filtro(){		
		// preparaci�n
		IFiltro filtro = Mockito.mock(IFiltro.class);
		Pagina<Dto> pagina = new Pagina<Dto>(2, 5, filtro);
		IPaginador<Entidad> paginaEntidad = new Pagina<Entidad>(pagina.getPagina(), pagina.getNumeroRegistrosPorPagina(), pagina.getFiltro());
		Mockito.doAnswer(new Answer<Entidad>() {

			@Override
			public Entidad answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Pagina<Entidad> pag = (Pagina<Entidad>) args[0];
				pag.setNumeroTotalRegistros(10);
				pag.setDatos(getTestDatosPaginacion());
				return null;
			}
			
		}).when(getMockDao()).paginar(paginaEntidad );
		Mockito.when(getMockDao().paginar(paginaEntidad)).thenReturn(paginaEntidad);
		
		// ejecuci�n
		getServicio().paginar(pagina);
		
		// verificaci�n
		Mockito.verify(getMockDao()).paginar(paginaEntidad);
		Assert.assertThat(pagina.getNumeroTotalRegistros(), IsEqual.equalTo(10));
		Assert.assertThat(pagina.getNumeroTotalPaginas(), IsEqual.equalTo(2));
		Assert.assertThat(pagina.getDatos(), IsNull.notNullValue());
		Assert.assertTrue(pagina.getDatos().size() > 0);
	}

	/**
	 * 
	 * @return
	 * 		{@link List} lista de entidades utilizados para los test de paginaci�n.
	 */
	protected abstract List<Entidad> getTestDatosPaginacion();

	/**
	 * 
	 * @return
	 * 		{@link Map} con Entidades utilizadas para los test.
	 */
	protected abstract Map<String,Entidad> getTestEntidades();

	/**
	 * Recupera el Dao simulado.
	 * 
	 * @return
	 */
	protected abstract IGenericDao<Id, Entidad> getMockDao();
	
	/**
	 * @return
	 */
	protected abstract IGenericManager<Id, Dto> getServicio();

	/**
	 * 
	 * @return
	 * 		{@link Map} con Dtos esperados como resultado de los test.
	 */
	protected abstract Map<String, Dto> getTestDtoEsperados();

	/**
	 * 
	 * @return
	 * 		{@link Map} con Dtos utilizados para los test.
	 */
	protected abstract Map<String, Dto> getTestDtos();
}
