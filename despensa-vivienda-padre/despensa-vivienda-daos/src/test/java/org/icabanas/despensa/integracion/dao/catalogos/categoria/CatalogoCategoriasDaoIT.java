package org.icabanas.despensa.integracion.dao.catalogos.categoria;

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.dao.catalogos.categoria.impl.jpa.CatalogoCategoriaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.impl.GenericDao;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.dao.jpa.it.AbstractTestIT;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CatalogoCategoriasDaoIT extends AbstractTestIT {

	private CatalogoCategoriaDao _dao;
//	private Categoria categoria;
	private GenericDao<Long, Producto> _daoProducto;

	@Before
	public void configura_test(){
		_dao = new CatalogoCategoriaDao();
		GestorPersistenciaJPA gestorPersistencia = 
				new GestorPersistenciaJPA(getEntityManager());
		_daoProducto = new GenericDao<Long, Producto>(Producto.class);
		
		_dao.setGestorPersistencia(gestorPersistencia);
		_daoProducto.setGestorPersistencia(gestorPersistencia);
		
	}
	
	@Test
	public void deberia_crear_categoria(){		
		// PREPARACIÓN
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		
		// EJECUCIÓN		
		
		// VERIFICACIÓN
		Assert.assertThat(categoria.getId(), IsNull.notNullValue());
		Assert.assertThat(categoria.getNombre(), IsEqual.equalTo(nombre));
		Assert.assertThat(categoria.getDescripcion(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_crear_categoria_con_descripcion(){		
		// PREPARACIÓN
		String nombre = "Una Categoría";
		String descripcion = "Descripción categoría";
		Categoria categoria = new Categoria(nombre,descripcion);
		_dao.crear(categoria);
		
		// EJECUCIÓN		
		
		// VERIFICACIÓN
		Assert.assertThat(categoria.getId(), IsNull.notNullValue());
		Assert.assertThat(categoria.getNombre(), IsEqual.equalTo(nombre));
		Assert.assertThat(categoria.getDescripcion(), IsEqual.equalTo(descripcion));
	}
	
	@Test
	public void deberia_actualizar_categoria(){
		// PREPARACIÓN
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		String nombreActualizado = "Una categoría modificada";
		categoria.setNombre(nombreActualizado);
		
		// EJECUCIÓN
		_dao.modificar(categoria);
		
		// VERIFICACIÓN
		Categoria categoria2 = _dao.buscarPorId(categoria.getId());
		Assert.assertThat(categoria2.getNombre(), IsEqual.equalTo(nombreActualizado));
		Assert.assertThat(categoria2.getDescripcion(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_eliminar_categoria(){
		// PREPARACIÓN
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
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
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
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
	
	@Test
	public void deberia_comprobar_que_una_categoria_no_tiene_productos_asociados() throws Exception {
		// PREPARACIÓNString nombre = "Una Categoría";
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		// creo una nueva categoría
		Categoria nuevaCategoria = new Categoria("Nueva Categoría");
		_dao.crear(nuevaCategoria);
		// creo un producto que tiene asociado la nueva categoría creada
		Producto producto = new Producto("cod-1", "Leche", nuevaCategoria); 
		_daoProducto.crear(producto);
		
		// EJECUCIÓN
		boolean resultado = _dao.tieneProductosAsociados(categoria.getId());

		// VERIFICACIÓN
		assertThat(resultado, IsEqual.equalTo(Boolean.FALSE));		
	}
	
	@Test
	public void deberia_comprobar_que_una_categoria_tiene_productos_asociados() throws Exception {
		// PREPARACIÓN
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		// creo un producto que tiene asociado la categoria
		Producto producto = new Producto("cod-1", "Leche", categoria); 
		_daoProducto.crear(producto);
		
		// EJECUCIÓN
		boolean resultado = _dao.tieneProductosAsociados(categoria.getId());

		// VERIFICACIÓN
		assertThat(resultado, IsEqual.equalTo(Boolean.TRUE));
	}
	
	@Test
	public void deberia_devolver_true_si_existe_una_categoria_con_un_nombre_determinado() throws Exception {
		// PREPARACIÓN
		// creo una categoría
		String nombre = "Una Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		Categoria categoria2 = new Categoria(nombre);
		
		// EJECUCIÓN
		boolean resultado = _dao.esCategoriaDuplicada(categoria2);
		
		// VERIFICACIÓN
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void deberia_devolver_false_si_no_existe_una_categoria_con_un_nombre_determinado() throws Exception {
		// PREPARACIÓN
		// creo una categoría
		String nombre = "Una Categoría";
		String nombre2 = "Otra Categoría";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		Categoria categoria2 = new Categoria(nombre2);
		
		// EJECUCIÓN
		boolean resultado = _dao.esCategoriaDuplicada(categoria2);
		
		// VERIFICACIÓN
		Assert.assertFalse(resultado);
	}

	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub
		
	}
}
