package org.icabanas.despensa.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Clase que mantiene una caché de las propiedades de ficheros de configuración.
 * 
 * @author f009994r
 *
 */
public class FicheroConfiguracion {

	private static Map<String, Properties> cache = new HashMap<String, Properties>();
	
	/**
	 * Método que recupera el valor de una propiedad de un fichero de propiedades del classpathS.
	 * 
	 * @param fichero
	 * @param propiedad
	 * @return
	 * @throws Exception 
	 */
	public static String getPropiedad(String fichero, String propiedad) throws Exception {
		if(!cache.containsKey(fichero)){
			cargarFicheroPropiedades(fichero);
		}
		return obtenerPropiedad(fichero,propiedad);
	}

	private static String obtenerPropiedad(String fichero, String propiedad) {
		String valor = null;
		Properties ficheroPropiedades = cache.get(fichero);
		if(ficheroPropiedades != null){
			valor = ficheroPropiedades.getProperty(propiedad);
		}
		return valor;
	}

	private static void cargarFicheroPropiedades(String fichero) throws Exception {
		Properties properties = null;
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fichero);
		if(is != null){
			properties = new Properties();
			try {
				Reader reader = new InputStreamReader(is, "UTF-8");
				properties.load(reader );
			} catch (IOException e) {
				throw new Exception("No se ha cargado el fichero de propiedades.", e);
			}
			cache.put(fichero, properties);
		}
		
	}

	
}
