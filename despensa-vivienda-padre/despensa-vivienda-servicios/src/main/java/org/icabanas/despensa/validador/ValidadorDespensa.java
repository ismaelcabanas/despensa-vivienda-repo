package org.icabanas.despensa.validador;

import org.icabanas.despensa.excepciones.ExcesoConsumoException;
import org.icabanas.despensa.excepciones.ReponerException;

public class ValidadorDespensa {
	
	public static void validaReposicionProducto(int cantidad) throws ReponerException{
		if(cantidad <= 0)
			throw new ReponerException("No se permite reponer una cantidad cero o negativa del producto.");
	}

	public static boolean validaExcesoConsumoProducto(int unidadesProducto,
			int unidadesAConsumir) throws ExcesoConsumoException {
		if(unidadesAConsumir > unidadesProducto)
			throw new ExcesoConsumoException("No se puede consumir más de las existencias del producto que hay en la despensa.");
		
		return true;
	}


}