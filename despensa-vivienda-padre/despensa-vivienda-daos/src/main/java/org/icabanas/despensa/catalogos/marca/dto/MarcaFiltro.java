package org.icabanas.despensa.catalogos.marca.dto;

import org.apache.commons.lang.StringUtils;
import org.icabanas.jee.api.integracion.dao.FiltroBean;

/**
 * Clase que define el criterio de búsqueda para la entidad Marca.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> :  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class MarcaFiltro extends FiltroBean {

	private static final long serialVersionUID = 1L;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	@Override
	public void limpiar() {
		this.nombre = null;
	}

	/* 
	 * Comprueba si el filtro de búsqueda es vacío. 
	 * 
	 * El filtro de búsqueda de las marcas es vacío si:
	 * <ul>
	 * 		<li>El nombre de la marca es nulo o vacío.</li>
	 * </ul>
	 */
	@Override
	public boolean esVacio() {
		return StringUtils.isEmpty(nombre);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcaFiltro other = (MarcaFiltro) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
