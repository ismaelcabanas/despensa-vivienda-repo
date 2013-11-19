package org.icabanas.despensa.especificaciones.concordion.catalogos.categorias;

import java.util.HashMap;
import java.util.Map;

import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.ICatalogoCategorias;
import org.icabanas.despensa.catalogos.categorias.impl.CatalogoCategoriasImpl;
import org.icabanas.despensa.dao.catalogos.categoria.criteria.CategoriaJPACriteriaBuilder;
import org.icabanas.despensa.dao.catalogos.categoria.impl.jpa.CatalogoCategoriaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.dao.jpa.it.AbstractTestIT;
import org.junit.Before;

public class AbstractCatalogoCategoriasTest extends AbstractTestIT {

	protected ICatalogoCategorias catalogoCategorias;
	
	/**
	 * Preparación común a todos los test.
	 */
	@Before
	public void configuraTest() {
		// creo la instancia del Dao
		CatalogoCategoriaDao dao = new CatalogoCategoriaDao();
		// creo el criteria builder que realiza los filtros y se los pongo al dao.
		Map<String, ICriteriaBuilder<Categoria>> criteriaBuilderMap = new HashMap<String, ICriteriaBuilder<Categoria>>();
		criteriaBuilderMap.put("categoriaFiltro", new CategoriaJPACriteriaBuilder());
		dao.setCriteriaBuilderMap(criteriaBuilderMap);
		// creo el gesto de persistencia necesario para realizar las consulta y se lo pongo al dao
		IGestorPersistencia gestorPersistencia = new GestorPersistenciaJPA(getEntityManager());
		dao.setGestorPersistencia(gestorPersistencia);		
		// creo la instancia del servicio de catálogos de categorías
		catalogoCategorias = new CatalogoCategoriasImpl(dao);
	}

	public void creaCategoria(String nombre) throws Exception{
		CategoriaDto dto = new CategoriaDto(nombre);
		catalogoCategorias.registrar(dto);
	}
	
	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub

	}

}
