package org.icabanas.despensa.modelo;

import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

public interface Validable {

	abstract boolean valida() throws ValidacionException;
	
}
