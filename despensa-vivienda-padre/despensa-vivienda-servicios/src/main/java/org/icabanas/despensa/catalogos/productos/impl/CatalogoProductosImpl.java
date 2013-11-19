package org.icabanas.despensa.catalogos.productos.impl;

import org.icabanas.despensa.adaptadores.catalogos.marcas.MarcaAdapter;
import org.icabanas.despensa.adaptadores.catalogos.productos.ProductoAdapter;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoFiltro;
import org.icabanas.despensa.catalogos.productos.ICatalogoProductos;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.icabanas.jee.api.integracion.manager.impl.AbstractGenericManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que implementa la interfaz {@link ICatalogoProductos}.
 * 
 * @author f009994r
 *
 */
public class CatalogoProductosImpl extends AbstractGenericManager<Long, ProductoDto, Producto>implements ICatalogoProductos {

	private static final Logger logger = LoggerFactory.getLogger(CatalogoProductosImpl.class);
	
//	private IGenericDao<Long, Producto> catalogoProductosDao;
	
	public CatalogoProductosImpl() {}
	
	public CatalogoProductosImpl(IGenericDao<Long, Producto> dao) {
		setDao(dao);
	}

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.catalogos.productos.CatalogoProductos#buscarProductosPorCodigo(java.lang.String)
	 */
	@Override
	public ProductoDto buscarPorCodigo(String codigo) {
		ProductoFiltro filtro = new ProductoFiltro();
		filtro.setCodigo(codigo);
		Producto entidadProducto = getDao().buscarUnico(filtro );
		
		return adaptarToDto(entidadProducto);
	}
	
//	public IGenericDao<Long, Producto> getCatalogoProductosDao() {
//		return getDao();
//	}
//
//	public void setCatalogoProductosDao(
//			IGenericDao<Long, Producto> catalogoProductosDao) {
//		this.catalogoProductosDao = catalogoProductosDao;
//	}

	@Override
	protected ProductoDto adaptarToDto(Producto entidad) {
		return ProductoAdapter.toDto(entidad);
	}
	
	

	@Override
	protected Producto adaptarToEntidad(ProductoDto dto) {
		return ProductoAdapter.toEntidad(dto);
	}

	@Override
	protected Producto crearEntidad(ProductoDto dto) throws ValidacionException {
		Producto producto = null;

		// compruebo si existe en el repositorio un producto con el mismo código
		if(buscarPorCodigo(dto.getCodigo()) != null){
			ValidacionException excepcion = new ValidacionException();
			excepcion.anade("error.producto.existe");
			throw excepcion;
		}
		
		// se crea y valida el producto
		if(dto.getMarca() != null){
			producto = Producto.registrar(dto.getCodigo(),dto.getNombre(),MarcaAdapter.toEntidad(dto.getMarca()));
		}
		else{
			producto = Producto.registrar(dto.getCodigo(),dto.getNombre());
		}
				
		return producto;
	}

	@Override
	protected void actualizarEntidad(Producto producto, ProductoDto dto)
			throws ValidacionException {
		if(dto.getMarca() != null){
			producto.actualizar(dto.getCodigo(),dto.getNombre(),dto.getMarca().getId());
		}
		else{
			producto.actualizar(dto.getCodigo(),dto.getNombre());
		}
	}	
}
