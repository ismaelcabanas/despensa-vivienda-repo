package org.icabanas.despensa.util;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Clase que realiza tests de la clase {@link FicheroConfiguracion}.
 * 
 * @author f009994r
 *
 */
public class FicheroConfiguracionTest {

	@Test
	public void recupera_propiedad_prueba_de_fichero_configuracion(){
		// preparaci�n
		String valorPropiedad = "prueba";		
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.prueba";
		
		// ejecuci�n
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad );
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificaci�n
		assertThat(valorPropiedad, equalTo(valor));
	}
	
	@Test
	public void recupera_propiedad_prueba1_de_fichero_configuracion(){
		// preparaci�n
		String valorPropiedad = "prueba1";		
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.prueba1";
		
		// ejecuci�n
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad );
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificaci�n
		assertThat(valorPropiedad, equalTo(valor));
	}
	
	@Test
	public void si_propiedad_no_existe_devuelve_null(){
		// preparaci�n
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.pruebanoexiste";
		
		// ejecuci�n
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificaci�n
		assertThat(valor, nullValue());
	}
	
	@Test
	public void si_fichero_no_existe_devuelve_null(){
		// preparaci�n
		String fichero = "pruebanoexiste.properties";		
		String propiedad = "propiedad.prueba";
		
		// ejecuci�n
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificaci�n
		assertThat(valor, nullValue());
	}
}
