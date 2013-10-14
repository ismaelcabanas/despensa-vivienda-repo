package org.icabanas.despensa.modelo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {

	private Categoria _categoria;
	
	@Before
	public void preparaTest(){
		_categoria = new Categoria();
	}	
	
	@Test
	public void si_nombre_categoria_no_es_nulo_ni_cadena_vacia_categoria_no_es_vacio(){
		// preparaci�n
		_categoria.setNombre("Categor�a Test");
		
		// ejecuci�n
		boolean resultado = _categoria.esVacia();
		
		// verificaci�n
		assertThat(resultado, is(Boolean.FALSE));
	}
	
	@Test
	public void si_nombre_categoria_es_null_categoria_es_vacio(){
		// preparaci�n
		_categoria.setNombre(null);
		
		// ejecuci�n
		boolean resultado = _categoria.esVacia();
		
		// verificaci�n
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void si_nombre_categoria_es_cadena_vacia_categoria_es_vacio(){
		// preparaci�n
		_categoria.setNombre("");
		
		// ejecuci�n
		boolean resultado = _categoria.esVacia();
		
		// verificaci�n
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void comprueba_to_string(){
		// preparaci�n
		_categoria.setNombre("Categor�a Test");
		
		// ejecuci�n
		String resultado = _categoria.toString();
		
		// verificaci�n
		Assert.assertThat(resultado, IsEqual.equalTo("Categor�a Test"));
	}
	
	@Test
	public void comprueba_equals_true(){
		// preparaci�n
		_categoria.setNombre("Categor�a Test");
		Categoria otraCategoriaIgual = new Categoria("Categor�a Test");
		
		// ejecuci�n
		boolean resultado = _categoria.equals(otraCategoriaIgual);
		
		// verificaci�n
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void comprueba_equals_false(){
		// preparaci�n
		_categoria.setNombre("Categor�a Test");
		Categoria unaCategoriaDistinta = new Categoria("Categor�a Test2");
		
		// ejecuci�n
		boolean resultado = _categoria.equals(unaCategoriaDistinta);
		
		// verificaci�n
		Assert.assertFalse(resultado);
	}

}
