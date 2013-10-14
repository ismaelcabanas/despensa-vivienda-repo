package org.icabanas.despensa.catalogos.categorias.impl;

import org.icabanas.despensa.adaptadores.catalogos.categorias.CategoriaAdapter;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categorias.ICatalogoCategorias;
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
			categoria = Categoria.registrar(dto.getNombre());
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



}
