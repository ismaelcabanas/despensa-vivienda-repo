package org.icabanas.despensa.catalogos.marca.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.icabanas.jee.api.integracion.dto.BaseDto;

public class MarcaDto extends BaseDto<Long> {

	public MarcaDto(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public MarcaDto() {
		super();
	}

	public MarcaDto(Long id) {
		super(id);
	}

	public MarcaDto(String nombre) {
		super();
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "MarcaDto [id=" + getId() + ", nombre=" + nombre + "]";
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
		MarcaDto other = (MarcaDto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
