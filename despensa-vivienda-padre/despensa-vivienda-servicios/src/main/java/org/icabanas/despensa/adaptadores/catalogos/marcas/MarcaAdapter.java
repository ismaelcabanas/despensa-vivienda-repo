package org.icabanas.despensa.adaptadores.catalogos.marcas;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.modelo.Marca;

public final class MarcaAdapter {

	public static Marca toEntidad(MarcaDto dto) {
		Marca entidad = null;
		
		if(dto != null){					
			entidad = new Marca();
			
			entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());						
		}
		
		return entidad; 
	}

	public static MarcaDto toDto(Marca entidad) {
		MarcaDto dto = null;
		
		if(entidad != null){
			dto = new MarcaDto();
			dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
		}
		
		return dto;
	}

	public static List<MarcaDto> toDto(List<Marca> entidades) {		
		List<MarcaDto> dtos = new ArrayList<MarcaDto>();
		
		if(entidades != null){
			for (Marca entidad : entidades) {
				dtos.add(toDto(entidad));
			}
		}

		return dtos;
	}

}
