package org.icabanas.despensa.catalogos.producto.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.jee.api.integracion.dto.BaseDto;

public class ProductoDto extends BaseDto<Long> {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nombre;
	
	private MarcaDto marca;
	
	@NotEmpty
	private String codigo;

	private CategoriaDto categoria;

	public ProductoDto(Long id, String codigo, String nombre, MarcaDto marca) {
		super(id);
		this.codigo = codigo;
		this.nombre = nombre;
		this.marca = marca;
	}
		
	public ProductoDto(Long id, String codigo) {
		super(id);
		this.codigo = codigo;
		this.nombre = null;
		this.marca = null;
	}

	

	public ProductoDto() {
		super();
	}

	public ProductoDto(String codigo, String nombre) {
		this(null,codigo,nombre,null,null);		
	}

	public ProductoDto(Long id, String codigo, String nombre, CategoriaDto categoria, MarcaDto marca) {
		super(id);
		this.codigo = codigo;
		this.nombre = nombre;
		this.categoria = categoria;
		this.marca = marca;
	}

	public ProductoDto(Long id, String codigo, String nombre) {
		this(id,codigo,nombre,null,null);
	}

	public ProductoDto(String codigo, String nombre, CategoriaDto categoria,
			MarcaDto marca) {
		this(null,codigo,nombre,categoria,marca);		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public MarcaDto getMarca() {
		return marca;
	}

	public void setMarca(MarcaDto marca) {
		this.marca = marca;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	public CategoriaDto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDto categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ProductoDto [id=" + getId() + ", nombre=" + nombre + ", marca="
				+ marca + ", codigo=" + codigo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
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
		ProductoDto other = (ProductoDto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
