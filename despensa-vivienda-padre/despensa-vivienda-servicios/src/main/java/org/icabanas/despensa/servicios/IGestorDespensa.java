package org.icabanas.despensa.servicios;

import org.icabanas.despensa.excepciones.EliminarProductoDespensaException;
import org.icabanas.despensa.excepciones.ExcesoConsumoException;
import org.icabanas.despensa.excepciones.ProductoAgotadoException;
import org.icabanas.despensa.excepciones.ReponerException;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.despensa.modelo.ProductoDespensa;

public interface IGestorDespensa {

//	/**
//	 * M�todo que repone en la despensa una cantidad determinada de un producto.
//	 * 
//	 * @param producto
//	 * @param cantidad
//	 * @throws ReponerException si se repone una cantidad negativa o cero de un producto.
//	 */
//	public abstract void reponer(Producto producto, int cantidad) throws ReponerException;
//
//	/**
//	 * M�todo que devuelve el n�mero de existencias del producto en la despensa.
//	 * 
//	 * @param producto
//	 * @return
//	 */
//	public abstract int provisiones(Producto producto);
//
//	/**
//	 * M�todo que consume un producto de la despensa.
//	 * 
//	 * @param producto
//	 * @throws ExcesoConsumoException Si se consume del producto m�s de lo que hay en la despensa.
//	 * @throws ProductoAgotadoException 
//	 */
//	public abstract void consumir(Producto producto) throws ExcesoConsumoException, ProductoAgotadoException;
//
//	/**
//	 * M�todo que consume un producto de la despensa.
//	 * 
//	 * @param producto
//	 * @param cantidad
//	 * @throws ExcesoConsumoException Si se consume del producto m�s de lo que hay en la despensa.
//	 * @throws ProductoAgotadoException Si al consumir el producto nos quedamos sin existencias.
//	 */
//	public abstract void consumir(Producto producto, int cantidad) throws ExcesoConsumoException, ProductoAgotadoException;
//
//	/**
//	 * M�todo que elimina un producto de la despensa.
//	 * 
//	 * @param producto
//	 * @throws EliminarProductoDespensaException Si el producto no existe en la despensa o todav�a hay existencias por consumir
//	 */
//	public abstract void eliminar(Producto producto) throws EliminarProductoDespensaException;
//
//	/**
//	 * M�todo que recupera el producto de la despensa
//	 * 
//	 * @param producto
//	 * @return
//	 */
//	public abstract ProductoDespensa buscar(Producto producto);

}