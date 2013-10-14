package org.icabanas.despensa.catalogos.categorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icabanas.despensa.catalogos.CRUDTest;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.impl.CatalogoCategoriasImpl;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoCategoriasTest extends CRUDTest<CategoriaDto, Long, Categoria>{

	private ICatalogoCategorias catalogoCategorias;
	
	@Mock
	private IGenericDao<Long, Categoria> _mockDao;
	
	@Before
	public void configuraTest(){
		catalogoCategorias = new CatalogoCategoriasImpl(_mockDao);
	}

	@Override
	protected List<Categoria> getTestDatosPaginacion() {
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categorias.add(new Categoria("Alimentación"));
		categorias.add(new Categoria("Ferretería"));
		categorias.add(new Categoria("Ropa"));
		categorias.add(new Categoria("Carnicería"));		
		
		return categorias;
	}

	@Override
	protected Map<String, Categoria> getTestEntidades() {
		Map<String, Categoria> testEntidades = new HashMap<String, Categoria>();

		Categoria entidadAModificar = new Categoria("Alimentación");
		entidadAModificar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_MODIFICAR, entidadAModificar);
		Categoria  entidadModificada = new Categoria("Ferretería");
		entidadModificada.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_MODIFICADA,  entidadModificada);
		Categoria entidadAEliminar = new Categoria();
		entidadAEliminar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_ELIMINAR, entidadAEliminar);
		Categoria entidadBusqueda = new Categoria("Alimentación");
		entidadBusqueda.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_BUSQUEDA, entidadBusqueda);

		return testEntidades;
	}

	@Override
	protected IGenericDao<Long, Categoria> getMockDao() {
		return _mockDao;
	}

	@Override
	protected IGenericManager<Long, CategoriaDto> getServicio() {
		return catalogoCategorias;
	}

	@Override
	protected Map<String, CategoriaDto> getTestDtoEsperados() {
		Map<String, CategoriaDto> testDtos = new HashMap<String, CategoriaDto>();
		
		CategoriaDto dtoEsperadoRegistrar = new CategoriaDto(1L,"Alimentación");
		testDtos.put(KEY_DTO_ESPERADO_REGISTRAR, dtoEsperadoRegistrar);
		
		CategoriaDto dtoEsperadoModificar = new CategoriaDto(1L, "Ferretería");		
		testDtos.put(KEY_DTO_ESPERADO_MODIFICAR, dtoEsperadoModificar);
		
		CategoriaDto dtoEsperadoBusqueda = new CategoriaDto(1L, "Alimentación");		
		testDtos.put(KEY_DTO_ESPERADO_BUSCAR, dtoEsperadoBusqueda);
		
		return testDtos;
	}

	@Override
	protected Map<String, CategoriaDto> getTestDtos() {
		Map<String, CategoriaDto> dtos = new HashMap<String, CategoriaDto>();
		
		CategoriaDto dto_a_registrar = new CategoriaDto("Alimentación");
		dtos.put(CRUDTest.KEY_DTO_A_REGISTRAR, dto_a_registrar);
		
		CategoriaDto dto_no_valido = new CategoriaDto();
		dtos.put(CRUDTest.KEY_DTO_NO_VALIDO, dto_no_valido);
		
		CategoriaDto dto_a_modificar = new CategoriaDto(1L,"Ferretería");
		dtos.put(CRUDTest.KEY_DTO_A_MODIFICAR, dto_a_modificar);
		
		CategoriaDto dto_no_valido_modificacion = new CategoriaDto(1L,"");
		dtos.put(CRUDTest.KEY_DTO_NO_VALIDO_PARA_MODIFICACION, dto_no_valido_modificacion);
		
		return dtos;
	}
	
	

}
