package org.icabanas.despensa.catalogos.productos;

import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.jee.api.integracion.manager.IGenericManager;

/**
 * Interfaz que gestiona el catálogo de productos.
 * 
 * @author f009994r
 *
 */
public interface ICatalogoProductos extends IGenericManager<Long, ProductoDto> {	

	
	/**
	 * Método que registra un producto en el catálogo del productos.
	 * 
	 * @param 
	 * 		productoDto El producto a registrar en el catálogo.
	 * 
	 * @return
	 * 		El producto registrado.
	 * 
	 * @throws RegistrarException Si se produce alguna excepción de validación al registrar el producto.
	 */
//	ProductoDto registrar(ProductoDto productoDto) throws RegistrarException;

//	/**
//	 * Método que elimina un producto del catálogo de productos.
//	 * 
//	 * @param 
//	 * 		id Identificador del producto.
//	 * @throws 
//	 * 		NoExisteEntidadException si no existe el producto.
//	 */
//	void eliminar(Long id) throws NoExisteEntidadException;

//	/**
//	 * Método que modifica las características de un producto.
//	 * 
//	 * @param producto Producto con las nuevas características.
//	 * 
//	 * @return Producto modificado.
//	 */
//	Producto modificar(Producto producto) throws ModificarProductoException;	
	
//	/**
//	 * Método que actualiza las características de un producto del catálogo.
//	 * 
//	 * @param 
//	 * 		productoDto Las nuevas características del producto.
//	 * 
//	 * @return El producto actualizado.
//	 * 
//	 * @throws 
//	 * 		NoExisteEntidadException Si el producto no existe en sistema de almacenamiento.
//	 * 
//	 * @throws 
//	 * 		ModificarException Si se produce alguna excepción de validación al actualizar el producto. 	 
//	 */
//	ProductoDto actualizar(ProductoDto productoDto) throws ModificarException, NoExisteEntidadException;
	
	/**
	 * Método que busca el producto del catálogo de un determinado código.
	 * 
	 * @param 
	 * 		codigo Código del producto.
	 * 
	 * @return Producto del catálogo con el código de producto buscado.
	 */
	ProductoDto buscarPorCodigo(String codigo);	
	
//	/**
//	 * Método que recupera los productos del catálogo paginados ordenados por nombre.
//	 * 
//	 * @param criterioBusqueda
//	 * 		El criterio de búsqueda
//	 * @param paginaActual
//	 * 		Página actual.
//	 * @param numeroRegistrosPorPagina
//	 * 		Número de registros por página.
//	 * @return
//	 */
//	ResultadoPaginado<ProductoDto> buscar(int paginaActual, int numeroRegistrosPorPagina);
//	
//	/**
//	 * Método que recupera los productos del catálogo paginados que cumplen con el criterio de búsqueda.
//	 * 
//	 * @param criterioBusqueda
//	 * 		El criterio de búsqueda
//	 * @param paginaActual
//	 * 		Página actual.
//	 * @param numeroRegistrosPorPagina
//	 * 		Número de registros por página.
//	 * @return
//	 */
//	ResultadoPaginado<ProductoDto> buscar(ProductoBusquedaDto criterioBusqueda, int paginaActual, int numeroRegistrosPorPagina);

//	/**
//	 * Método que comprueba si un producto es válido.
//	 * 
//	 * @param 
//	 * 		productoDto El producto.
//	 * 
//	 * @return 
//	 * 		True si los datos del producto son válidos.
//	 * 
//	 * @throws 
//	 * 		ValidacionException Si el producto no es válido, indicando qué campos son los incorrectos.
//	 * 
//	 * @throws 
//	 * 		IllegalArgumentException Si el parámetro de entrada es nulo.
//	 */
//	boolean validar(ProductoDto productoDto) throws ValidacionException;

//	/**
//	 * Método que realiza una búsqueda de productos por identificador.
//	 * 
//	 * @param 
//	 * 		idProducto el identificador del producto.
//	 * 
//	 * @return El producto
//	 *  
//	 * @throws 
//	 * 		NoExisteEntidadException si el producto no existe
//	 */
//	ProductoDto buscarPorId(Long idProducto) throws NoExisteEntidadException;

//	/**
//	 * Realiza una paginación de registros.
//	 * 
//	 * @param pagina
//	 * @throws DespensaException
//	 * 		Si se produce alguna excepción durante la paginación.
//	 */
//	void paginar(Pagina<ProductoDto> pagina) throws DespensaException;
	
	
}
