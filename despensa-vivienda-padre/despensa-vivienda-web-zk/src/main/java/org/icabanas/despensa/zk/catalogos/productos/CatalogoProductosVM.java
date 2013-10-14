package org.icabanas.despensa.zk.catalogos.productos;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.web.actions.catalogos.ICatalogoAction;
import org.icabanas.despensa.web.actions.catalogos.productos.CatalogoProductosAction;
import org.icabanas.despensa.zk.catalogos.AbstractCatalogoBaseVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 * <br/><br/>
 * <b>Responsabilidad</b>: Se encarga de la vista de la gestión de productos.    
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
public class CatalogoProductosVM extends AbstractCatalogoBaseVM<Long, ProductoDto>{

	private static final Logger logger = LoggerFactory.getLogger(CatalogoProductosVM.class);
	
	private static final String ZUL_ALTA = "/WEB-INF/zul/catalogos/productos/alta.zul";

	private static final String ZUL_BUSQUEDA = "/WEB-INF/zul/catalogos/productos/busqueda.zul";
	
	private static final String ZUL_EDICION = "/WEB-INF/zul/catalogos/productos/edicion.zul";

	private static final MarcaDto MARCA_COMBO_POR_DEFECTO = new MarcaDto(null, Labels.getLabel("option.default"));		
		
	@WireVariable
	protected ICatalogoAction<Long, ProductoDto> catalogoProductosAction;
			
	@Override
	protected ICatalogoAction<Long, ProductoDto> getAction() {
		return catalogoProductosAction;
	}

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

	@Init
	public void inicializa(@BindingParam(value="accion") String accion, @ExecutionArgParam("parametros") Object parametros){
		super.inicializa(accion, parametros);		
	}	



	public ICatalogoAction<Long, ProductoDto> getCatalogoProductosAction() {
		return catalogoProductosAction;
	}

	public void setCatalogoProductosAction(
			ICatalogoAction<Long, ProductoDto> catalogoProductosAction) {
		this.catalogoProductosAction = catalogoProductosAction;
	}
	
	
	public List<MarcaDto> getMarcas(){
		List<MarcaDto> marcasConOpcionPorDefecto = new ArrayList<MarcaDto>();
		marcasConOpcionPorDefecto.add(MARCA_COMBO_POR_DEFECTO);
		CatalogoProductosAction cpa = (CatalogoProductosAction) catalogoProductosAction;
		marcasConOpcionPorDefecto.addAll(cpa.getMarcas());		
		return marcasConOpcionPorDefecto;
	}		
	
}
