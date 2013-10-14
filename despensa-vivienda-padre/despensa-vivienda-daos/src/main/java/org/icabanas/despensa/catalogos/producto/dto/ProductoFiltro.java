package org.icabanas.despensa.catalogos.producto.dto;

import org.apache.commons.lang.StringUtils;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.jee.api.integracion.dao.FiltroBean;

public class ProductoFiltro extends FiltroBean {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String codigo;
	
	private MarcaDto marca;
	
	

	public ProductoFiltro() {
	}

	public ProductoFiltro(String nombreFiltro) {
		super(nombreFiltro);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public MarcaDto getMarca() {
		return marca;
	}

	public void setMarca(MarcaDto marca) {
		this.marca = marca;
	}

	@Override
	public void limpiar() {
		this.nombre = null;
		this.codigo = null;
		this.marca = null;
	}
	
	/* 
	 * Comprueba si el filtro de búsqueda es vacío. 
	 * 
	 * El filtro de búsqueda de los productos es vacío si:
	 * <ul>
	 * 		<li>El nombre del producto es nulo o vacío.</li>
	 * 		<li>El código del producto es nulo o vacío.</li>
	 * 		<li>La marca del producto es nulo o vacío.</li>
	 * </ul>
	 */
	@Override
	public boolean esVacio() {
		return (StringUtils.isEmpty(nombre) && StringUtils.isEmpty(codigo) && (marca == null || marca.getId() == null));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoFiltro other = (ProductoFiltro) obj;
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
