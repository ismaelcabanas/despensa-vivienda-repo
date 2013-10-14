package org.icabanas.despensa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

@Entity
@Table(name="Categoria")
public class Categoria extends EntidadBase<Long> {

	private String nombre;

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Categoria() {
	}

	@Override
	public boolean esVacia() {
		if(nombre == null || nombre.equals("")){
			return true;
		}
		return false;
	}

	@Override
	public boolean valida() throws ValidacionException {
		ValidacionException excepcion = new ValidacionException();
		
		if(nombre == null || nombre.equals("")){
			excepcion.anade("categoria.nombre","categoria.nombre.requerido");
		}
		
		if(excepcion.hayErrores()){
			throw excepcion;
		}
		return true;
	}

	/**
	 * Método que registra una categoría a partir del nombre de la categoría.
	 * 
	 * @param nombre
	 * 		Nombre de la categoría.
	 * @return
	 * 		Nueva instancia de {@link Categoria}.
	 */
	public static Categoria registrar(String nombre) throws ValidacionException{
		Categoria categoria = new Categoria(nombre);
		
		if(categoria.valida())
			return categoria;
		
		return null;
	}

	@Column(nullable=false,length=50,unique=true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void actualizar(String nuevoNombre) throws ValidacionException {
		this.nombre = nuevoNombre;
		
		this.valida();
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
		Categoria other = (Categoria) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(nombre);
		return sb.toString();
	}
}
