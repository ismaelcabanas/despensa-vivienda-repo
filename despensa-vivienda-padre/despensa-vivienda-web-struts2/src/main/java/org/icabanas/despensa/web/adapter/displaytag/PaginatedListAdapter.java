package org.icabanas.despensa.web.adapter.displaytag;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.icabanas.jee.api.integracion.dao.Pagina;

public class PaginatedListAdapter implements PaginatedList {

	private Pagina<?> pagina;
	
	/**
	 * Indica si la paginación es vacía.
	 */
	private boolean vacia;		

	public PaginatedListAdapter(Pagina<?> pagina) {
		super();
		this.pagina = pagina;
	}

	@Override
	public int getFullListSize() {
		return pagina.getNumeroTotalRegistros();
	}

	@Override
	public List getList() {
		return pagina.getDatos();
	}

	@Override
	public int getObjectsPerPage() {
		return pagina.getNumeroRegistrosPorPagina();
	}

	@Override
	public int getPageNumber() {
		return pagina.getPagina();
	}

	@Override
	public String getSearchId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSortCriterion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortOrderEnum getSortDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pagina<?> getPagina() {
		return pagina;
	}
	
	public boolean isVacia() {
		vacia = (pagina == null || (pagina != null && pagina.getNumeroTotalRegistros() <= 0));
		
		return vacia;
	}

	public void setVacia(boolean vacia) {
		this.vacia = vacia;
	}
}
