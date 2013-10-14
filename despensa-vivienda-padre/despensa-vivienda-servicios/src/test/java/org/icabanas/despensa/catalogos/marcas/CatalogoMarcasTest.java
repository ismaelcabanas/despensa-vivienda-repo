package org.icabanas.despensa.catalogos.marcas;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icabanas.despensa.catalogos.CRUDTest;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
import org.icabanas.despensa.catalogos.marcas.impl.CatalogoMarcasImpl;
import org.icabanas.despensa.dao.catalogos.marca.ICatalogoMarcaDao;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.consulta.OrderEnum;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoMarcasTest extends CRUDTest<MarcaDto, Long, Marca>{

	private ICatalogoMarcas catalogoMarcas;
	
	@Mock
	private ICatalogoMarcaDao _mockDao;
	
	@Before
	public void configuraTest(){
		catalogoMarcas = new CatalogoMarcasImpl(_mockDao);
	}
	
	@Test
	public void deberia_devolver_todas_las_marcas_ordenadas_por_nombre(){
		// preparación
		List<Marca> marcas = new ArrayList<Marca>();
		marcas.add(new Marca("Hacendado"));
		marcas.add(new Marca("Pascual"));	
		List<Marca> expected = new ArrayList<Marca>();
		expected.add(new Marca("Hacendado"));
		expected.add(new Marca("Pascual"));
		
		IFiltro filtro = new MarcaFiltro();
		List<Orden> orden = new ArrayList<Orden>();
		orden.add(new Orden("nombre", OrderEnum.ASC));
		filtro.setOrden(orden);
		
		when(_mockDao.buscar(filtro)).thenReturn(expected);
		
		// ejecución
		List<MarcaDto> listaMarcas = catalogoMarcas.buscarTodas();
				
		// verificación
		assertThat(listaMarcas, notNullValue());
		assertThat(listaMarcas.get(0).getNombre(), equalTo(marcas.get(0).getNombre()));
		assertThat(listaMarcas.get(1).getNombre(), equalTo(marcas.get(1).getNombre()));
	}

	@Override
	protected List<Marca> getTestDatosPaginacion() {
		List<Marca> marcas = new ArrayList<Marca>();
		
		marcas.add(new Marca("Hacendado"));
		marcas.add(new Marca("Auchan"));
		marcas.add(new Marca("Hipercor"));
		marcas.add(new Marca("Pascual"));		
		
		return marcas;
	}

	@Override
	protected Map<String, Marca> getTestEntidades() {
		Map<String, Marca> testEntidades = new HashMap<String, Marca>();

		Marca entidadAModificar = new Marca("Hacendado");
		entidadAModificar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_MODIFICAR, entidadAModificar);
		Marca  entidadModificada = new Marca("Auchan");
		entidadModificada.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_MODIFICADA,  entidadModificada);
		Marca entidadAEliminar = new Marca();
		entidadAEliminar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_ELIMINAR, entidadAEliminar);
		Marca entidadBusqueda = new Marca("Hacendado");
		entidadBusqueda.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_BUSQUEDA, entidadBusqueda);

		return testEntidades;
	}

	@Override
	protected IGenericDao<Long, Marca> getMockDao() {
		return _mockDao;
	}

	@Override
	protected IGenericManager<Long, MarcaDto> getServicio() {
		return catalogoMarcas;
	}

	@Override
	protected Map<String, MarcaDto> getTestDtoEsperados() {
		Map<String, MarcaDto> testDtos = new HashMap<String, MarcaDto>();
		
		MarcaDto dtoEsperadoRegistrar = new MarcaDto(1L,"Hacendado");
		testDtos.put(KEY_DTO_ESPERADO_REGISTRAR, dtoEsperadoRegistrar);
		
		MarcaDto dtoEsperadoModificar = new MarcaDto(1L, "Auchan");		
		testDtos.put(KEY_DTO_ESPERADO_MODIFICAR, dtoEsperadoModificar);
		
		MarcaDto dtoEsperadoBusqueda = new MarcaDto(1L, "Hacendado");		
		testDtos.put(KEY_DTO_ESPERADO_BUSCAR, dtoEsperadoBusqueda);
		
		return testDtos;
	}

	@Override
	protected Map<String, MarcaDto> getTestDtos() {
		Map<String, MarcaDto> testDtos = new HashMap<String, MarcaDto>();
		MarcaDto dtoARegistrar = new MarcaDto("Hacendado");
		testDtos.put(CRUDTest.KEY_DTO_A_REGISTRAR, dtoARegistrar);
		MarcaDto dtoNoValido = new MarcaDto();
		testDtos.put(CRUDTest.KEY_DTO_NO_VALIDO, dtoNoValido);
		MarcaDto dtoAModificar = new MarcaDto(1L, "Hacendado");
		testDtos.put(CRUDTest.KEY_DTO_A_MODIFICAR, dtoAModificar);
		MarcaDto dtoNoValidoModificacion = new MarcaDto(1L, "");
		testDtos.put(CRUDTest.KEY_DTO_NO_VALIDO_PARA_MODIFICACION, dtoNoValidoModificacion);
		
		return testDtos;
	}
}
