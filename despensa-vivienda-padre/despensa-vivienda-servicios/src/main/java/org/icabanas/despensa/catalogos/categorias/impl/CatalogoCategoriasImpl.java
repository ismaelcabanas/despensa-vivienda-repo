package org.icabanas.despensa.catalogos.categorias.impl;

import org.icabanas.despensa.adaptadores.catalogos.categorias.CategoriaAdapter;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.ICatalogoCategorias;
import org.icabanas.despensa.dao.catalogos.categoria.ICatalogoCategoriaDao;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.icabanas.jee.api.integracion.manager.impl.AbstractGenericManager;

public class CatalogoCategoriasImpl extends
		AbstractGenericManager<Long, CategoriaDto, Categoria> implements ICatalogoCategorias {

	
	public CatalogoCategoriasImpl() {}

	public CatalogoCategoriasImpl(IGenericDao<Long, Categoria> dao) {
		setDao(dao);
	}

	@Override
	protected CategoriaDto adaptarToDto(Categoria entidad) {
		return CategoriaAdapter.toDto(entidad);
	}

	@Override
	protected Categoria adaptarToEntidad(CategoriaDto dto) {
		return CategoriaAdapter.toEntidad(dto);
	}

	@Override
	protected Categoria crearEntidad(CategoriaDto dto)
			throws ValidacionException {
		Categoria categoria = null;
		
		if(dto != null){
			categoria = Categoria.registrar(dto.getNombre(), dto.getDescripcion());
		}
		
		// compruebo si existe en el repositorio una marca con el mismo nombre
		if(esCategoriaDuplicada(categoria)){
			ValidacionException excepcion = new ValidacionException();
			excepcion.anade("error.categoria.existe");
			throw excepcion;
		}				
		
		return categoria;
	}	

	@Override
	protected void actualizarEntidad(Categoria categoria, CategoriaDto dto)
			throws ValidacionException {
		if(dto != null){
			categoria.actualizar(dto.getNombre());
		}
		
	}

	/**
	 * Comprueba si existe una categoría con el mismo nombre en el repositorio de almacenamiento.
	 * 
	 * @param categoria
	 * 		El dto a validar.
	 * @return
	 * 		True si existe una categoría en el repositorio de almacenamiento con el mismo nombre que la categoría <code>dto</code>.
	 * 		False, en caso contrario.
	 */
	private boolean esCategoriaDuplicada(Categoria categoria) {
		ICatalogoCategoriaDao _dao = (ICatalogoCategoriaDao) getDao();
		
		return _dao.esCategoriaDuplicada(categoria);
	}

}
