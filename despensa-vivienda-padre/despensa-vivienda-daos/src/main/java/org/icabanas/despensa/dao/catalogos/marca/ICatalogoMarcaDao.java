package org.icabanas.despensa.dao.catalogos.marca;

import org.icabanas.despensa.modelo.Marca;
import org.icabanas.jee.api.integracion.dao.IGenericDao;

public interface ICatalogoMarcaDao extends IGenericDao<Long, Marca>{

	/**
	 * @param id
	 * 		Identificador de la marca
	 * @return
	 * 		True si la marca tiene productos asociados, false en caso contrario.
	 */
	public abstract boolean tieneProductosAsociados(Long id);

}