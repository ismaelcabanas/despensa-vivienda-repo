package org.icabanas.despensa.dao.catalogos.marca.impl.jpa;

import javax.persistence.Query;

import org.icabanas.despensa.dao.catalogos.marca.ICatalogoMarcaDao;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.jee.api.integracion.dao.impl.AbstractGenericDao;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;


public class CatalogoMarcaDao extends AbstractGenericDao<Long, Marca> implements ICatalogoMarcaDao {

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.dao.catalogos.marca.impl.jpa.IMarcaDao#tieneProductosAsociados(java.lang.Long)
	 */
	@Override
	public boolean tieneProductosAsociados(Long id) {
		GestorPersistenciaJPA gestorPersistenciaJPA = (GestorPersistenciaJPA) getGestorPersistencia();
		
		Query query = gestorPersistenciaJPA.getEntityManager().createNamedQuery("tieneProductosAsociados");
		query.setParameter("id", id);
		int result = ((Long) query.getSingleResult()).intValue();
		return result > 0;
	}

}
