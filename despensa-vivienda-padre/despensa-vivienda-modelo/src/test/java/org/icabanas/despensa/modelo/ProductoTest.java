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
		// preparación
		String codigo = "prod-001";
		String nombre = "Leche";
		Marca marca = new Marca(1L);
		
		// ejecución
		Producto test = null;		
		try {			
			test = Producto.registrar(codigo, nombre, marca);
		} catch (ValidacionException e) {
			fail("Debería registrar el producto.");
		}
		
		// verificación
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), notNullValue());
		assertThat(test.getMarca().getId(), equalTo(1L));
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test
	public void deberia_registrar_un_producto_sin_especificar_marca(){
		// preparación
		String codigo = "prod-001";
		String nombre = "Leche";
		
		// ejecución
		Producto test = null;
		try {
			test = Producto.registrar(codigo, nombre);
		} catch (ValidacionException e) {
			fail("Debería registrar el producto.");
		}
		
		// verificación
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), nullValue());	
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test
	public void deberia_registrar_un_producto_con_categoria(){
		// preparación
		String codigo = "prod-001";
		String nombre = "Leche";
		Categoria categoria = new Categoria(1L);
		
		// ejecución
		Producto test = null;
		try {
			test = Producto.registrar(codigo, nombre, categoria);
		} catch (ValidacionException e) {
			fail("Debería registrar el producto.");
		}
		
		// verificación
		assertThat(test, notNullValue());
		assertThat(test.getCodigo(), equalTo(codigo));
		assertThat(test.getNombre(), equalTo(nombre));
		assertThat(test.getMarca(), nullValue());	
		assertThat(test.getCategoria().getId(), equalTo(-1L));
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_sin_nombre() throws ValidacionException{
		// preparación
		
		// ejecución
		Producto test = null;
		test = Producto.registrar("prod-001", null);
				
		// verificación
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_con_nombre_vacio() throws ValidacionException{
		// preparación
		
		// ejecución
		Producto test = null;
		test = Producto.registrar("prod-001", "");
				
		// verificación
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_sin_codigo() throws ValidacionException{
		// preparación
		
		// ejecución
		Producto test = null;
		test = Producto.registrar(null, "Leche");
				
		// verificación
					
	}
	
	@Test(expected=ValidacionException.class)
	public void deberia_lanzar_excepcion_validacion_si_registra_un_producto_con_codigo_vacio() throws ValidacionException{
		// preparación
		
		// ejecución
		Producto test = null;
		test = Producto.registrar("", "Leche");
				
		// verificación
					
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
		// preparación
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		_producto.setNombre("Leche");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecución
		boolean esValido;
		try {
			esValido = _producto.valida();
		} catch (ValidacionException e) {
			esValido = false;
		}
		
		// verificación
		assertThat(esValido, is(Boolean.TRUE));
	}
	
	@Test
	public void valida_producto_sin_nombre(){
		// preparación
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecución
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("El nombre del producto es obligatorio.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificación
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificación
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void valida_producto_sin_codigo(){
		// preparación
		_producto.setNombre("Leche");
		_producto.setMarca("Parmalat");
		_producto.setCategoria(Categoria.DESCATEGORIZADO);
		
		// ejecución
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("El código del producto es obligatorio.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificación
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificación
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void valida_producto_sin_categoria(){
		// preparación
		_producto.setNombre("Leche");
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		
		// ejecución
		boolean esValido;
		try {
			esValido = _producto.valida();
			fail("La categoría del producto es obligatoria.");
		} catch (ValidacionException e) {
			esValido = false;
			// verificación
			List<ErrorValidacion> listaExcepciones = e.getListaExcepciones();
			assertThat(1, is(listaExcepciones.size()));
		}
		
		// verificación
		assertThat(esValido, is(Boolean.FALSE));
	}
	
	@Test
	public void comprueba_producto_no_es_vacio(){
		// preparación
		_producto.setCodigo("cod1");
		_producto.setMarca("Parmalat");
		
		// ejecución
		boolean resultado = _producto.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.FALSE));
	}
	
	@Test
	public void comprueba_producto_es_vacio(){
		// preparación
		
		// ejecución
		boolean resultado = _producto.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.TRUE));
	}
}
