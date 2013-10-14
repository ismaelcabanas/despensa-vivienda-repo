package org.icabanas.despensa.web.actions.catalogos;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.icabanas.api.creacion.factory.FactoryGenerics;
import org.icabanas.despensa.web.helper.PaginacionFactory;
import org.icabanas.despensa.web.helper.notificacion.INotificacion;
import org.icabanas.despensa.web.util.PreferenciasUsuario;
import org.icabanas.jee.api.integracion.dao.FiltroBean;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dao.util.DAOUtil;
import org.icabanas.jee.api.integracion.dto.BaseDto;
import org.icabanas.jee.api.integracion.dto.mensajes.Mensaje;
import org.icabanas.jee.api.integracion.dto.mensajes.TipoMensaje;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCatalogoAction<ID extends Serializable, T extends BaseDto<ID>, K extends FiltroBean> implements ICatalogoAction<ID,T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCatalogoAction.class);
		
	private INotificacion notificacion;
	
	protected T dto;
	
	private Class<T> dtoClass;		
	
	private IGenericManager<ID, T> catalogoManager;
	
	private Pagina<T> pagina;
	
	private IFiltro filtro;
	
	private Class<K> filtroClass;
	
	private PreferenciasUsuario preferenciasUsuario;
	
	
	public AbstractCatalogoAction(IGenericManager<ID, T> catalogoManager) {
		super();
		this.dtoClass = (Class<T>) DAOUtil.getTypeArguments(AbstractCatalogoAction.class, this.getClass()).get(1);
		this.filtroClass = (Class<K>) DAOUtil.getTypeArguments(AbstractCatalogoAction.class, this.getClass()).get(2);
		this.catalogoManager = catalogoManager;
	}

	/**
	 * Inicializa el filtro de búsqueda.
	 * 
	 * @return
	 */
	protected void inicializaFiltroDeBusqueda(){}
	
	@Override
	public void busqueda() {
		logger.debug("Acción: búsqueda()");
		
//		if(filtro == null){			
//			filtro = inicializaFiltroDeBusqueda();
//		}
		
		// inicializo el dto a null por si el usuario hubiera seleccionado una entidad anteriormente
		dto = null;
		
		if(filtro != null && !filtro.esVacio()){
			paginar(1);
		}
		else{
			filtro = FactoryGenerics.getInstance(filtroClass).getInstance();
			inicializaFiltroDeBusqueda();
		}
	}	

	@Override
	public void alta() {
		logger.debug("Acción: alta()");
		dto = FactoryGenerics.getInstance(dtoClass).getInstance();
	}

	@Override
	public void cancelar() {
		logger.debug("Acción: cancelar()");
		
		// notifico al usuario de la cancelación de la operación
		notificacion.notifica(new Mensaje(TipoMensaje.INFO,"mensaje.cancelacion"));
		
//		// esblezco mensaje de usuario
//		estableceMensajeUsuario(new Mensaje(TipoMensaje.INFO,"mensaje.cancelacion"));
		
//		// si el usuario realizó una búsqueda anteriormente, la vuelvo a ejecutar llamando a la acción de paginar
//		if(filtro != null){
//			paginar(1);
//		}
	}

	@Override
	public void eliminar() throws NoExisteEntidadException, EliminarException {
		if(dto != null){
			eliminar(dto.getId());
		}
		else{
			throw new NoExisteEntidadException("Identificador de entidad nulo.");
		}							
	}
	
	@Override
	public void eliminar(ID id) throws NoExisteEntidadException, EliminarException {
		logger.debug("Acción: eliminar()");
		
		try {
			if(id != null){
				logger.debug("Identificador del DTO a eliminar es " + id);
				catalogoManager.eliminar(id);
				// establezco el mensaje informativo indicando que se ha eliminado el producto
				notificacion.notifica(new Mensaje(TipoMensaje.EXITO,"dto.eliminar.correcto"));
			}
			else{
				throw new NoExisteEntidadException("Identificador de entidad nulo.");
			}			
		} catch (NoExisteEntidadException e) {
			// establezco el mensaje informativo indicando que debe seleccionar un registro
			notificacion.notifica(new Mensaje(TipoMensaje.ADVERTENCIA,"dto.eliminar.noexiste"));
			throw e;
		} catch (EliminarException e) {
			throw e;
		}
	}

	@Override
	public void guardar() throws RegistrarException {
		try{
			getCatalogoManager().registrar(dto);
			
			setDto(null);
		}
		catch (RegistrarException e) {			
			throw e;
		}		
		
		// establezco el mensaje informativo
		notificacion.notifica(new Mensaje(TipoMensaje.EXITO,"dto.alta.correcto"));
	}

	@Override
	public void actualizar() throws ModificarException,
			NoExisteEntidadException {
		try {
			getCatalogoManager().actualizar(dto);
		} catch (ModificarException e) {
			throw e;
		} catch (NoExisteEntidadException e) {
			// establezco el mensaje informativo indicando que debe seleccionar un registro
			notificacion.notifica(new Mensaje(TipoMensaje.ADVERTENCIA,"dto.editar.noexiste"));
			throw e;
		}
		finally{
			dto = null;
		}
				
		// establezco mensaje de información al usuario
		notificacion.notifica(new Mensaje(TipoMensaje.EXITO,"dto.actualizacion.correcto"));
		
	}

	@Override
	public void editar(ID id) throws NoExisteEntidadException {
		try {
			if(id == null){
				throw new NoExisteEntidadException("Identificador de entidad nulo.");
			}
			logger.debug("Identificador del dto seleccionado es " + id);
			setDto(getCatalogoManager().buscarPorId(id));
		} catch (NoExisteEntidadException e) {
			// establezco el mensaje informativo indicando que debe seleccionar un registro
			notificacion.notifica(new Mensaje(TipoMensaje.ADVERTENCIA,"dto.editar.noexiste"));
			// establezco a null el producto
			setDto(null);
			
			throw e;
		}
	}
	
	@Override
	public void editar() throws NoExisteEntidadException {
		editar(getDto().getId());
	}

	@Override
	public void validar() throws ValidacionException {
		getCatalogoManager().validar(dto);
	}

	@Override
	public void paginar(HttpServletRequest request) {
		pagina = (new PaginacionFactory<T>()).getPagina(request);
		_paginar();
	}	
	
	@Override
	public void paginar(int paginaActual) {
		// inicializo el dto a null por si el usuario hubiera seleccionado una entidad anteriormente
		dto = null;
		pagina = (new PaginacionFactory<T>()).getPagina(preferenciasUsuario,paginaActual);
		_paginar();
	}

	@Override
	public void limpiarFiltro() {
		getCatalogoManager().limpiarFiltro(getFiltro());
	}	

	@Override
	public Pagina<T> getPagina() {
		return pagina;
	}


	public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}
	

	protected IGenericManager<ID, T> getCatalogoManager() {
		return catalogoManager;
	}

	protected void setCatalogoManager(IGenericManager<ID, T> catalogoManager) {
		this.catalogoManager = catalogoManager;
	}

	public void setFiltro(IFiltro filtro) {
		this.filtro = filtro;
	}

	public IFiltro getFiltro() {
		return filtro;
	}		
	
	public PreferenciasUsuario getPreferenciasUsuario() {
		return preferenciasUsuario;
	}

	public void setPreferenciasUsuario(PreferenciasUsuario preferenciasUsuario) {
		this.preferenciasUsuario = preferenciasUsuario;
	}		

	public INotificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(INotificacion notificacion) {
		this.notificacion = notificacion;
	}

	/**
	 *  
	 */
	private void _paginar() {
		pagina.setFiltro(filtro);
		getCatalogoManager().paginar(pagina);
		
		if(pagina.getDatos() == null || pagina.getDatos().isEmpty()){
			// establezco el mensaje informativo
			notificacion.notifica(new Mensaje(TipoMensaje.INFO,"mensaje.busqueda.sin.registros"));
		}
	}
}
