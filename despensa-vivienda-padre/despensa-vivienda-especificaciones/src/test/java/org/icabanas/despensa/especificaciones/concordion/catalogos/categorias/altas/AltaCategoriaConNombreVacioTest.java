package org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.altas;

import org.concordion.integration.junit4.ConcordionRunner;
import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.especificaciones.concordion.catalogos.categorias.AbstractCatalogoCategoriasTest;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class AltaCategoriaConNombreVacioTest extends AbstractCatalogoCategoriasTest{	

	public boolean registra(String nombreCategoria) {
		boolean resultado = true;
				
		CategoriaDto dto = new CategoriaDto(nombreCategoria);
		
		try {
			catalogoCategorias.registrar(dto);
		} catch (RegistrarException e) {
			resultado = false;
		}
		
		return resultado;
	}
		
}
