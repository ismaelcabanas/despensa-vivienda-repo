package org.icabanas.despensa.catalogos.marcas.impl;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.despensa.adaptadores.catalogos.marcas.MarcaAdapter;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marca.dto.MarcaFiltro;
import org.icabanas.despensa.catalogos.marcas.ICatalogoMarcas;
import org.icabanas.despensa.dao.catalogos.marca.ICatalogoMarcaDao;
import org.icabanas.despensa.modelo.Marca;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.Orden;
import org.icabanas.jee.api.integracion.dao.consulta.OrderEnum;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.icabanas.jee.api.integracion.manager.impl.AbstractGenericManager;

public class CatalogoMarcasImpl extends AbstractGenericManager<Long, MarcaDto, Marca> implements ICatalogoMarcas {

//	private ICatalogoMarcasDAO catalogoMarcasDao;
	
	public CatalogoMarcasImpl() {
	}

	public CatalogoMarcasImpl(IGenericDao<Long, Marca> catalogoMarcasDao) {
		setDao(catalogoMarcasDao);
	}


	@Override
	public List<MarcaDto> buscarTodas() {
		// filtro de búsqueda
		IFiltro filtro = new MarcaFiltro();
		List<Orden> orden = new ArrayList<Orden>();
		orden.add(new Orden("nombre", OrderEnum.ASC));
		filtro.setOrden(orden);
		
		//		List<Marca> resultado = catalogoMarcasDao.buscarTodas();
		List<Marca> resultado = getDao().buscar(filtro);
		
		return MarcaAdapter.toDto(resultado);
	}
	
	
	/* 
	 * Elimina la entidad Marca que corresponde con el identificador <code>id</code>.
	 * 
	 * Al eliminar una marca puede ocurrir que está asociado a un producto, en ese caso se 
	 * lanza una EliminarEntidadException indicando el motivo de por qué no puede eliminar la entidad.
	 * 
	 * @see org.icabanas.jee.api.integracion.manager.impl.AbstractGenericManager#eliminar(java.io.Serializable)
	 */
	@Override
	public void eliminar(Long id) throws NoExisteEntidadException, EliminarException {		
		if(tieneProductosAsociados(id)){
			ValidacionException excepcion = new ValidacionException();
			excepcion.anade("error.marca.productos");
			throw new EliminarException(excepcion);
		}
		super.eliminar(id);
	}	

	@Override
	protected MarcaDto adaptarToDto(Marca entidad) {
		return MarcaAdapter.toDto(entidad);
	}
	
	@Override
	protected Marca adaptarToEntidad(MarcaDto dto) {
		return MarcaAdapter.toEntidad(dto);
	}

	@Override
	protected Marca crearEntidad(MarcaDto dto) throws ValidacionException {
		Marca marca = null;
		
		// compruebo si existe en el repositorio una marca con el mismo nombre
		if(esMarcaDuplicada(dto)){
			ValidacionException excepcion = new ValidacionException();
			excepcion.anade("error.marca.existe");
			throw excepcion;
		}
		
		if(dto != null){
			marca = Marca.registrar(dto.getNombre());
		}
		
		return marca;
	}	

	@Override
	protected void actualizarEntidad(Marca marca, MarcaDto dto)
			throws ValidacionException {
		if(dto != null){
			marca.actualizar(dto.getNombre());
		}
	}
	
	private boolean esMarcaDuplicada(MarcaDto dto) {
		MarcaFiltro filtro = new MarcaFiltro();
		filtro.setNombre(dto.getNombre());
		
		return (getDao().buscarUnico(filtro) != null);
	}

	private boolean tieneProductosAsociados(Long id) {
		ICatalogoMarcaDao dao = (ICatalogoMarcaDao) getDao();
		
		return dao.tieneProductosAsociados(id);
	}
}
