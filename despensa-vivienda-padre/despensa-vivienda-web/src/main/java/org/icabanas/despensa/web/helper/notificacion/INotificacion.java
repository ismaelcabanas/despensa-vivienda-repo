package org.icabanas.despensa.web.helper.notificacion;

import org.icabanas.jee.api.integracion.dto.mensajes.Mensaje;

/**
 * 
 * <br/><br/>
 * <b>Responsabilidad</b>:   
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public interface INotificacion {

	/**
	 * Notifica al usuario un determinado mensaje.
	 * 
	 * @param mensaje
	 * 		El mensaje a notificar.
	 */
	void notifica(final Mensaje mensaje);

}
