package org.icabanas.despensa.servicios.impl;

import org.icabanas.despensa.servicios.IGestorDespensa;

public class GestorDespensaImpl implements IGestorDespensa {

//	private ProductoDespensaDAO productoDespensaDao = null;
//
//
//
//	public GestorDespensaImpl(ProductoDespensaDAO gestorDespensaDao) {
//		this.productoDespensaDao = gestorDespensaDao;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.icabanas.despensa.servicios.GestorDespensa#reponer(org.icabanas.despensa.modelo.Producto, int)
//	 */
//	@Override
//	public void reponer(Producto producto, int cantidad) throws ReponerException {
//		
//		ValidadorDespensa.validaReposicionProducto(cantidad);
//		
//		ProductoDespensa productoDespensa = productoDespensaDao.buscarPorId(producto.getId());
//		if(productoDespensa == null){
//			productoDespensa = new ProductoDespensa(producto);
//		}
//		productoDespensa.repone(cantidad);
//		
//		productoDespensaDao.crear(productoDespensa);
//	}	
//
//	
//	/* (non-Javadoc)
//	 * @see org.icabanas.despensa.servicios.GestorDespensa#consumir(org.icabanas.despensa.modelo.Producto)
//	 */
//	@Override
//	public void consumir(Producto producto) throws ExcesoConsumoException, ProductoAgotadoException {
//		consumir(producto,1);		
//	}
//
//	
//	@Override
//	public void consumir(Producto producto, int cantidad) throws ExcesoConsumoException, ProductoAgotadoException {
//		ProductoDespensa productoDespensa = productoDespensaDao.buscarPorId(producto.getId());
//		
//		if(productoDespensa != null){
//			if(ValidadorDespensa.validaExcesoConsumoProducto(productoDespensa.getCantidad(),cantidad)){				
//				productoDespensa.consume(cantidad);
//				productoDespensaDao.crear(productoDespensa);
//				if(productoDespensa.getCantidad() == 0)
//					throw new ProductoAgotadoException("El producto se ha agotado.");
//			}
//		}		
//	}
//		
//	@Override
//	public void eliminar(Producto producto) throws EliminarProductoDespensaException {
//		Long id = producto.getId();
//		ProductoDespensa productoDespensa = productoDespensaDao.buscarPorId(id);
//		if(productoDespensa != null){
//			if(productoDespensa.getCantidad() > 0)
//				throw new EliminarProductoDespensaException("No se puede eliminar el producto de la despensa ya que aún hay existencias.");
//			productoDespensaDao.eliminar(productoDespensa);
//		}
//		else{
//			throw new EliminarProductoDespensaException("El producto no existe en la despensa.");
//		}
//	}		
//
//	@Override
//	public ProductoDespensa buscar(Producto producto) {
//		Long id = producto.getId();
//		return productoDespensaDao.buscarPorId(id);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.icabanas.despensa.servicios.GestorDespensa#provisiones(org.icabanas.despensa.modelo.Producto)
//	 */
//	@Override
//	public int provisiones(Producto producto) {
//		Long id = producto.getId();
//		ProductoDespensa productoDespensa = productoDespensaDao.buscarPorId(id);
//		return productoDespensa.getCantidad();
//	}	
//
//	public ProductoDespensaDAO getGestorDespensaDao() {
//		return productoDespensaDao;
//	}
//
//	public void setGestorDespensaDao(ProductoDespensaDAO gestorDespensaDao) {
//		this.productoDespensaDao = gestorDespensaDao;
//	}

	
}
