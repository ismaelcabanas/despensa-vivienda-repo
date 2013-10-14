package org.icabanas.despensa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

@Entity
@Table(name="Producto")
public class Producto extends EntidadBase<Long>{
	
	private String nombre; 
		
	private Marca marca;
	
	private String codigo;
	
	public Producto(String nombre, String codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.marca = null;
	}		
	
	public Producto() {}

	
	public Producto(String nombre, String marca, String codigo) {
		super();
		this.nombre = nombre;
		if(marca != null)
			this.marca = new Marca(marca);
		else
			this.marca = null;
		this.codigo = codigo;
	}

	public Producto(String nombre, Marca marca, String codigo) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.codigo = codigo;
	}

	public Producto(String nombre, String codigo, Long idMarca) {
		this.nombre = nombre;
		this.codigo = codigo;
		if(idMarca != null){
			this.marca = new Marca(idMarca);
		}
		else{
			this.marca = null;
		}
	}

	@Column(name="nombre",nullable=false,length=50)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * En la anotación @ManyToOne no es necesario establecer el atributo cascade, ya que la entidad Marca se gestiona 
	 * independientemente de Producto. //Lo anoto como MERGE puesto que a la hora de persistir un producto, la marca asociada será detached
	 * 
	 * 
	 * @return
	 */
	@ManyToOne(optional=true) 
	@JoinColumn(name="id_marca")
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public void setMarca(String marca){
		this.marca = new Marca(marca);
	}

	@Column(name="codigo",unique=true, nullable=false,length=10)	
	public String getCodigo() {
		return this.codigo;
	}
	
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(codigo).append(" - ").append(nombre).append(" ");
		if(marca != null){
			sb.append(marca.toString());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Producto other = (Producto) obj;
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

//	public Producto crea(String nombre, String nombreMarca) {
//		Marca marca = null;
//		
//		this.nombre = nombre;		
//		if(nombreMarca != null){
//			marca = new Marca(nombreMarca);			
//		}
//		else{
//			marca = Marca.MARCA_GENERICA;
//		}
//		this.marca = marca;	
//				
//		return this;
//	}
//
//	public Producto crea(String nombre) {
//		return crea(nombre,null);
//	}
	
	public boolean valida() throws ValidacionException {
		ValidacionException excepcion = new ValidacionException();
		
		if(nombre == null || nombre.equals("")){
			excepcion.anade("producto.nombre","error.producto.nombre.requerido");
		}
		if(codigo == null || codigo.equals("")){
			excepcion.anade("producto.codigo", "error.producto.codigo.requerido");
		}
//		if(marca == null){
//			excepcion.anade(CAMPO_MARCA, "producto.marca.requerido");
//		}
		
		if(excepcion.hayErrores()){
			throw excepcion;
		}
		return true;
	}

	@Override
	public boolean esVacia() {
		if((nombre == null || nombre.equals(""))
				&& (codigo == null || codigo.equals(""))
				&& (marca == null || marca.esVacia())){
			return true;
		}		
		
		return false;
	}

	/**
	 * Método que crea una instancia de la clase.
	 * 
	 * @param codigo Código del producto.
	 * @param nombre Nombre del producto.
	 * @param idMarca Identificador de la marca del producto.
	 * @return
	 * @throws ValidacionException Si la instancia de la clase no es válida.
	 */
	public static Producto registrar(String codigo, String nombre,
			Long idMarca) throws ValidacionException {
		Producto producto = new Producto(nombre,codigo,idMarca);
		
		if(producto.valida())
			return producto;
		
		return null;
	}

	/**
	 * Método que crea una instancia de la clase.
	 * 
	 * @param codigo Código del producto.
	 * @param nombre Nombre del producto.
	 * @return
	 * @throws ValidacionException Si la instancia de la clase no es válida.
	 */
	public static Producto registrar(String codigo, String nombre) throws ValidacionException {
		return registrar(codigo,nombre,null);
	}

	/**
	 * Método que modifica las propiedades de la instancia.
	 * 
	 * @param codigo Nuevo código de producto.
	 * @param nombre Nuevo nombre de producto.
	 * @param idMarca Nuevo identificado de marca de producto.
	 * @throws ValidacionException 
	 */
	public void actualizar(String codigo, String nombre, Long idMarca) throws ValidacionException {
		this.codigo = codigo;
		this.nombre = nombre;
		if(idMarca != null){
			this.marca = new Marca(idMarca);
		}
		
		this.valida();
	}

	/**
	 * Método que modifica las propiedades de la instancia.
	 * 
	 * @param codigo Nuevo código de producto.
	 * @param nombre Nuevo nombre de producto.
	 * @throws ValidacionException 
	 */
	public void actualizar(String codigo, String nombre) throws ValidacionException {
		actualizar(codigo, nombre, null);
	}
	
	
}
