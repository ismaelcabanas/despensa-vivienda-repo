package org.icabanas.despensa.web.util;

import java.io.Serializable;

/**
 * 
 * <br/><br/>
 * <b>Responsabilidad</b>: Mantener las preferencias de la sesi�n del usuario.   
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class PreferenciasUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Indica el n�mero de registros por p�gina de un listado paginado 
	 */
	private int numeroRegistrosPorPagina;

	public int getNumeroRegistrosPorPagina() {
		return numeroRegistrosPorPagina;
	}

	public void setNumeroRegistrosPorPagina(int numeroRegistrosPorPagina) {
		this.numeroRegistrosPorPagina = numeroRegistrosPorPagina;
	}
	
	
}
