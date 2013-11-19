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
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class GestionBajasCatalogoCategoriasStorieTest extends AbstractTestIT{

	public boolean baja(String nombreCategoria) {
		boolean resultado = false;

		if(nombreCategoria.equals("null"))
			nombreCategoria = null;
		
		ICatalogoCategoriaDao dao = new CatalogoCategoriaDao();
		IGestorPersistencia gestorPersistencia = new GestorPersistenciaJPA(getEntityManager());
		dao.setGestorPersistencia(gestorPersistencia);
		ICatalogoCategorias catalogoCategorias = new CatalogoCategoriasImpl(dao);		
		
		CategoriaDto dto = new CategoriaDto();
		dto.setNombre(nombreCategoria);		
		try {
			dto = catalogoCategorias.registrar(dto);
		} catch (RegistrarException e) {
			resultado = false;
		}

		try {
			catalogoCategorias.eliminar(dto.getId());
		} catch (NoExisteEntidadException e) {
			resultado = false;
		} catch (EliminarException e) {
			resultado = false;
		}
		
		CategoriaDto dtoEliminado = null;
		try {
			dtoEliminado = catalogoCategorias.buscarPorId(dto.getId());
		} catch (NoExisteEntidadException e) {
			resultado = false;
		}
		
		resultado = (dtoEliminado == null);
		
		return resultado;
	}

	@Override
	protected void generarDatos() {
		// TODO Auto-generated method stub
		
	}
}
