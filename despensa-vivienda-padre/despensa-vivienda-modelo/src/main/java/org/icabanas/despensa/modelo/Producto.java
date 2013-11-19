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

	private Categoria categoria;
	
	public Producto() {}
	
	public Producto(String nombre, String codigo) {
		this(codigo,nombre,Categoria.DESCATEGORIZADO,null);
	}				

	public Producto(String codigo, String nombre, Marca marca) {
		this(codigo,nombre,Categoria.DESCATEGORIZADO,marca);		
	}

	public Producto(String codigo, String nombre, Categoria categoria,
			Marca marca) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.categoria = categoria;
		this.marca = marca;
	}

	public Producto(String codigo, String nombre, Categoria categoria) {		
		this(codigo,nombre,categoria,null);
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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_categoria")
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public boolean valida() throws ValidacionException {
		ValidacionException excepcion = new ValidacionException();
		
		if(nombre == null || nombre.equals("")){
			excepcion.anade("producto.nombre","error.producto.nombre.requerido");
		}
		if(codigo == null || codigo.equals("")){
			excepcion.anade("producto.codigo", "error.producto.codigo.requerido");			
		}
		if(categoria == null){
			excepcion.anade("producto.categoria", "error.producto.categoria.requerido");
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
	 * @param marca Identificador de la marca del producto.
	 * @return
	 * @throws ValidacionException Si la instancia de la clase no es válida.
	 */
	public static Producto registrar(String codigo, String nombre,
			Marca marca) throws ValidacionException {
		Producto producto = new Producto(codigo,nombre,marca);
		
		if(producto.valida())
			return producto;
		
		return null;
	}
	
	/**
	 * @param codigo
	 * @param nombre
	 * @param categoria
	 * @return
	 * @throws ValidacionException
	 */
	public static Producto registrar(String codigo, String nombre,
			Categoria categoria) throws ValidacionException {
		return registrar(codigo, nombre, categoria, null);
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
		return registrar(codigo,nombre,Categoria.DESCATEGORIZADO,null);
	}

	/**
	 * Crea una instancia de la clase.
	 * 
	 * @param codigo
	 * @param nombre
	 * @param categoria
	 * @param marca
	 * @throws ValidacionException
	 * @return
	 */
	public static Producto registrar(String codigo, String nombre,
			Categoria categoria, Marca marca) throws ValidacionException{
		Producto producto = new Producto(codigo, nombre, categoria, marca);
		
		if(producto.valida())
			return producto;
		
		return null;
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
