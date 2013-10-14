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
	 * Comprueba si el filtro de búsqueda es vacío. 
	 * 
	 * El filtro de búsqueda de las categorías es vacío si:
	 * <ul>
	 * 		<li>El nombre de la categoría es nulo o vacío.</li>
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
