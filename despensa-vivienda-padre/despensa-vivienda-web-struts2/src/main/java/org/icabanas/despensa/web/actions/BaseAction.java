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
	 * Método que trata los errores de validación de campos.
	 * 
	 * @param excepcion
	 */
	protected void tratarErroresValidacion(final ValidacionException excepcion) {
		if(excepcion != null){
			tratarErroresValidacion(excepcion.getListaExcepciones());
		}
	}
	
	/**
	 * Método que trata una lista de errores.
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
	 * Método que establece en la request un atributo y su valor asociado.
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
	 * Método que devuelve el número máximo de registros por página.
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
			logger.warn("La variable que indica el número de registros por página en un listado no está definida en la sesión.");
		}
		
		if(numeroRegistrosPorPagina == null){
			 numeroRegistrosPorPagina = PaginacionFactory.NUMERO_REGISTROS_POR_PAGINA_DEFAULT; 
		}
		return numeroRegistrosPorPagina.intValue();
	}
}
