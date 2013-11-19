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
		// PREPARACI�N
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		
		// EJECUCI�N		
		
		// VERIFICACI�N
		Assert.assertThat(categoria.getId(), IsNull.notNullValue());
		Assert.assertThat(categoria.getNombre(), IsEqual.equalTo(nombre));
		Assert.assertThat(categoria.getDescripcion(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_crear_categoria_con_descripcion(){		
		// PREPARACI�N
		String nombre = "Una Categor�a";
		String descripcion = "Descripci�n categor�a";
		Categoria categoria = new Categoria(nombre,descripcion);
		_dao.crear(categoria);
		
		// EJECUCI�N		
		
		// VERIFICACI�N
		Assert.assertThat(categoria.getId(), IsNull.notNullValue());
		Assert.assertThat(categoria.getNombre(), IsEqual.equalTo(nombre));
		Assert.assertThat(categoria.getDescripcion(), IsEqual.equalTo(descripcion));
	}
	
	@Test
	public void deberia_actualizar_categoria(){
		// PREPARACI�N
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		String nombreActualizado = "Una categor�a modificada";
		categoria.setNombre(nombreActualizado);
		
		// EJECUCI�N
		_dao.modificar(categoria);
		
		// VERIFICACI�N
		Categoria categoria2 = _dao.buscarPorId(categoria.getId());
		Assert.assertThat(categoria2.getNombre(), IsEqual.equalTo(nombreActualizado));
		Assert.assertThat(categoria2.getDescripcion(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_eliminar_categoria(){
		// PREPARACI�N
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		
		// EJECUCI�N
		_dao.eliminar(categoria);
		
		// VERIFICACI�N
		Categoria categoria2 = _dao.buscarPorId(categoria.getId());
		Assert.assertThat(categoria2, IsNull.nullValue());
	}
	
	@Test
	public void deberia_buscar_categoria_por_nombre(){
		// PREPARACI�N
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setNombreFiltro("categoriaFiltro");
		filtro.setNombre("categ");
		
		// EJECUCI�N
		List<Categoria> listado = _dao.buscar(filtro );
		
		// VERIFICACI�N
		Assert.assertThat(listado, IsNull.notNullValue());
		Assert.assertThat(listado.size(), IsEqual.equalTo(1));
	}
	
	@Test
	public void deberia_comprobar_que_una_categoria_no_tiene_productos_asociados() throws Exception {
		// PREPARACI�NString nombre = "Una Categor�a";
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		// creo una nueva categor�a
		Categoria nuevaCategoria = new Categoria("Nueva Categor�a");
		_dao.crear(nuevaCategoria);
		// creo un producto que tiene asociado la nueva categor�a creada
		Producto producto = new Producto("cod-1", "Leche", nuevaCategoria); 
		_daoProducto.crear(producto);
		
		// EJECUCI�N
		boolean resultado = _dao.tieneProductosAsociados(categoria.getId());

		// VERIFICACI�N
		assertThat(resultado, IsEqual.equalTo(Boolean.FALSE));		
	}
	
	@Test
	public void deberia_comprobar_que_una_categoria_tiene_productos_asociados() throws Exception {
		// PREPARACI�N
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		// creo un producto que tiene asociado la categoria
		Producto producto = new Producto("cod-1", "Leche", categoria); 
		_daoProducto.crear(producto);
		
		// EJECUCI�N
		boolean resultado = _dao.tieneProductosAsociados(categoria.getId());

		// VERIFICACI�N
		assertThat(resultado, IsEqual.equalTo(Boolean.TRUE));
	}
	
	@Test
	public void deberia_devolver_true_si_existe_una_categoria_con_un_nombre_determinado() throws Exception {
		// PREPARACI�N
		// creo una categor�a
		String nombre = "Una Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		Categoria categoria2 = new Categoria(nombre);
		
		// EJECUCI�N
		boolean resultado = _dao.esCategoriaDuplicada(categoria2);
		
		// VERIFICACI�N
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void deberia_devolver_false_si_no_existe_una_categoria_con_un_nombre_determinado() throws Exception {
		// PREPARACI�N
		// creo una categor�a
		String nombre = "Una Categor�a";
		String nombre2 = "Otra Categor�a";
		Categoria categoria = new Categoria(nombre);
		_dao.crear(categoria);
		Categoria categoria2 = new Categoria(nombre2);
		
		// EJECUCI�N
		boolean resultado = _dao.esCategoriaDuplicada(categoria2);
		
		// VERIFICACI�N
		Assert.assertFalse(resultado);
	}

	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub
		
	}
}
