package org.icabanas.despensa.web.actions.catalogos;

import java.io.Serializable;

import org.icabanas.despensa.web.actions.BaseAction;
import org.icabanas.jee.api.integracion.dto.BaseDto;

public abstract class CatalogoBaseAction<ID extends Serializable, T extends BaseDto<ID>> extends BaseAction implements ICatalogoBaseAction{

	private static final long serialVersionUID = 1L;
	
	protected ICatalogoAction<ID, T> accion;

	public ICatalogoAction<ID, T> getAccion() {
		return accion;
	}

	public void setAccion(ICatalogoAction<ID, T> accion) {
		this.accion = accion;
	}

	
}
