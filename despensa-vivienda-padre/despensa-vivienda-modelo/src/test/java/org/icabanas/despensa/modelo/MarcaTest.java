package org.icabanas.despensa.modelo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Producto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MarcaTest {

	private Marca _marca;
	
	@Before
	public void preparaTest(){
		_marca = new Marca();
	}	
	
	@Test
	public void si_nombre_marca_no_es_nulo_ni_cadena_vacia_marca_no_es_vacio(){
		// preparación
		_marca.setNombre("Pascual");
		
		// ejecución
		boolean resultado = _marca.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.FALSE));
	}
	
	@Test
	public void si_nombre_marca_es_null_marca_es_vacio(){
		// preparación
		_marca.setNombre(null);
		
		// ejecución
		boolean resultado = _marca.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void si_nombre_marca_es_cadena_vacia_marca_es_vacio(){
		// preparación
		_marca.setNombre("");
		
		// ejecución
		boolean resultado = _marca.esVacia();
		
		// verificación
		assertThat(resultado, is(Boolean.TRUE));
	}
	
	@Test
	public void comprueba_to_string(){
		// preparación
		_marca.setNombre("Pascual");
		
		// ejecución
		String resultado = _marca.toString();
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo("Pascual"));
	}
	
	@Test
	public void comprueba_equals_true(){
		// preparación
		_marca.setNombre("Pascual");
		Marca otraMarcaIgual = new Marca("Pascual");
		
		// ejecución
		boolean resultado = _marca.equals(otraMarcaIgual);
		
		// verificación
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void comprueba_equals_false(){
		// preparación
		_marca.setNombre("Pascual");
		Marca otraMarcaIgual = new Marca("Pascuala");
		
		// ejecución
		boolean resultado = _marca.equals(otraMarcaIgual);
		
		// verificación
		Assert.assertFalse(resultado);
	}
	
	@Test
	public void deberia_recuperar_nombre_marca(){
		// preparación
		_marca.setNombre("Pascual");
		
		// ejecución
		String resultado = _marca.getNombre();
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo("Pascual"));
	}
	
	@Test
	public void deberia_modificar_nombre_marca(){
		// preparación
		_marca.setNombre("Pascual");
		
		// ejecución
		String resultado = _marca.getNombre();
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo("Pascual"));		
	}
}
