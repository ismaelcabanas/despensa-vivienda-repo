package org.icabanas.despensa.web.actions.catalogos.categorias;

import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.web.actions.catalogos.AbstractCatalogoAction;
import org.icabanas.jee.api.integracion.manager.IGenericManager;

public class CatalogoCategoriasAction extends AbstractCatalogoAction<Long, CategoriaDto, CategoriaFiltro> {

	public CatalogoCategoriasAction(
			IGenericManager<Long, CategoriaDto> catalogoManager) {
		super(catalogoManager);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	private CategoriaDto categoria;

	
	public CategoriaDto getCategoria() {
		return getDto();
	}

	public void setCategoria(CategoriaDto categoria) {
		setDto(categoria);
	}
	
	
}
