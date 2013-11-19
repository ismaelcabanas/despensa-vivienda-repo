package org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.bajas;

import java.util.ArrayList;
import java.util.List;

import org.concordion.integration.junit4.ConcordionRunner;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaFiltro;
import org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.AbstractCatalogoCategoriasTest;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class BajasTest extends AbstractCatalogoCategoriasTest {

	public List<CategoriaDto> baja(String nombreCategoria) throws Exception {
		List<CategoriaDto> resultado = new ArrayList<CategoriaDto>();
		
		// categoría a eliminar
		CategoriaDto dto = new CategoriaDto(nombreCategoria);
				
		// busco la categoría
		CategoriaFiltro filtro = new CategoriaFiltro();
		filtro.setNombreFiltro("categoriaFiltro");
		filtro.setNombre(nombreCategoria);
		List<CategoriaDto> categorias = catalogoCategorias.buscar(filtro);
		if(categorias == null || categorias.size() > 1){
			throw new Exception("No existe categoría o existe más de una para la categoría " + nombreCategoria);
		}
		CategoriaDto cat = categorias.get(0);
		
		// doy de baja la categoría
		catalogoCategorias.eliminar(cat.getId());
				
		resultado = catalogoCategorias.buscarTodas();
		
		return resultado;
	}
}
