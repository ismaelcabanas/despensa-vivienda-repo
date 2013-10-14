package org.icabanas.despensa.web.actions.catalogos.productos;

import static org.icabanas.despensa.util.Constantes.ACTION_CORRECTO;
import static org.icabanas.despensa.util.Constantes.ACTION_INPUT;
import static org.icabanas.despensa.util.Constantes.ACTION_LISTADO;

import org.apache.struts2.ServletActionContext;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.web.actions.catalogos.CatalogoBaseAction;
import org.icabanas.despensa.web.adapter.displaytag.PaginatedListAdapter;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;

public class CatalogoProductosStruts2WrapperAction extends CatalogoBaseAction<Long, ProductoDto> {

	private static final long serialVersionUID = 1L;
		
	private PaginatedListAdapter paginacion;
		
	public CatalogoProductosStruts2WrapperAction() {
		super();
	}
	
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#alta()
	 */
	@Override
	public String alta(){
		accion.alta();
		
		return ACTION_CORRECTO; 
	}		

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#cancelar()
	 */
	@Override
	public String cancelar() {
		accion.cancelar();
		
		estableceMensajes(accion.getMensajesUsuario());
		
		return ACTION_CORRECTO;
	}	

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#editar()
	 */
	@Override
	public String editar() {
		try{
			accion.editar(accion.getDto().getId());
		}
		catch(NoExisteEntidadException ex){
			return ACTION_LISTADO;
		}
		
		return ACTION_CORRECTO;
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#eliminar()
	 */
	@Override
	public String eliminar() {
		try {
			accion.eliminar();
		} catch (NoExisteEntidadException e) {
			return ACTION_LISTADO;
		}

		// establezco el mensaje informativo
		estableceMensajes(accion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#guardar()
	 */
	@Override
	public String guardar() {
		try {			
			accion.guardar();
		} catch (RegistrarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
			return ACTION_INPUT;
		}
		
		// establezco el mensaje informativo
		estableceMensajes(accion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#actualizar()
	 */
	@Override
	public String actualizar() {
		try {
			accion.actualizar();
		} catch (ModificarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
			return ACTION_INPUT;
		} catch (NoExisteEntidadException e) {
			return ACTION_LISTADO;
		}
		
		// establezco el mensaje informativo
		estableceMensajes(accion.getMensajesUsuario());
		
		return ACTION_LISTADO;
	}	
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.IBaseAction#paginar()
	 */
	@Override
	public String paginar() {
		accion.paginar(ServletActionContext.getRequest());
		
		paginacion = new PaginatedListAdapter(accion.getPagina());
		
		// establezco el mensaje informativo si lo hubiera
		estableceMensajes(accion.getMensajesUsuario());
				
		return ACTION_LISTADO;
	}
	
	public PaginatedListAdapter getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(PaginatedListAdapter paginacion) {
		this.paginacion = paginacion;
	}

	
}
