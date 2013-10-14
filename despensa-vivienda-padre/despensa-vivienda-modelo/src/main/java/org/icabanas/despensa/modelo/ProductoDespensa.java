package org.icabanas.despensa.modelo;

public class ProductoDespensa{

	private Producto producto;
	
	private int cantidad;

	public ProductoDespensa() {
	}

	public ProductoDespensa(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ProductoDespensa(Producto producto) {
		this.producto = producto;
		this.cantidad = 0;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ProductoDespensa [producto=" + producto + ", cantidad="
				+ cantidad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result
				+ ((producto == null) ? 0 : producto.hashCode());
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
		ProductoDespensa other = (ProductoDespensa) obj;
		if (cantidad != other.cantidad)
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	/**
	 * Método que añade unidades del producto a los que había anteriormente.
	 * 
	 * @param cantidad
	 */
	public void repone(int cantidad) {
		this.cantidad += cantidad;		
	}

	/**
	 * Método que quita una cantidad determinada de existencias del producto.
	 * @param cantidad
	 */
	public void consume(int cantidad) {
		this.cantidad-= cantidad;
		
	}
	
	
}
