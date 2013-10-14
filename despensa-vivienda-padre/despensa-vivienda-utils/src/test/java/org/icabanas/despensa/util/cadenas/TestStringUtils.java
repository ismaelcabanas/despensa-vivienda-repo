package org.icabanas.despensa.util.cadenas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStringUtils {

	@Test
	public void el_string_debe_ser_vacio_si_es_nulo(){
		// preparación
		String cadena = null;
		
		// ejecución
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificación
		assertTrue(esVacia);
	}
	
	@Test
	public void el_string_debe_ser_vacio_si_es_blanco(){
		// preparación
		String cadena = "";
		
		// ejecución
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificación
		assertTrue(esVacia);
	}
	
	@Test
	public void el_string_no_debe_ser_vacio_si_no_es_vacio_o_nulo(){
		// preparación
		String cadena = "test";
		
		// ejecución
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificación
		assertFalse(esVacia);
	}
}
