package org.icabanas.despensa.dao.catalogos.categoria.impl.jpa;

import javax.persistence.Query;

import org.icabanas.despensa.dao.catalogos.categoria.ICatalogoCategoriaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.impl.GenericDao;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;

public class CatalogoCategoriaDao extends GenericDao<Long, Categoria> implements
		ICatalogoCategoriaDao {

	@Override
	public boolean tieneProductosAsociados(Long id) {
		GestorPersistenciaJPA gestorPersistenciaJPA = (GestorPersistenciaJPA) getGestorPersistencia();
		
		Query query = gestorPersistenciaJPA.getEntityManager().createNamedQuery("categoriaTieneProductosAsociados");
		query.setParameter("id", id);
		int result = ((Long) query.getSingleResult()).intValue();
		return result > 0;
	}

	@Override
	public boolean esCategoriaDuplicada(Categoria categoria) {
		GestorPersistenciaJPA gestorPersistenciaJPA = (GestorPersistenciaJPA) getGestorPersistencia();
		
		Query query = gestorPersistenciaJPA.getEntityManager().createNamedQuery("esCategoriaDuplicada");
		query.setParameter("nombre", categoria.getNombre().toUpperCase());
		int result = ((Long) query.getSingleResult()).intValue();
		return result > 0;
	}

}
