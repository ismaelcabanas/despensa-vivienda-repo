package org.icabanas.despensa.catalogos.categoria.dto;

import org.apache.commons.lang.StringUtils;
import org.icabanas.jee.api.integracion.dao.FiltroBean;

public class CategoriaFiltro extends FiltroBean {

	private static final long serialVersionUID = 1L;

	private String nombre;

	@Override
	public void limpiar() {
		this.nombre = null;
	}
	
	/* 
	 * Comprueba si el filtro de b�squeda es vac�o. 
	 * 
	 * El filtro de b�squeda de las categor�as es vac�o si:
	 * <ul>
	 * 		<li>El nombre de la categor�a es nulo o vac�o.</li>
	 * </ul>
	 */
	@Override
	public boolean esVacio() {
		return StringUtils.isEmpty(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
