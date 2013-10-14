package org.icabanas.despensa.zk.notificacion;

import org.icabanas.despensa.web.helper.notificacion.INotificacion;
import org.icabanas.jee.api.integracion.dto.mensajes.Mensaje;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.util.Clients;

/**
 * <br/><br/>
 * <b>Responsabilidad</b>: Se encarga de mandar notificaciones a los usuarios.  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class Notificacion implements INotificacion {

	@Override
	public void notifica(final Mensaje mensaje) {
		if(mensaje != null){
			Clients.evalJavaScript("notificacion('" + 
					mensaje.getTipoMensaje().toString() + "','" + 
					Labels.getLabel(mensaje.getI18NKeyMensaje()) + "')");	
		}
	}

}
