package org.icabanas.despensa.integracion.dao.catalogos.marca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
import org.icabanas.despensa.dao.catalogos.marca.criteria.MarcaJPACriteriaBuilder;
import org.icabanas.despensa.dao.catalogos.marca.impl.jpa.CatalogoMarcaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Marca_;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.consulta.OrderEnum;
import org.icabanas.jee.api.integracion.dao.impl.GenericDao;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.dao.jpa.it.AbstractTestIT;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CatalogoMarcasDaoIT extends AbstractTestIT{

	private CatalogoMarcaDao _dao;
	private GenericDao<Long, Producto> _daoProducto;
	private GenericDao<Long, Categoria> _daoCategoria;
	private Categoria categoriaPorDefecto;

	@Before
	public void configura_test(){
//		_dao = new CatalogoMarcasDAOImpl(getEntityManager());
		// creo la instancia del DAO
		_dao = new CatalogoMarcaDao();
		_daoProducto = new GenericDao<Long, Producto>();
		_daoCategoria = new GenericDao<Long, Categoria>();
		
		// creo la instancia del creador de Criteria
		Map<String, ICriteriaBuilder<Marca>> criteriaBuilderMap = new HashMap<String, ICriteriaBuilder<Marca>>();
		ICriteriaBuilder<Marca> marcaCriteriaBuilder = new MarcaJPACriteriaBuilder(Marca.class);
		criteriaBuilderMap.put("marcaFiltro", marcaCriteriaBuilder);		

		//		_dao.setEntityManager(getEntityManager());
		// creo el gestor de persistencia
		GestorPersistenciaJPA gestorPersistencia = 
				new GestorPersistenciaJPA(getEntityManager());
//		IProcesadorConsultas<Marca> procesadorConsultas = new ProcesadorConsultasCriteria<Marca>(getEntityManager(), Marca.class);
//		gestorPersistencia.setProcesadorConsultas(procesadorConsultas);
		
		// establezco el gestor de persistencia al DAO
		_dao.setGestorPersistencia(gestorPersistencia);
		_daoProducto.setGestorPersistencia(gestorPersistencia);
		_daoCategoria.setGestorPersistencia(gestorPersistencia);
		
		// creo la categoría por defecto
		categoriaPorDefecto = new Categoria("Prueba Categoría");
		_daoCategoria.crear(categoriaPorDefecto);
		
		// establezco el criteriaBuilder
		_dao.setCriteriaBuilderMap(criteriaBuilderMap);
	}
	
	@Test
	public void deberia_crear_marca(){
		// PREPARACIÓN
		Marca marca = new Marca("Una Marca");
		
		// EJECUCIÓN		
		_dao.crear(marca);
		
		// VERIFICACIÓN
		Assert.assertThat(marca.getId(), IsNull.notNullValue());
	}
	
	@Test
	public void deberia_actualizar_marca(){
		// PREPARACIÓN
		Marca marca = new Marca("Una Marca");
		_dao.crear(marca);
		marca.setNombre("Una marca modificada");
		
		// EJECUCIÓN
		_dao.modificar(marca);
		
		// VERIFICACIÓN
		Marca marcaActualizada = _dao.buscarPorId(marca.getId());
		Assert.assertThat(marcaActualizada.getNombre(), IsEqual.equalTo("Una marca modificada"));
	}
	
	@Test
	public void deberia_eliminar_marca(){
		// PREPARACIÓN
		Marca marca = new Marca("Una Marca");
		_dao.crear(marca);
		
		// EJECUCIÓN
		_dao.eliminar(marca);
		
		// VERIFICACIÓN
		Marca marcaEliminada = _dao.buscarPorId(marca.getId());
		Assert.assertThat(marcaEliminada, IsNull.nullValue());
	}
	
	@Test
	public void deberia_buscar_todas_las_marcas_y_devolverlas_ordenadas_por_nombre(){
		// preparación
		Marca marca1 = new Marca("Lauki");
		Marca marca2 = new Marca("Hacendado");
		_dao.crear(marca1);
		_dao.crear(marca2);
		
		MarcaFiltro filtro = new MarcaFiltro();
		List<Orden> orden = new ArrayList<Orden>();
		orden.add(new Orden(Marca_.nombre.getName(), OrderEnum.ASC));
		filtro.setOrden(orden );
		
		// ejecución
		List<Marca> resultado = _dao.buscar(filtro );
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(), IsEqual.equalTo(2));
		Assert.assertThat(resultado.get(0).getNombre(), IsEqual.equalTo("Hacendado"));
		Assert.assertThat(resultado.get(1).getNombre(), IsEqual.equalTo("Lauki"));
	}
	
	@Test
	public void deberia_buscar_marcas_por_nombre(){
		// preparación
		Marca marca1 = new Marca("Lauki");
		Marca marca2 = new Marca("Hacendado");
		_dao.crear(marca1);
		_dao.crear(marca2);
		
		MarcaFiltro filtro = new MarcaFiltro();
		filtro.setNombre("uk");
		
		// ejecución
		List<Marca> resultado = _dao.buscar(filtro );
		
		// verificación
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(), IsEqual.equalTo(1));
		Assert.assertThat(resultado.get(0).getNombre(), IsEqual.equalTo("Lauki"));
	}
	
	@Test
	public void deberia_haber_marcas_con_productos_asociados(){
		// PREPARACIÓN
		// creo la marca
		Marca marca1 = new Marca("Lauki");
		_dao.crear(marca1);
		// creo una categoría
		Categoria categoria = new Categoria("Lácteos");
		_daoCategoria.crear(categoria);
		
		// creo un producto de la marca creada anteriormente		
		Producto producto = new Producto("cod-1", "Leche", categoria, marca1);
		_daoProducto.crear(producto);
		
		// EJECUCIÓN
		boolean resultado = _dao.tieneProductosAsociados(marca1.getId());
		
		// VERIFICACIÓN
		Assert.assertThat(resultado, Is.is(Boolean.TRUE));		
	}
	
	@Test
	public void no_deberia_haber_marcas_con_productos_asociados(){
		// PREPARACIÓN
		// creo la marca
		Marca marca1 = new Marca("Lauki");
		_dao.crear(marca1);
		// creo un producto de la marca creada anteriormente
		Producto producto = new Producto("cod-1", "Leche", categoriaPorDefecto, marca1);
		_daoProducto.crear(producto);
		
		// EJECUCIÓN
		boolean resultado = _dao.tieneProductosAsociados(5555L);
		
		// VERIFICACIÓN
		Assert.assertThat(resultado, Is.is(Boolean.FALSE));		
	}

	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub
		
	}
	
//	@Test(expected=DaoException.class)	
//	public void deberia_lanzar_excepcion_si_falla_al_buscar_todas_las_marcas_y_devolverlas_ordenadas_por_nombre(){				
//		// preparación
//		@SuppressWarnings("unchecked")
////		IProcesadorConsultas<Marca> _mockProcesadorConsultas = Mockito.mock(IProcesadorConsultas.class);
//		IGestorPersistencia _mockGestorPersistencia = Mockito.mock(IGestorPersistencia.class);
//		ICriteriaBuilder<Marca> _mockCriteriaBuilder = Mockito.mock(ICriteriaBuilder.class);
//		IFiltro _mockFiltro = Mockito.mock(IFiltro.class);
//		
//		Mockito.when(_mockGestorPersistencia.buscar(_mockFiltro, _dao.getPersistentClass(), Mockito.anyObject()).thenThrow(new DaoException());
//		
//		// ejecución
//		@SuppressWarnings("unused")
//		List<Marca> resultado = _dao.buscar(_mockFiltro);
//	}
}
