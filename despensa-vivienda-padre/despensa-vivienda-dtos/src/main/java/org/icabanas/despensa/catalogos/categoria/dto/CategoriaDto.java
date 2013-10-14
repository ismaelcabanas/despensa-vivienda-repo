package org.icabanas.despensa.catalogos.categoria.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.icabanas.jee.api.integracion.dto.BaseDto;

public class CategoriaDto extends BaseDto<Long>{

	@NotEmpty
	private String nombre;
	
	public CategoriaDto(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaDto() {
	}

	public CategoriaDto(Long id, String nombre) {
		super(id);
		this.nombre = nombre;				
	}

	private static final long serialVersionUID = 1L;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CategoriaDto [nombre=" + nombre + ", getId()=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaDto other = (CategoriaDto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
