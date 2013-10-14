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
		// preparación
		_categoria.setNombre("Categoría Test");
		
		// ejecución
		boolean resultado = _categoria.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.FALSE));
	}
	
	@Test
	public void si_nombre_categoria_es_null_categoria_es_vacio(){
		// preparación
		_categoria.setNombre(null);
		
		// ejecución
		boolean resultado = _categoria.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void si_nombre_categoria_es_cadena_vacia_categoria_es_vacio(){
		// preparación
		_categoria.setNombre("");
		
		// ejecución
		boolean resultado = _categoria.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void comprueba_to_string(){
		// preparación
		_categoria.setNombre("Categoría Test");
		
		// ejecución
		String resultado = _categoria.toString();
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo("Categoría Test"));
	}
	
	@Test
	public void comprueba_equals_true(){
		// preparación
		_categoria.setNombre("Categoría Test");
		Categoria otraCategoriaIgual = new Categoria("Categoría Test");
		
		// ejecución
		boolean resultado = _categoria.equals(otraCategoriaIgual);
		
		// verificación
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void comprueba_equals_false(){
		// preparación
		_categoria.setNombre("Categoría Test");
		Categoria unaCategoriaDistinta = new Categoria("Categoría Test2");
		
		// ejecución
		boolean resultado = _categoria.equals(unaCategoriaDistinta);
		
		// verificación
		Assert.assertFalse(resultado);
	}

}
