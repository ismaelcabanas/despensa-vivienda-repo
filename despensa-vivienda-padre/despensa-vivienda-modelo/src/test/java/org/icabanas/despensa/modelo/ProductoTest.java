package org.icabanas.despensa.modelo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Before;
import org.junit.Test;

public class ProductoTest {
	
	private Producto _producto;
	
	@Before
	public void preparaTest(){
		_producto = new Producto();				
	}

	@Test
	public void deberia_registrar_un_producto(){
		// preparaci�n
		String codigo = "prod-001";
		String nombre = "Leche";
		Marca marca = new Marca(1L);
		
		// ejecuci�n
		Producto test = null;		
		try {			
			test = Producto.registrar(codigo, nombre, marca);
		} catch (ValidacionException e) {
			fail("Deber�a registrar el producto.");
		}
		
		// verificaci�n
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), notNullValue());
		assertThat(test.getMarca().getId(), equalTo(1L));
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test
	public void deberia_registrar_un_producto_sin_especificar_marca(){
		// preparaci�n
		String codigo = "prod-001";
		String nombre = "Leche";
		
		// ejecuci�n
		Producto test = null;
		try {
			test = Producto.registrar(codigo, nombre);
		} catch (ValidacionException e) {
			fail("Deber�a registrar el producto.");
		}
		
		// verificaci�n
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), nullValue());	
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test
	public void deberia_registrar_un_producto_con_categoria(){
		// preparaci�n
		String codigo = "prod-001";
		String nombre = "Leche";
		Categoria categoria = new Categoria(1L);
		
		// ejecuci�n
		Producto test = null;
		try {
			test = Producto.registrar(codigo, nombre, categoria);
		} catch (ValidacionException e) {
			fail("Deber�a registrar el producto.");
		}
		
		// verificaci�n
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), nullValue());	
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_sin_nombre() throws ValidacionException{
		// preparaci�n
		
		// ejecuci�n
		Producto test = null;
		test = Producto.registrar("prod-001", null);
				
		// verificaci�n
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_con_nombre_vacio() throws ValidacionException{
		// preparaci�n
		
		// ejecuci�n
		Producto test = null;
		test = Producto.registrar("prod-001", "");
				
		// verificaci�n
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_sin_codigo() throws ValidacionException{
		// preparaci�n
		
		// ejecuci�n
		Producto test = null;
		test = Producto.registrar(null, "Leche");
				
		// verificaci�n
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_con_codigo_vacio() throws ValidacionException{
		// preparaci�n
		
		// ejecuci�n
		Producto test = null;
		test = Producto.registrar("", "Leche");
				
		// verificaci�n
					
	}
	
//	@Test(expected=ValidacionException.class)
//	public void altaDeProductoConNombreVacio() throws Exception{		
//		_producto.crea("");
//	}
//	
//	@Test(expected=ValidacionException.class)
//	public void altaDeProductoConMarcaConNombreVacio() throws Exception{
//		_producto.crea("", "Pascual");
//	}
	
	@Test
	public void valida_producto_correcto(){
		// preparaci�n
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		_producto.setNombre("Leche");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecuci�n
		boolean esValido;
		try {
			esValido = _producto.valida();
		} catch (ValidacionException e) {
			esValido = false;
		}
		
		// verificaci�n
		assertThat(esValido, is(Boolean.TRUE));
	}
	
	@Test
	public void valida_producto_sin_nombre(){
		// preparaci�n
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecuci�n
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("El nombre del producto es obligatorio.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificaci�n
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificaci�n
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void valida_producto_sin_codigo(){
		// preparaci�n
		_producto.setNombre("Leche");
		_producto.setMarca("Parmalat");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecuci�n
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("El c�digo del producto es obligatorio.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificaci�n
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificaci�n
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void valida_producto_sin_categoria(){
		// preparaci�n
		_producto.setNombre("Leche");
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		
		// ejecuci�n
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("La categor�a del producto es obligatoria.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificaci�n
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificaci�n
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void comprueba_producto_no_es_vacio(){
		// preparaci�n
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		
		// ejecuci�n
		boolean resultado = _producto.esVacia();
		
		// verificaci�n
		assertThat(resultado, is(Boolean.FALSE));
	}
	
	@Test
	public void comprueba_producto_es_vacio(){
		// preparaci�n
		
		// ejecuci�n
		boolean resultado = _producto.esVacia();
		
		// verificaci�n
		assertThat(resultado, is(Boolean.TRUE));
	}
}
