package org.icabanas.despensa.zk.catalogos.marcas;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.web.actions.catalogos.ICatalogoAction;
import org.icabanas.despensa.zk.catalogos.AbstractCatalogoBaseVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CatalogoMarcasVM extends AbstractCatalogoBaseVM<Long, MarcaDto> {

	private static final Logger logger = LoggerFactory.getLogger(CatalogoMarcasVM.class);
	
	private static final String ZUL_ALTA = "/WEB-INF/zul/catalogos/marcas/alta.zul";

	private static final String ZUL_BUSQUEDA = "/WEB-INF/zul/catalogos/marcas/busqueda.zul";
	
	private static final String ZUL_EDICION = "/WEB-INF/zul/catalogos/marcas/edicion.zul";

	@WireVariable
	protected ICatalogoAction<Long, MarcaDto> catalogoMarcasAction;
	
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
	protected ICatalogoAction<Long, MarcaDto> getAction() {
		return catalogoMarcasAction;
	}

//	@Init
//	public void inicializa(@BindingParam(value="accion") String accion, @ExecutionArgParam("parametros") Object parametros){
//		super.inicializa(accion, parametros);		
//	}

	public ICatalogoAction<Long, MarcaDto> getCatalogoMarcasAction() {
		return catalogoMarcasAction;
	}

	public void setCatalogoMarcasAction(
			ICatalogoAction<Long, MarcaDto> catalogoMarcasAction) {
		this.catalogoMarcasAction = catalogoMarcasAction;
	}	
	
	@Init
	public void inicializa(@BindingParam(value="accion") String accion, @ExecutionArgParam("parametros") Object parametros){
		super.inicializa(accion, parametros);		
	}
}
