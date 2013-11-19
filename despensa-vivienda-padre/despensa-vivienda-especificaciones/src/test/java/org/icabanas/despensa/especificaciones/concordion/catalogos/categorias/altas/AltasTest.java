package org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.altas;

import java.util.ArrayList;
import java.util.List;

import org.concordion.integration.junit4.ConcordionRunner;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.AbstractCatalogoCategoriasTest;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class AltasTest extends AbstractCatalogoCategoriasTest{

	public List<CategoriaDto> registra(String nombreCategoria) throws Exception {
		return registra(nombreCategoria, null);
	}
	
	public List<CategoriaDto> registra(String nombreCategoria, String descripcion) throws Exception {
		List<CategoriaDto> resultado = new ArrayList<CategoriaDto>();
			
		CategoriaDto dto = new CategoriaDto(nombreCategoria, descripcion);
		
		resultado.add(catalogoCategorias.registrar(dto));
		
		return resultado;
	}

}
