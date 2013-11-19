package org.icabanas.despensa.catalogos.categorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.icabanas.despensa.adaptadores.catalogos.categorias.CategoriaAdapter;
import org.icabanas.despensa.catalogos.CRUDTest;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.impl.CatalogoCategoriasImpl;
import org.icabanas.despensa.dao.catalogos.categoria.ICatalogoCategoriaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoCategoriasTest extends CRUDTest<CategoriaDto, Long, Categoria>{

	private ICatalogoCategorias catalogoCategorias;
	
	@Mock
	private ICatalogoCategoriaDao _mockDao;
	
	@Before
	public void configuraTest(){
		catalogoCategorias = new CatalogoCategoriasImpl(_mockDao);
	}

	@Test
	public void deberia_lanzar_una_excepcion_al_registrar_una_categoria_con_un_nombre_de_una_categoria_existente() throws Exception {
		// PREPARACIÓN
		CategoriaDto catExistente = getTestDtos().get(CRUDTest.KEY_DTO_A_REGISTRAR);
		String nombreCat = catExistente.getNombre();		
		CategoriaDto cat = new CategoriaDto(nombreCat);
		Mockito.when(_mockDao.esCategoriaDuplicada(CategoriaAdapter.toEntidad(cat))).thenReturn(Boolean.TRUE);
		
		// EJECUCIÓN
		RegistrarException rex = null;
		try{
			catalogoCategorias.registrar(cat);
			Assert.fail("Debería lanzar la excepción RegistrarException.");
		}
		catch(RegistrarException e){
			rex = e;
		}

		// VERIFICACIÓN
		ValidacionException vex = (ValidacionException) rex.getCause();
		List<ErrorValidacion> listaErrores = vex.getListaExcepciones();
		Assert.assertThat(1, IsEqual.equalTo(listaErrores.size()));
		Assert.assertThat("error.categoria.existe", IsEqual.equalTo((listaErrores.get(0)).getClaveMensaje()));
		Mockito.verify(_mockDao).esCategoriaDuplicada(CategoriaAdapter.toEntidad(cat));
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

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.catalogos.CRUDTest#getTestDtos()
	 */
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
