package org.icabanas.despensa.web.interceptors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

/**
 * Clase que actúa de interceptor de las excepciones para realizar logging de las excepciones no controladas por 
 * la aplicación.
 * 
 * @author f009994r
 *
 */
public class LoggingExcepcionesInterceptor extends ExceptionMappingInterceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingExcepcionesInterceptor.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		super.setLogEnabled(true);
		
		return super.intercept(actionInvocation);		
	}
	
	
	
	@Override
	protected void handleLogging(Exception e) {
		logger.error("Excepción no controlada en la aplicación: " + e.getMessage());
		logger.error("Traza de la excepción: " + obtenerTrazaExcepcion(e));
		Throwable causaError = e.getCause();
		if(causaError != null){
			logger.error("Causa de la excepción: " + causaError.getMessage());				
			logger.error("Traza de la causa de la excepción: " + obtenerTrazaExcepcion(causaError));				
		}
	}

	private String obtenerTrazaExcepcion(Throwable ex){		
		String traza = "";
		
		try{
			Writer out = new StringWriter();
			PrintWriter printWriter = new PrintWriter(out);
			ex.printStackTrace(printWriter);
			traza = out.toString();
			out.close();
			printWriter.close();
		}
		catch(Exception e){
			traza = "ERROR AL OBTENER LA TRAZA DE LA EXCEPCIÓN.";
		}
		
		return traza;
	}
		
}
