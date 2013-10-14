package org.icabanas.despensa.zk;

import org.icabanas.jee.api.integracion.dto.mensajes.Mensaje;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;

/**
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
public class ContenidoVM {

	private Mensaje mensajeUsuario;

	public Mensaje getMensajeUsuario() {
		return mensajeUsuario;
	}
	
	@GlobalCommand
	@NotifyChange("mensajeUsuario")
	public void establecerMensaje(@BindingParam("mensaje") Mensaje mensaje){
		this.mensajeUsuario = mensaje;
		this.mensajeUsuario.setI18NMensaje(Labels.getLabel(this.mensajeUsuario.getI18NKeyMensaje()));
		
	}
}
