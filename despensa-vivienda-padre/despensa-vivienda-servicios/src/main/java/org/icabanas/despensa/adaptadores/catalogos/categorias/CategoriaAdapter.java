package org.icabanas.despensa.adaptadores.catalogos.categorias;

import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.modelo.Categoria;

public class CategoriaAdapter {

	public static CategoriaDto toDto(Categoria entidad) {
		CategoriaDto dto = null;
		
		if(entidad != null){
			dto = new CategoriaDto();
			dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
			dto.setDescripcion(entidad.getDescripcion());
		}
		
		return dto;
	}

	public static Categoria toEntidad(CategoriaDto dto) {
		Categoria entidad = null;

		if(dto != null){
			entidad = new Categoria();
			entidad.setNombre(dto.getNombre());
			entidad.setDescripcion(dto.getDescripcion());
		}
		return entidad;
	}

}
