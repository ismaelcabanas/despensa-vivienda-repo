package org.icabanas.despensa.util.cadenas;

/**
 * Clase de utiliadades para cadenas.
 * 
 * @author f009994r
 *
 */
public final class StringUtils {

	/**
	 * M�todo que comprueba si una cadena es nula o vac�a.
	 * 
	 * @param cadena
	 * @return True si la cadena es nula o vac�a, false en caso contrario.
	 */
	public static boolean esCadenaVacia(String cadena) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cadena);
	}

}
