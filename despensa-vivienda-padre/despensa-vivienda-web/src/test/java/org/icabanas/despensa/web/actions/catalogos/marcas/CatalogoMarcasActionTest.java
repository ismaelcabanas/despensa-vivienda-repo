package org.icabanas.despensa.web.actions.catalogos.marcas;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
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
public class CatalogoMarcasActionTest extends AbstractCatalogoActionTest<Long, MarcaDto> {
	
	@Mock
	private HttpServletRequest _mockRequest;
	
	@Before
	public void configura_test(){
		accion = new CatalogoMarcasAction(_mockCatalogoManager);
	}

	@Override
	protected MarcaDto getDto() {
		MarcaDto marca = new MarcaDto(1L);
		return marca;
	}

	@Override
	protected MarcaDto getDtoParaRegistrar() {
		MarcaDto marca = new MarcaDto("Hacendado");
		return marca;
	}

	@Override
	protected MarcaDto getDtoParaActualizar() {
		MarcaDto marca = new MarcaDto(1L,"Hacendado");
		return marca;
	}

	@Override
	protected MarcaDto getDtoEsperado() {
		MarcaDto marca = new MarcaDto(1L,"Hacendado");
		return marca;
	}

	@Override
	protected IFiltro getFiltroParaPaginacion() {
		MarcaFiltro filtro = new MarcaFiltro();
		filtro.setNombre("Hacendado");
		
		return filtro;
	}
	
	@Test
	public void deberia_lanzar_excepcion_validacion_si_valida_marca_sin_nombre() throws Exception{
		// preparación
		MarcaDto marca = new MarcaDto();
		accion.setDto(marca);
		ValidacionException excepcionEsperada = new ValidacionException();
		List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
		ErrorValidacion errorCodigoVacio = new ErrorValidacion("marca.nombre", "marca.nombre.requerido");
		listaExcepciones.add(errorCodigoVacio);
		excepcionEsperada.setListaExcepciones(listaExcepciones);
		Mockito.doThrow(excepcionEsperada).when(_mockCatalogoManager).validar(marca);
		
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
		Mockito.verify(_mockCatalogoManager).validar(marca);
		Assert.assertThat(testExcepcion, IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones(), IsNull.notNullValue());
		Assert.assertThat(testExcepcion.getListaExcepciones().size(), IsEqual.equalTo(1));
	}
	
}
