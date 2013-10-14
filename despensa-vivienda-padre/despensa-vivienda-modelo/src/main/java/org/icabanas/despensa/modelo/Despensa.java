package org.icabanas.despensa.modelo;


public class Despensa {

//	private Map<Producto,ProductoDespensa> _productos = new HashMap<Producto, ProductoDespensa>();
//	
//	public void repone(Producto producto, int cantidad) throws ReponerException {
//		
//		if(cantidad < 0)
//			throw new ReponerException("No se permite reponer una cantidad negativa de productos.");
//		
//		ProductoDespensa productoDespensa = new ProductoDespensa(producto, cantidad);
//		
//		if(_productos.containsKey(productoDespensa.getProducto())){
//			ProductoDespensa pd = buscar(productoDespensa.getProducto());
//			if(pd != null){
//				int provisiones = pd.getCantidad() + cantidad;
//				pd.setCantidad(provisiones);
//			}
//		}
//		else{
//			_productos.put(productoDespensa.getProducto(), productoDespensa);
//		}
//	}
//		
//	public int provisiones(Producto producto) {
//		ProductoDespensa pd = buscar(producto);
//		
//		return pd.getCantidad();
//	}
//
//	public void consume(Producto producto, int cantidad) throws ExcesoConsumoException, ProductoAgotadoException{
//		ProductoDespensa pd = buscar(producto);
//		if(pd != null){
//			if(cantidad > pd.getCantidad())
//				throw new ExcesoConsumoException("No se puede consumir más de lo que hay en la despensa.");
//			pd.setCantidad(pd.getCantidad() - cantidad);
//			if(pd.getCantidad() == 0)
//				throw new ProductoAgotadoException("El producto se ha agotado.");
//		}		
//	}
//
//	public void elimina(Producto producto) {
//		_productos.remove(producto);		
//	}
//	
//	public ProductoDespensa buscar(Producto producto) {
//		ProductoDespensa productoDespensa = _productos.get(producto);
//		return productoDespensa;
//	}
//	
//	public Map<Producto, ProductoDespensa> getProductos() {
//		return _productos;
//	}
//
//	public void setProductos(Map<Producto, ProductoDespensa> productos) {
//		this._productos = productos;
//	}
	
	
}
