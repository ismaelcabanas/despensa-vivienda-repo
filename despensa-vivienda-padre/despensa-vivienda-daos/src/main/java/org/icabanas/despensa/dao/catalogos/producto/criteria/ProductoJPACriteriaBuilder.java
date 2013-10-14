package org.icabanas.despensa.dao.catalogos.producto.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang.StringUtils;
import org.icabanas.despensa.catalogos.producto.dto.ProductoFiltro;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.despensa.modelo.Producto_;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.jpa.JPACriteriaBuilderSupport;

public class ProductoJPACriteriaBuilder extends JPACriteriaBuilderSupport<Producto, ProductoFiltro> {

	public ProductoJPACriteriaBuilder(){}
	
	public ProductoJPACriteriaBuilder(Class<Producto> entityClass) {
		super(entityClass);
	}

	@Override
	protected void doConstruir(CriteriaQuery<Producto> criteria,
			CriteriaBuilder cb, ProductoFiltro filtro) {
		if(filtro != null){
			List<Predicate> predicates = new ArrayList<Predicate>();
			Path<Marca> marcaPath = root.<Marca>get(Producto_.marca);
			if(StringUtils.isNotEmpty(filtro.getNombre()) && StringUtils.isNotBlank(filtro.getNombre())){
				//predicates.add(cb.like(cb.lower(root.<String>get(Producto_.nombre)), "%" + filtro.getNombre().toLowerCase() + "%"));
				predicates.add(like(Producto_.nombre.getName(), filtro.getNombre()));
			}
			if(StringUtils.isNotEmpty(filtro.getCodigo()) && StringUtils.isNotBlank(filtro.getCodigo())){
				predicates.add(igualQue(Producto_.codigo.getName(), filtro.getCodigo()));
			}
			if(filtro.getMarca() != null && filtro.getMarca().getId() != null){
				predicates.add(igualQue(Producto_.marca.getName(), filtro.getMarca().getId()));
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
