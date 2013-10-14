package org.icabanas.despensa.dao.catalogos.categoria.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang.StringUtils;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.modelo.Categoria;
import org.icabanas.despensa.modelo.Categoria_;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.jpa.JPACriteriaBuilderSupport;

public class CategoriaJPACriteriaBuilder extends
		JPACriteriaBuilderSupport<Categoria, CategoriaFiltro> {

	@Override
	protected void doConstruir(CriteriaQuery<Categoria> criteria,
			CriteriaBuilder cb, CategoriaFiltro filtro) {
		if(filtro != null){
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(StringUtils.isNotEmpty(filtro.getNombre())){
				predicates.add(like(Categoria_.nombre.getName(), filtro.getNombre()));
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
