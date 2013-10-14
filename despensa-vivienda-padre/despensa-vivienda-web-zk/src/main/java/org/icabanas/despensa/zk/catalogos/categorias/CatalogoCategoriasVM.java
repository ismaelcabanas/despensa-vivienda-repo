package org.icabanas.despensa.zk.catalogos.categorias;

import org.icabanas.despensa.catalogos.categoria.dto.CategoriaDto;
import org.icabanas.despensa.web.actions.catalogos.ICatalogoAction;
import org.icabanas.despensa.zk.catalogos.AbstractCatalogoBaseVM;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 * <br/><br/>
 * <b>Responsabilidad</b> :  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CatalogoCategoriasVM extends AbstractCatalogoBaseVM<Long, CategoriaDto> {

	private static final String ZUL_ALTA = "/WEB-INF/zul/catalogos/categorias/alta.zul";

	private static final String ZUL_BUSQUEDA = "/WEB-INF/zul/catalogos/categorias/busqueda.zul";
	
	private static final String ZUL_EDICION = "/WEB-INF/zul/catalogos/categorias/edicion.zul";

	@WireVariable
	private ICatalogoAction<Long, CategoriaDto> catalogoCategoriasAction;
	
	@Override
	protected String getZulAlta() {
		return ZUL_ALTA;
	}

	@Override
	protected String getZulBusqueda() {
		return ZUL_BUSQUEDA;
	}

	@Override
	protected String getZulEdicion() {
		return ZUL_EDICION;
	}

	@Override
	protected ICatalogoAction<Long, CategoriaDto> getAction() {
		return catalogoCategoriasAction;
	}

	@Init
	public void inicializa(@BindingParam(value="accion") String accion, @ExecutionArgParam("parametros") Object parametros){
		super.inicializa(accion, parametros);		
	}
	
}
