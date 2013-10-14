package org.icabanas.despensa.integracion.dao.catalogos.producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoFiltro;
import org.icabanas.despensa.dao.catalogos.marca.criteria.MarcaJPACriteriaBuilder;
import org.icabanas.despensa.dao.catalogos.marca.impl.CatalogoMarcasDao;
import org.icabanas.despensa.dao.catalogos.producto.criteria.ProductoJPACriteriaBuilder;
import org.icabanas.despensa.dao.catalogos.producto.impl.CatalogoProductosDao;
import org.icabanas.despensa.integracion.AbstractTestIT;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CatalogoProductosDaoIT extends AbstractTestIT {

	private CatalogoProductosDao _dao;
	
	private CatalogoMarcasDao _marcaDao;

	private Map<String, ICriteriaBuilder<Producto>> criteriaBuilderMap1;

	@Before
	public void configura_test(){
//		_dao = new CatalogoProductosDAOImpl(getEntityManager());
		_dao = new CatalogoProductosDao();		
		GestorPersistenciaJPA gestorPersistencia1 = 
				new GestorPersistenciaJPA(getEntityManager());
		_dao.setGestorPersistencia(gestorPersistencia1);
		criteriaBuilderMap1 = new HashMap<String, ICriteriaBuilder<Producto>>();
		ICriteriaBuilder<Producto> productoCriteriaBuilder = new ProductoJPACriteriaBuilder(Producto.class);
		criteriaBuilderMap1.put("productoFiltro", productoCriteriaBuilder);
		_dao.setCriteriaBuilderMap(criteriaBuilderMap1);
		
		_marcaDao = new CatalogoMarcasDao();		
		GestorPersistenciaJPA gestorPersistencia = 
				new GestorPersistenciaJPA(getEntityManager());
		_marcaDao.setGestorPersistencia(gestorPersistencia);
		Map<String, ICriteriaBuilder<Marca>> criteriaBuilderMap = new HashMap<String, ICriteriaBuilder<Marca>>();
		ICriteriaBuilder<Marca> marcaCriteriaBuilder = new MarcaJPACriteriaBuilder(Marca.class);
		criteriaBuilderMap.put("marcaFiltro", marcaCriteriaBuilder);
		_marcaDao.setCriteriaBuilderMap(criteriaBuilderMap);
				
		bateria_datos();
	}
	
	@Test
	public void deberia_devolver_producto_por_codigo_producto(){
		// preparación		
		String codigo = "cod-0007";		
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setCodigo(codigo);
		
		// ejecución
		Producto test = _dao.buscarUnico(filtro);
		
		// verificación
		Assert.assertThat(test, IsNull.notNullValue());
		Assert.assertThat(test.getNombre(), IsEqual.equalTo("Macarrones"));
	}
	
	@Test
	public void deberia_devolver_null_si_no_existe_producto_con_codigo(){
		// preparación		
		String codigo = "cod-1001";
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setCodigo(codigo);
		
		// ejecución
		Producto test = _dao.buscarUnico(filtro);
		
		// verificación
		Assert.assertThat(test, IsNull.nullValue());
	}
	
//	@Test(expected=DaoException.class)
//	public void deberia_lanzar_excepcion_si_falla_al_buscar_producto_por_codigo(){
//		// preparación		
//		String codigo = "cod-1001";
//		ProductoFiltro filtro = new ProductoFiltro();
//		filtro.setCodigo(codigo);
//		@SuppressWarnings("unchecked")
//		IProcesadorConsultas<Producto> _mockProcesadorConsultas = Mockito.mock(IProcesadorConsultas.class);
//		IGestorPersistencia _mockGestorPersistencia = Mockito.mock(IGestorPersistencia.class);
//		CatalogoProductosDAOImpl _dao = new CatalogoProductosDAOImpl();
////		_dao.setEntityManager(getEntityManager());
//		_dao.setGestorPersistencia(_mockGestorPersistencia);				
//		Mockito.when(_mockGestorPersistencia.buscarUnico(filtro, Producto.class, criteriaBuilderMap1.get("productoFiltro"))).thenThrow(new DaoException());
//		
//		// ejecución
//		@SuppressWarnings("unused")
//		Producto test = _dao.buscarUnico(filtro);
//	}
		
	@Test
	public void deberia_buscar_productos_por_criterio_busqueda_nombre(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setNombre("ech");
		
		// ejecución				
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(2));
		Assert.assertThat(resultado.get(0).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.get(0).getCodigo(),IsEqual.equalTo("cod-0001"));
		Assert.assertThat(resultado.get(1).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.get(1).getCodigo(),IsEqual.equalTo("cod-0002"));
	}
	
	@Test
	public void deberia_buscar_productos_por_criterio_busqueda_marca(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setMarca(new MarcaDto(1L));
		
		// ejecución
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(3));
		Assert.assertThat(resultado.get(0).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.get(0).getCodigo(),IsEqual.equalTo("cod-0001"));
		Assert.assertThat(resultado.get(1).getNombre(),IsEqual.equalTo("Yogur"));
		Assert.assertThat(resultado.get(1).getCodigo(),IsEqual.equalTo("cod-0003"));
		Assert.assertThat(resultado.get(2).getNombre(),IsEqual.equalTo("Natillas"));
		Assert.assertThat(resultado.get(2).getCodigo(),IsEqual.equalTo("cod-0006"));
	}
	
	@Test
	public void deberia_buscar_productos_por_criterio_busqueda_todos_campos(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setMarca(new MarcaDto(1L));
		filtro.setNombre("ech");
		filtro.setCodigo("cod-0001");
		
		// ejecución
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(1));
		Assert.assertThat(resultado.get(0).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.get(0).getCodigo(),IsEqual.equalTo("cod-0001"));						
	}	
	
	@Test
	public void deberia_devolver_vacio_si_buscar_productos_por_criterio_busqueda_y_no_hay_registros(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setNombre("espa");
		
		// ejecución
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(0));
	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_criterio_busqueda_nulo(){
		// preparación
		ProductoFiltro filtro = null;
		
		// ejecución
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(7));
	}
	
	@Test
	public void deberia_buscar_todos_productos_si_criterio_busqueda_nombre_y_codigo_es_vacio(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setNombre("");
		filtro.setCodigo("");
		
		// ejecución
		List<Producto> resultado = _dao.buscar(filtro);
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(),IsEqual.equalTo(7));
	}
	
	@Test
	public void deberia_paginar_productos_por_criterio_busqueda(){
		// preparación
		ProductoFiltro filtro = new ProductoFiltro();
		IPaginador<Producto> pagina = new Pagina<Producto>();
		pagina.setFiltro(filtro);
		pagina.setNumeroTotalRegistrosPorPagina(2);
		pagina.setPagina(1);
		
		// ejecución
		IPaginador<Producto> resultado = _dao.paginar(pagina);
				
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.getDatos().size(),IsEqual.equalTo(2));
		Assert.assertThat(resultado.getDatos().get(0).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.getDatos().get(0).getCodigo(),IsEqual.equalTo("cod-0001"));
		Assert.assertThat(resultado.getDatos().get(1).getNombre(),IsEqual.equalTo("Leche"));
		Assert.assertThat(resultado.getDatos().get(1).getCodigo(),IsEqual.equalTo("cod-0002"));
		Assert.assertThat(resultado.getNumeroTotalPaginas(),IsEqual.equalTo(4));
		Assert.assertThat(resultado.getPagina(),IsEqual.equalTo(1));		
		Assert.assertThat(resultado.getNumeroTotalRegistros(),IsEqual.equalTo(7));
		Assert.assertThat(resultado.getNumeroRegistrosPorPagina(),IsEqual.equalTo(2));
	}
	
	private void bateria_datos(){
		Marca marca1 = new Marca("Pascual");
		_marcaDao.crear(marca1);
		Marca marca2 = new Marca("Hacendado");
		_marcaDao.crear(marca2);
		
		Producto p1 = new Producto("Leche", marca1, "cod-0001");
		Producto p2 = new Producto("Leche", marca2, "cod-0002");
		Producto p3 = new Producto("Yogur", marca1, "cod-0003");
		Producto p4 = new Producto("Atún", marca2, "cod-0004");
		Producto p5 = new Producto("Arroz", marca2, "cod-0005");
		Producto p6 = new Producto("Natillas", marca1, "cod-0006");
		Producto p7 = new Producto("Macarrones", marca2, "cod-0007");
		
		_dao.crear(p1);
		_dao.crear(p2);
		_dao.crear(p3);
		_dao.crear(p4);
		_dao.crear(p5);
		_dao.crear(p6);
		_dao.crear(p7);
		
	}
}
