package org.icabanas.despensa.util.cadenas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStringUtils {

	@Test
	public void el_string_debe_ser_vacio_si_es_nulo(){
		// preparaci�n
		String cadena = null;
		
		// ejecuci�n
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificaci�n
		assertTrue(esVacia);
	}
	
	@Test
	public void el_string_debe_ser_vacio_si_es_blanco(){
		// preparaci�n
		String cadena = "";
		
		// ejecuci�n
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificaci�n
		assertTrue(esVacia);
	}
	
	@Test
	public void el_string_no_debe_ser_vacio_si_no_es_vacio_o_nulo(){
		// preparaci�n
		String cadena = "test";
		
		// ejecuci�n
		boolean esVacia = StringUtils.esCadenaVacia(cadena);
		
		// verificaci�n
		assertFalse(esVacia);
	}
}
