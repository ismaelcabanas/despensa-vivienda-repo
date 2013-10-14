package org.icabanas.despensa.zk.catalogos;



public interface ICatalogoVM {

	/**
	 * Alta de un nuevo producto. 
	 */
	public abstract void alta();

	/**
	 * Busca productos en base a un criterio de b�squeda.
	 */
	public abstract void buscar();

	/**
	 * Limpia el formulario de b�squeda de productos.
	 */
	public abstract void limpiar();

	/**
	 * Persiste un producto en el repositorio de datos. 
	 */
	public abstract void guardar();

	/**
	 * Edita el producto seleccionado.
	 * 
	 */
	public abstract void editar();

	/**
	 * Modifica el producto seleccionado y lo persiste en el repositorio de datos.
	 */
	public abstract void actualizar();

	/**
	 * Elimina el producto seleccionado del repositorio de datos
	 * 
	 */
	public abstract void eliminar();
	
	
	/**
	 * Cancela una acci�n 
	 */
	public abstract void cancelar();

	/**
	 * Eidta el producto seleccionado en modo solo lectura.
	 * 
	 */
	void detalle();
	
	/**
	 * Vuelve a la acci�n anterior 
	 */
	public abstract void volver();


}