package org.icabanas.despensa.web.actions.catalogos.marcas;

import static org.icabanas.despensa.util.Constantes.ACTION_CORRECTO;
import static org.icabanas.despensa.util.Constantes.ACTION_INPUT;
import static org.icabanas.despensa.util.Constantes.ACTION_LISTADO;

import org.apache.struts2.ServletActionContext;
import org.icabanas.despensa.web.actions.BaseAction;
import org.icabanas.despensa.web.adapter.displaytag.PaginatedListAdapter;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;

public class CatalogoMarcasStruts2WrapperAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private CatalogoMarcasAction wpAccion;
	
	private PaginatedListAdapter paginacion;

	public String alta() {
		wpAccion.alta();
		
		return ACTION_CORRECTO; 
	}

	public void setWpAccion(CatalogoMarcasAction actionWrapped) {
		this.wpAccion = actionWrapped;
	}

	public CatalogoMarcasAction getWpAccion() {
		return wpAccion;
	}

	public String cancelar() {
		wpAccion.cancelar();
		
		estableceMensajes(wpAccion.getMensajesUsuario());
		
		return ACTION_CORRECTO;
	}

	public String editar() {
		try{
			wpAccion.editar(wpAccion.getDto().getId());
		}
		catch(NoExisteEntidadException ex){
			return ACTION_LISTADO;
		}
		
		return ACTION_CORRECTO;
	}

	public String eliminar() {
		try {
			wpAccion.eliminar();
		} catch (NoExisteEntidadException e) {
			return ACTION_LISTADO;
		}

		// establezco el mensaje informativo
		estableceMensajes(wpAccion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}

	public String guardar() {
		try {			
			wpAccion.guardar();
		} catch (RegistrarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
			return ACTION_INPUT;
		}
		
		// establezco el mensaje informativo
		estableceMensajes(wpAccion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}

	public String actualizar() {
		try {
			wpAccion.actualizar();
		} catch (ModificarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
			return ACTION_INPUT;
		} catch (NoExisteEntidadException e) {
			return ACTION_LISTADO;
		}
		
		// establezco el mensaje informativo
		estableceMensajes(wpAccion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}

	public String paginar() {
		wpAccion.paginar(ServletActionContext.getRequest());
		
		paginacion = new PaginatedListAdapter(wpAccion.getPagina());
		
		// establezco el mensaje informativo si lo hubiera
		estableceMensajes(wpAccion.getMensajesUsuario());
				
		return ACTION_LISTADO;
	}

	
}
