package org.icabanas.despensa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;


@Entity
@Table(name="Marca")
@NamedQuery(name="tieneProductosAsociados", query="SELECT COUNT(p) FROM Producto p WHERE p.marca.id = :id")
public class Marca extends EntidadBase<Long> {

	private String nombre;
		
	public Marca(){}
	
	public Marca(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Marca(Long idMarca) {
		super();
		setId(idMarca);
	}	

	@Column(nullable=false,length=50,unique=true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Marca other = (Marca) obj;
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

	public static Marca registrar(String nombre) throws ValidacionException{
		Marca marca = new Marca(nombre);
		
		if(marca.valida()){
			return marca;
		}
		
		return null;
	}

	public Marca modifica(String nombre) {
		setNombre(nombre);
		return this;
	}

	@Override
	public boolean valida() throws ValidacionException {
		ValidacionException excepcion = new ValidacionException();
		
		if(nombre == null || nombre.equals(""))
			excepcion.anade("nombre","error.marca.nombre");
		
		if(excepcion.hayErrores()){
			throw excepcion;
		}
		
		return true;
	}

	@Override
	public boolean esVacia() {
		if(nombre == null || nombre.equals("")){
			return true;
		}
		return false;
	}

	public void actualizar(String nombre) throws ValidacionException {
		setNombre(nombre);
		
		this.valida();
	}

	
	
}
