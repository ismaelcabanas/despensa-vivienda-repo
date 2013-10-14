package org.icabanas.despensa.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.icabanas.despensa.util.Constantes;

/**
 * Clase que configura la aplicación.
 * 
 * El objetivo de la clase es almacenar como variables de contexto ciertos parámetros de configuración 
 * de la aplicación, como pueden ser el número máximo de registros que se mostrarán en los listados, etc...
 * 
 * @author f009994r
 *
 */
public class ConfigurarAplicacionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		// TODO Recuperar el valor de número de registros por página de un fichero de configuración
//		context.getServletContext().setAttribute(Constantes.SERVLET_CONTEXT_ATTR_NUM_MAX_REG_PP, 2);
	}

}
