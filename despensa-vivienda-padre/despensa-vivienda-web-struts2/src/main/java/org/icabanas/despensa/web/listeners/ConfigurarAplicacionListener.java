package org.icabanas.despensa.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.icabanas.despensa.util.Constantes;

/**
 * Clase que configura la aplicaci�n.
 * 
 * El objetivo de la clase es almacenar como variables de contexto ciertos par�metros de configuraci�n 
 * de la aplicaci�n, como pueden ser el n�mero m�ximo de registros que se mostrar�n en los listados, etc...
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
		// TODO Recuperar el valor de n�mero de registros por p�gina de un fichero de configuraci�n
//		context.getServletContext().setAttribute(Constantes.SERVLET_CONTEXT_ATTR_NUM_MAX_REG_PP, 2);
	}

}
