package org.icabanas.despensa.web.actions.catalogos.marcas;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
import org.icabanas.despensa.web.actions.catalogos.AbstractCatalogoAction;
import org.icabanas.jee.api.integracion.manager.IGenericManager;

public class CatalogoMarcasAction extends AbstractCatalogoAction<Long, MarcaDto, MarcaFiltro> {
	
	public CatalogoMarcasAction(IGenericManager<Long, MarcaDto> catalogoManager) {
		super(catalogoManager);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	private MarcaDto marca;
			
	public MarcaDto getMarca() {
		return getDto();
	}
	
	public void setMarca(MarcaDto marca) {		
		setDto(marca);
	}	
	
}
