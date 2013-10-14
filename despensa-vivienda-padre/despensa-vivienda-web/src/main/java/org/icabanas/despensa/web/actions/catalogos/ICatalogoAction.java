package org.icabanas.despensa.web.actions.catalogos;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dto.BaseDto;
import org.icabanas.jee.api.integracion.dto.mensajes.Mensaje;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

public interface ICatalogoAction<ID extends Serializable,T extends BaseDto<ID>> {

	/**
	 * Acci�n que se ejecuta al iniciar la acci�n de b�squeda. Si el usuario realiz� una b�squeda anterior 
	 * se realiza la b�squeda.
	 */
	public abstract void busqueda();
	
	/**
	 * Acci�n que se ejecuta al dar de alta una entidad
	 */
	public abstract void alta();

	/**
	 * Acci�n que se ejecuta al cancelar una operaci�n (alta, edici�n, ...)
	 */
	public abstract void cancelar();

	/**
	 * Acci�n que se ejecuta al eliminar una entidad.
	 * 
	 * @throws NoExisteEntidadException
	 * 		Si la entidad a eliminar no existe.
	 */
	public abstract void eliminar() throws NoExisteEntidadException, EliminarException;
	
	/**
	 * Acci�n que se ejecuta al eliminar una entidad.
	 * 
	 * @param id
	 * 		Identificador de la entidad a eliminar.
	 * 
	 * @throws NoExisteEntidadException
	 * 		Si la entidad a eliminar no existe.
	 */
	public abstract void eliminar(ID id) throws NoExisteEntidadException, EliminarException;

	/**
	 * Acci�n que se ejecuta al guardar una entidad.
	 * 
	 * @throws RegistrarException
	 * 		Si se produce un error de validaci�n al registrar una entidad.
	 */
	public abstract void guardar() throws RegistrarException;

	/**
	 * Acci�n que se ejecuta al actualizar una entidad.
	 * 
	 * @throws ModificarException
	 * 		Si se produce una error de validaci�n al actualizar una entidad.
	 * @throws NoExisteEntidadException
	 * 		Si la entidad a actualizar no existe.
	 */
	public abstract void actualizar() throws ModificarException, NoExisteEntidadException;

	/**
	 * Acci�n que se ejecuta al editar una entidad seleccionada previamente.
	 *
	 * @throws NoExisteEntidadException
	 * 		Si el producto a editar no existe.
	 */
	public abstract void editar() throws NoExisteEntidadException;
	
	/**
	 * Acci�n que se ejecuta al editar una entidad.
	 *
	 * @param id
	 * 		Identificador de la entidad.
	 * @throws NoExisteEntidadException
	 * 		Si el producto a editar no existe.
	 */
	public abstract void editar(ID id) throws NoExisteEntidadException;

	/**
	 * Acci�n que se ejecuta al validar una entidad.
	 * 
	 * @throws ValidacionException
	 */
	public abstract void validar() throws ValidacionException;

	/**
	 * Acci�n que se ejecuta al realizar una b�squeda paginada sobre una entidad.
	 * 
	 * La informaci�n de la p�gina que se tiene que mostrar se recupera de la HTTPRequest.
	 * 	
	 * @param request
	 * 		Objeto {@link HttpServletRequest}.
	 */
	public abstract void paginar(HttpServletRequest request);
	
	/**
	 * Acci�n que se ejecuta al realizar una b�squeda paginada sobre una entidad indicando la p�gina que 
	 * se tiene que mostrar.
	 * 
	 * @param paginaActual
	 * 		La p�gina del resultado de la b�squeda que el usuario quiere visualizar.
	 */
	public abstract void paginar(int paginaActual);
	
//	/**
//	 * Devuelve los mensajes de usuario establecidos por los action.
//	 * 
//	 * @return
//	 */
//	public abstract List<Mensaje> getMensajesUsuario();
	
	/**
	 * Devuelve la paginaci�n.
	 * 
	 * @return
	 */
	public abstract Pagina<T> getPagina();
	
	/**
	 * Obtiene el Dto del action.
	 * 
	 * @return
	 */
	public abstract T getDto();

//	/**
//	 * @param manager
//	 */
//	public abstract void setCatalogoManager(IGenericManager<ID, T> manager);

	/**
	 * Establece el Dto del action
	 * 
	 * @param dto
	 */
	public abstract void setDto(T dto);

	/**
	 * Filtro que se aplica al action.
	 * 
	 * @param filtro
	 */
	public abstract void setFiltro(IFiltro filtro);

	/**
	 * Recupera el filtro de b�squeda.
	 * 
	 * @return
	 */
	public abstract IFiltro getFiltro();

	/**
	 * M�todo que limpia el filtro de b�squeda
	 */
	public abstract void limpiarFiltro();	
	
}