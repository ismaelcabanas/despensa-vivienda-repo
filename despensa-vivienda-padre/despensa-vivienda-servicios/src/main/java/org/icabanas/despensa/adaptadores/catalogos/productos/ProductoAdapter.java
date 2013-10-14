package org.icabanas.despensa.adaptadores.catalogos.productos;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.despensa.adaptadores.catalogos.marcas.MarcaAdapter;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.modelo.Producto;

public final class ProductoAdapter {

	public static ProductoDto toDto(Producto entidad) {
		ProductoDto dto = null;
		
		if(entidad != null){
			dto = new ProductoDto();
			dto.setCodigo(entidad.getCodigo());
			dto.setNombre(entidad.getNombre());
			dto.setId(entidad.getId());
			dto.setMarca(MarcaAdapter.toDto(entidad.getMarca()));		
		}
		
		return dto;
	}

	public static List<ProductoDto> toDto(List<Producto> entidades) {
		List<ProductoDto> dtos = new ArrayList<ProductoDto>();
		for (Producto producto : entidades) {
			dtos.add(toDto(producto));
		}
		return dtos;
	}

	public static Producto toEntidad(ProductoDto dto) {
		Producto entidad = null;
		
		if(dto != null){
			entidad = new Producto();				
			entidad.setId(dto.getId());
			entidad.setCodigo(dto.getCodigo());
			entidad.setNombre(dto.getNombre());
			entidad.setMarca(MarcaAdapter.toEntidad(dto.getMarca()));
		}
		
		return entidad;
	}

}
