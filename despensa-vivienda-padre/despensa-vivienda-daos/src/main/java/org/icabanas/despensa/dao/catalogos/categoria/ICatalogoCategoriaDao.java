package org.icabanas.despensa.dao.catalogos.categoria;

import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.IGenericDao;

public interface ICatalogoCategoriaDao extends IGenericDao<Long, Categoria>{

	/**
	 * @param id
	 * 		Identificador de la categor�a
	 * @return
	 * 		True si la categor�a tiene productos asociados, false en caso contrario.
	 */
	public abstract boolean tieneProductosAsociados(Long id);

	/**
	 * @param categoria
	 * 		La entidad
	 * @return
	 * 		True si existe una categor�a en el repositorio de almacenamiento con el mismo nombre que la categor�a <code>categoriaDto</code>.
	 * 		False, en caso contrario.
	 */
	public abstract boolean esCategoriaDuplicada(Categoria categoria);
}
