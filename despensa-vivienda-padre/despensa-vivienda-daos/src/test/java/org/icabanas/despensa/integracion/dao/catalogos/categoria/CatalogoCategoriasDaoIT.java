package org.icabanas.despensa.integracion.dao.catalogos.categoria;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.dao.catalogos.categoria.CatalogoCategoriaDao;
import org.icabanas.despensa.integracion.AbstractTestIT;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CatalogoCategoriasDaoIT extends AbstractTestIT {

	private CatalogoCategoriaDao _dao;

	@Before
	public void configura_test(){
		_dao = new CatalogoCategoriaDao();
		GestorPersistenciaJPA gestorPersistencia = 
				new GestorPersistenciaJPA(getEntityManager());
		_dao.setGestorPersistencia(gestorPersistencia);
	}
	
	@Test
	public void deberia_crear_categoria(){
		// PREPARACIÓN
		Categoria categoria = new Categoria("Una Categoría");
		
		// EJECUCIÓN		
		_dao.crear(categoria);
		
		// VERIFICACIÓN
		Assert.assertThat(categoria.getId(), IsNull.notNullValue());
	}
	
	@Test
	public void deberia_actualizar_categoria(){
		// PREPARACIÓN
		Categoria categoria = new Categoria("Una Categoría");
		_dao.crear(categoria);
		categoria.setNombre("Una categoría modificada");
		
		// EJECUCIÓN
		_dao.modificar(categoria);
		
		// VERIFICACIÓN
		Categoria categoria2 = _dao.buscarPorId(categoria.getId());
		Assert.assertThat(categoria2.getNombre(), IsEqual.equalTo("Una categoría modificada"));
	}
	
	@Test
	public void deberia_eliminar_categoria(){
		// PREPARACIÓN
		Categoria categoria = new Categoria("Una Categoría");
		_dao.crear(categoria);
		
		// EJECUCIÓN
		_dao.eliminar(categoria);
		
		// VERIFICACIÓN
		Categoria categoria2 = _dao.buscarPorId(categoria.getId());
		Assert.assertThat(categoria2, IsNull.nullValue());
	}
	
	@Test
	public void deberia_buscar_categoria_por_nombre(){
		// PREPARACIÓN
		Categoria categoria = new Categoria("Una Categoría");
		_dao.crear(categoria);		
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setNombreFiltro("categoriaFiltro");
		filtro.setNombre("categ");
		
		// EJECUCIÓN
		List<Categoria> listado = _dao.buscar(filtro );
		
		// VERIFICACIÓN
		Assert.assertThat(listado, IsNull.notNullValue());
		Assert.assertThat(listado.size(), IsEqual.equalTo(1));
	}
}
