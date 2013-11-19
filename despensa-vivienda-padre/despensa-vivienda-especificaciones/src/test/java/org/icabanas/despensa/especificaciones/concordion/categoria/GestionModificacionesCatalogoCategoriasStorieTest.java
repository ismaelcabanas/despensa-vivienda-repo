package org.icabanas.despensa.especificaciones.concordion.categoria;

import org.concordion.integration.junit4.ConcordionRunner;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.ICatalogoCategorias;
import org.icabanas.despensa.catalogos.categorias.impl.CatalogoCategoriasImpl;
import org.icabanas.despensa.dao.catalogos.categoria.ICatalogoCategoriaDao;
import org.icabanas.despensa.dao.catalogos.categoria.impl.jpa.CatalogoCategoriaDao;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.dao.jpa.it.AbstractTestIT;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class GestionModificacionesCatalogoCategoriasStorieTest extends AbstractTestIT{

	public void inicializaTest(String nombreCategoria) throws Exception{
		ICatalogoCategoriaDao dao = new CatalogoCategoriaDao();
		IGestorPersistencia gestorPersistencia = new GestorPersistenciaJPA(getEntityManager());
		dao.setGestorPersistencia(gestorPersistencia);
		ICatalogoCategorias catalogoCategorias = new CatalogoCategoriasImpl(dao);
		
		CategoriaDto dto = new CategoriaDto(nombreCategoria);
		catalogoCategorias.registrar(dto);
	}

	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub
		
	}
}
