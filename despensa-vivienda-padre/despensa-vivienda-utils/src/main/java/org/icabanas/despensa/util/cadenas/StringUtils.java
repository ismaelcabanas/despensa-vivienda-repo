package org.icabanas.despensa.util.cadenas;

/**
 * Clase de utiliadades para cadenas.
 * 
 * @author f009994r
 *
 */
public final class StringUtils {

	/**
	 * Método que comprueba si una cadena es nula o vacía.
	 * 
	 * @param cadena
	 * @return True si la cadena es nula o vacía, false en caso contrario.
	 */
	public static boolean esCadenaVacia(String cadena) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cadena);
	}

}
