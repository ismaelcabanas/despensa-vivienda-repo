package org.icabanas.despensa.web.actions.catalogos.categorias;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.web.actions.catalogos.AbstractCatalogoActionTest;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoCategoriasActionTest extends AbstractCatalogoActionTest<Long, CategoriaDto> {

	
	@Mock
	private HttpServletRequest _mockRequest;
	
	@Before
	public void configura_test(){
		accion = new CatalogoCategoriasAction(_mockCatalogoManager);
	}
	
	
	@Override
	protected CategoriaDto getDto() {
		CategoriaDto categoria = new CategoriaDto();
		categoria.setId(1L);
		return categoria;
	}


	@Override
	protected IFiltro getFiltroParaPaginacion() {
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setNombre("Alimentación");
		return filtro;
	}


	@Override
	protected CategoriaDto getDtoEsperado() {
		CategoriaDto categoriaEsperado = new CategoriaDto(1L, "Alimentación");
		return categoriaEsperado;
	}


	@Override
	protected CategoriaDto getDtoParaActualizar() {
		CategoriaDto categoria = new CategoriaDto(1L,"Alimentación");
		return categoria;
	}


	@Override
	protected CategoriaDto getDtoParaRegistrar() {
		CategoriaDto categoria = new CategoriaDto("Alimentación");
		return categoria;
	}
	
	@Test
	public void deberia_lanzar_excepcion_validacion_si_valida_categoria_sin_nombre() throws Exception{
		// preparación
		CategoriaDto categoria = new CategoriaDto();
		accion.setDto(categoria);
		ValidacionException excepcionEsperada = new ValidacionException();
		List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
		ErrorValidacion errorCodigoVacio = new ErrorValidacion("categoria.nombre", "categoria.nombre.requerido");
		listaExcepciones.add(errorCodigoVacio);
		excepcionEsperada.setListaExcepciones(listaExcepciones);
		Mockito.doThrow(excepcionEsperada).when(_mockCatalogoManager).validar(categoria);
		
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
		Mockito.verify(_mockCatalogoManager).validar(categoria);
		Assert.assertThat(testExcepcion, IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones(), IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones().size(), IsEqual.equalTo(1));
	}
	
}
