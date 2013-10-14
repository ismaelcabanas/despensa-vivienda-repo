package org.icabanas.despensa.catalogos.productos;

import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.jee.api.integracion.manager.IGenericManager;

/**
 * Interfaz que gestiona el cat�logo de productos.
 * 
 * @author f009994r
 *
 */
public interface ICatalogoProductos extends IGenericManager<Long, ProductoDto> {	

	
	/**
	 * M�todo que registra un producto en el cat�logo del productos.
	 * 
	 * @param 
	 * 		productoDto El producto a registrar en el cat�logo.
	 * 
	 * @return
	 * 		El producto registrado.
	 * 
	 * @throws RegistrarException Si se produce alguna excepci�n de validaci�n al registrar el producto.
	 */
//	ProductoDto registrar(ProductoDto productoDto) throws RegistrarException;

//	/**
//	 * M�todo que elimina un producto del cat�logo de productos.
//	 * 
//	 * @param 
//	 * 		id Identificador del producto.
//	 * @throws 
//	 * 		NoExisteEntidadException si no existe el producto.
//	 */
//	void eliminar(Long id) throws NoExisteEntidadException;

//	/**
//	 * M�todo que modifica las caracter�sticas de un producto.
//	 * 
//	 * @param producto Producto con las nuevas caracter�sticas.
//	 * 
//	 * @return Producto modificado.
//	 */
//	Producto modificar(Producto producto) throws ModificarProductoException;	
	
//	/**
//	 * M�todo que actualiza las caracter�sticas de un producto del cat�logo.
//	 * 
//	 * @param 
//	 * 		productoDto Las nuevas caracter�sticas del producto.
//	 * 
//	 * @return El producto actualizado.
//	 * 
//	 * @throws 
//	 * 		NoExisteEntidadException Si el producto no existe en sistema de almacenamiento.
//	 * 
//	 * @throws 
//	 * 		ModificarException Si se produce alguna excepci�n de validaci�n al actualizar el producto. 	 
//	 */
//	ProductoDto actualizar(ProductoDto productoDto) throws ModificarException, NoExisteEntidadException;
	
	/**
	 * M�todo que busca el producto del cat�logo de un determinado c�digo.
	 * 
	 * @param 
	 * 		codigo C�digo del producto.
	 * 
	 * @return Producto del cat�logo con el c�digo de producto buscado.
	 */
	ProductoDto buscarPorCodigo(String codigo);	
	
//	/**
//	 * M�todo que recupera los productos del cat�logo paginados ordenados por nombre.
//	 * 
//	 * @param criterioBusqueda
//	 * 		El criterio de b�squeda
//	 * @param paginaActual
//	 * 		P�gina actual.
//	 * @param numeroRegistrosPorPagina
//	 * 		N�mero de registros por p�gina.
//	 * @return
//	 */
//	ResultadoPaginado<ProductoDto> buscar(int paginaActual, int numeroRegistrosPorPagina);
//	
//	/**
//	 * M�todo que recupera los productos del cat�logo paginados que cumplen con el criterio de b�squeda.
//	 * 
//	 * @param criterioBusqueda
//	 * 		El criterio de b�squeda
//	 * @param paginaActual
//	 * 		P�gina actual.
//	 * @param numeroRegistrosPorPagina
//	 * 		N�mero de registros por p�gina.
//	 * @return
//	 */
//	ResultadoPaginado<ProductoDto> buscar(ProductoBusquedaDto criterioBusqueda, int paginaActual, int numeroRegistrosPorPagina);

//	/**
//	 * M�todo que comprueba si un producto es v�lido.
//	 * 
//	 * @param 
//	 * 		productoDto El producto.
//	 * 
//	 * @return 
//	 * 		True si los datos del producto son v�lidos.
//	 * 
//	 * @throws 
//	 * 		ValidacionException Si el producto no es v�lido, indicando qu� campos son los incorrectos.
//	 * 
//	 * @throws 
//	 * 		IllegalArgumentException Si el par�metro de entrada es nulo.
//	 */
//	boolean validar(ProductoDto productoDto) throws ValidacionException;

//	/**
//	 * M�todo que realiza una b�squeda de productos por identificador.
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
//	 * Realiza una paginaci�n de registros.
//	 * 
//	 * @param pagina
//	 * @throws DespensaException
//	 * 		Si se produce alguna excepci�n durante la paginaci�n.
//	 */
//	void paginar(Pagina<ProductoDto> pagina) throws DespensaException;
	
	
}
