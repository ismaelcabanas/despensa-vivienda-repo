package org.icabanas.despensa.dao.catalogos.marca.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang.StringUtils;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Marca_;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.jpa.JPACriteriaBuilderSupport;

/**
 * Clase que se encarga de construir los criterios de búsqueda para la entidad {@link Marca}.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> :  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class MarcaJPACriteriaBuilder extends JPACriteriaBuilderSupport<Marca, MarcaFiltro> {

	
	public MarcaJPACriteriaBuilder() {
	}

	public MarcaJPACriteriaBuilder(Class<Marca> entityClass) {
		super(entityClass);
	}

	@Override
	protected void doConstruir(CriteriaQuery<Marca> criteria,
			CriteriaBuilder cb, MarcaFiltro filtro) {
		
		if(filtro != null){
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(StringUtils.isNotEmpty(filtro.getNombre())){
				predicates.add(like(Marca_.nombre.getName(), filtro.getNombre()));
			}
			
			if(predicates.size() > 0){
				criteria.where(cb.and(predicates.toArray(new Predicate[]{})));
			}
			
			if(filtro.getOrden() != null && filtro.getOrden().size() > 0){
				List<Order> orderBy = new ArrayList<Order>();
				for (Orden orden : filtro.getOrden()) {
					switch(orden.getOrden()){
					case ASC:
						orderBy.add(cb.asc(root.get(orden.getCampo())));				
						break;
					case DESC:
						orderBy.add(cb.desc(root.get(orden.getCampo())));	
						break;
					}		
				}	
				criteria.orderBy(orderBy);
			}
			
		}
	}
	
	

}
