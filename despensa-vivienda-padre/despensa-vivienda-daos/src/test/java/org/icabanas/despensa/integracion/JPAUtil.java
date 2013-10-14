package org.icabanas.despensa.integracion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManager em;	

	public static EntityManagerFactory crearEntityManagerFactoryParaHibernate() {
		return Persistence.createEntityManagerFactory("HibernateJPATest");
	}
		
	public void iniciarConexion(EntityManager em) {
		this.em = em;
		this.em.getTransaction().begin();		
	}

	public void cerrarConexion() {
		this.em.getTransaction().commit();
		this.em.close();
	}

	public EntityManager getEntityManager() {
		return this.em;
	}	
	
}
