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
		// preparación
		String valorPropiedad = "prueba";		
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.prueba";
		
		// ejecución
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad );
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificación
		assertThat(valorPropiedad, equalTo(valor));
	}
	
	@Test
	public void recupera_propiedad_prueba1_de_fichero_configuracion(){
		// preparación
		String valorPropiedad = "prueba1";		
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.prueba1";
		
		// ejecución
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad );
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificación
		assertThat(valorPropiedad, equalTo(valor));
	}
	
	@Test
	public void si_propiedad_no_existe_devuelve_null(){
		// preparación
		String fichero = "prueba.properties";		
		String propiedad = "propiedad.pruebanoexiste";
		
		// ejecución
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificación
		assertThat(valor, nullValue());
	}
	
	@Test
	public void si_fichero_no_existe_devuelve_null(){
		// preparación
		String fichero = "pruebanoexiste.properties";		
		String propiedad = "propiedad.prueba";
		
		// ejecución
		String valor = null;
		try {
			valor = FicheroConfiguracion.getPropiedad(fichero ,propiedad);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// verificación
		assertThat(valor, nullValue());
	}
}
