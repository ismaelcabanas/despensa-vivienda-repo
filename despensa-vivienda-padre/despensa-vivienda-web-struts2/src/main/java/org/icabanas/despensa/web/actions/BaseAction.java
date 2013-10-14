package org.icabanas.despensa.web.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.icabanas.despensa.util.IParametros;
import org.icabanas.despensa.web.helper.PaginacionFactory;
import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
		
	private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

	/**
	 * M�todo que trata los errores de validaci�n de campos.
	 * 
	 * @param excepcion
	 */
	protected void tratarErroresValidacion(final ValidacionException excepcion) {
		if(excepcion != null){
			tratarErroresValidacion(excepcion.getListaExcepciones());
		}
	}
	
	/**
	 * M�todo que trata una lista de errores.
	 * 
	 * @param listaErrores
	 */
	protected void tratarErroresValidacion(final List<ErrorValidacion> listaErrores) {
		if(listaErrores != null){
			Iterator<ErrorValidacion> iterador = listaErrores.iterator();
			while(iterador.hasNext()){
				ErrorValidacion errorValidacion = iterador.next();
				addFieldError(errorValidacion.getCampo(), getText(errorValidacion.getClaveMensaje()));
			}		
		}
	}
	
	/**
	 * M�todo que establece en la request un atributo y su valor asociado.
	 * 
	 * @param atributo
	 * @param valor
	 */
	protected void setAtributoEnRequest(String atributo, Object valor){
		ServletActionContext.getRequest().setAttribute(atributo, valor);
	}
	
	protected void estableceMensajes(String... mensajes) {
		Collection<String> _mensajes = new ArrayList<String>();
		for (String mensaje : mensajes) {
			_mensajes.add(mensaje);
		}
		
		setActionMessages(_mensajes);
	}
	
	/**
	 * Establece mensajes para el framework de Struts2
	 * @param mensajesUsuario
	 */
	protected void estableceMensajes(List<String> mensajesUsuario) {
		Collection<String> _mensajes = new ArrayList<String>();
		for (String mensaje : mensajesUsuario) {
			_mensajes.add(getText(mensaje));
		}
		
		setActionMessages(_mensajes);
	}
	
	/**
	 * M�todo que devuelve el n�mero m�ximo de registros por p�gina.
	 * 
	 * @return
	 */
	protected int getNumeroMaximoRegistrosPorPagina() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer numeroRegistrosPorPagina = null;
		
		try{			
			numeroRegistrosPorPagina = (Integer) request.getSession().getAttribute(IParametros.NUMERO_REGISTROS_POR_PAGINA);
		}
		catch(Exception ex){
			logger.warn("La variable que indica el n�mero de registros por p�gina en un listado no est� definida en la sesi�n.");
		}
		
		if(numeroRegistrosPorPagina == null){
			 numeroRegistrosPorPagina = PaginacionFactory.NUMERO_REGISTROS_POR_PAGINA_DEFAULT; 
		}
		return numeroRegistrosPorPagina.intValue();
	}
}
