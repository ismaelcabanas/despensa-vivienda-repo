package org.icabanas.despensa.catalogos.marcas;

import java.util.List;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.jee.api.integracion.manager.IGenericManager;

public interface ICatalogoMarcas extends IGenericManager<Long, MarcaDto>{

	/**
	 * Método que devuelve todas las marcas de un producto ordenadas por nombre.
	 * 
	 * @return
	 */
	List<MarcaDto> buscarTodas();
		

}
