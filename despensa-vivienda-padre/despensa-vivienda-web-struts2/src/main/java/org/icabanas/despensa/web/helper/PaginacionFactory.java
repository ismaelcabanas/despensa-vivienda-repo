package org.icabanas.despensa.web.helper;

import javax.servlet.http.HttpServletRequest;

import org.icabanas.despensa.util.IParametros;
import org.icabanas.jee.api.integracion.dao.Pagina;

public class PaginacionFactory<T> {

	public static final int NUMERO_REGISTROS_POR_PAGINA_DEFAULT = 5;
	
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
			numeroRegistrosPorPagina = ((Integer) request.getSession().getAttribute(IParametros.NUMERO_REGISTROS_POR_PAGINA)).intValue();
		}
		catch(Exception ex){
			numeroRegistrosPorPagina = NUMERO_REGISTROS_POR_PAGINA_DEFAULT;
		}
		numeroRegistrosPorPagina = (numeroRegistrosPorPagina == 0 ? NUMERO_REGISTROS_POR_PAGINA_DEFAULT : numeroRegistrosPorPagina);

		// recupero la página a la que se debe navegar de la request
		String _paginaActual = request.getParameter(IParametros.PAGINA_ACTUAL);
		int paginaActual = (_paginaActual == null ? 1 : Integer.parseInt(_paginaActual));
		
		// creo el objeto página
		Pagina<T> pagina = new Pagina<T>(paginaActual,numeroRegistrosPorPagina);
		
		return pagina;
	}
}
