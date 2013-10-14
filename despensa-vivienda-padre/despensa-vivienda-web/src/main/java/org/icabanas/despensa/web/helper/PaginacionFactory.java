package org.icabanas.despensa.web.helper;

import javax.servlet.http.HttpServletRequest;

import org.icabanas.despensa.web.util.PreferenciasUsuario;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaginacionFactory<T> {

	private static final Logger logger = LoggerFactory.getLogger(PaginacionFactory.class);
	public static final int NUMERO_REGISTROS_POR_PAGINA_DEFAULT = 5;
	public static final String PAGINA_ACTUAL = "page";
	public static final String NUMERO_REGISTROS_POR_PAGINA = "NUMERO_REGISTROS_POR_PAGINA";
	
//	public IPaginacionExtendida<T> getPaginacionDeRequest(
//			HttpServletRequest request,	ResultadoPaginado<T> listadoPaginado) {
//		Integer numeroRegistrosPorPagina = null;
//		
//		// recupero de la sesión el número de registros por página
//		try{
//			numeroRegistrosPorPagina = (Integer) request.getSession().getAttribute(IParametros.NUMERO_REGISTROS_POR_PAGINA);
//		}
//		catch(Exception ex){
//			numeroRegistrosPorPagina = NUMERO_REGISTROS_POR_PAGINA_DEFAULT;
//		}
//		numeroRegistrosPorPagina = (numeroRegistrosPorPagina == null ? NUMERO_REGISTROS_POR_PAGINA_DEFAULT : numeroRegistrosPorPagina);
//
//		// recupero la página a la que se debe navegar de la request
//		String _paginaActual = request.getParameter(IParametros.PAGINA_ACTUAL);
//		int paginaActual = (_paginaActual == null ? 1 : Integer.parseInt(_paginaActual));
//		
//		IPaginacionExtendida<T> paginacion = new PaginacionExtendidaImpl<T>(listadoPaginado, numeroRegistrosPorPagina, paginaActual);
//		
//		return paginacion;
//	}

	public Pagina<T> getPagina(HttpServletRequest request){

		// recupero de la sesión el número de registros por página		
		int numeroRegistrosPorPagina;
		try{
			numeroRegistrosPorPagina = ((Integer) request.getSession().getAttribute(NUMERO_REGISTROS_POR_PAGINA)).intValue();
		}
		catch(Exception ex){
			numeroRegistrosPorPagina = NUMERO_REGISTROS_POR_PAGINA_DEFAULT;
		}
		numeroRegistrosPorPagina = (numeroRegistrosPorPagina == 0 ? NUMERO_REGISTROS_POR_PAGINA_DEFAULT : numeroRegistrosPorPagina);

		// recupero la página a la que se debe navegar de la request
		String _paginaActual = request.getParameter(PAGINA_ACTUAL);
		int paginaActual = (_paginaActual == null ? 1 : Integer.parseInt(_paginaActual));
		
		// creo el objeto página
		Pagina<T> pagina = new Pagina<T>(paginaActual,numeroRegistrosPorPagina);
		
		return pagina;
	}

	/**
	 * Crea una instancia de {@link Pagina} en base a las preferencias del usuario, que contiene el número de 
	 * registros que se muestran por página, y a la página del resultado de la consulta.
	 * 
	 * @param preferenciasUsuario
	 * @param paginaActual
	 * @return
	 */
	public Pagina<T> getPagina(final PreferenciasUsuario preferenciasUsuario, int paginaActual) {
		int numeroRegistrosPorPagina = 0;
		
		if(preferenciasUsuario != null){			
			numeroRegistrosPorPagina = preferenciasUsuario.getNumeroRegistrosPorPagina();
		}
		else{
			logger.warn("No se han establecido las preferencias del usuario.");
		}
				
		return new Pagina<T>(paginaActual,numeroRegistrosPorPagina);
	}
}
