package org.icabanas.despensa.web.actions.catalogos.productos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.marcas.ICatalogoMarcas;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoFiltro;
import org.icabanas.despensa.web.actions.catalogos.AbstractCatalogoAction;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogoProductosAction extends AbstractCatalogoAction<Long, ProductoDto, ProductoFiltro> {

	private static final Logger logger = LoggerFactory.getLogger(CatalogoProductosAction.class);
	
	private List<MarcaDto> marcas;
	
	private ICatalogoMarcas catalogoMarcas;
	
	// TODO Recuperar de forma elegante el texto
	private static final MarcaDto MARCA_COMBO_POR_DEFECTO = new MarcaDto(null, "Seleccione una opción");
	
	public CatalogoProductosAction(IGenericManager<Long, ProductoDto> catalogoManager,
			ICatalogoMarcas catalogoMarcas) {
		super(catalogoManager);
		this.catalogoMarcas = catalogoMarcas;
	}
//	public CatalogoProductosAction() {
//		setFiltro(new ProductoFiltro("productoFiltro"));
//	}
	
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.ICatalogoAction#alta()
	 */
	@Override
	public void alta() {
		super.alta();
		marcas = catalogoMarcas.buscarTodas();
		//dto = new ProductoDto();
		dto.setMarca(MARCA_COMBO_POR_DEFECTO);
	}
			

	@Override
	public void busqueda() {
		super.busqueda();
		marcas = catalogoMarcas.buscarTodas();		
	}

	@Override
	public void limpiarFiltro() {
		super.limpiarFiltro();		
		((ProductoFiltro) getFiltro()).setMarca(MARCA_COMBO_POR_DEFECTO);
	}

	@Override
	protected void inicializaFiltroDeBusqueda() {
		ProductoFiltro filtro = (ProductoFiltro) getFiltro();
		filtro.setMarca(MARCA_COMBO_POR_DEFECTO);
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.ICatalogoAction#editar()
	 */
	@Override
	public void editar(Long id) throws NoExisteEntidadException {
		super.editar(id);
		
		marcas = catalogoMarcas.buscarTodas();
	}				

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.web.actions.catalogos.productos.ICatalogoAction#paginar()
	 */
	@Override
	public void paginar(final HttpServletRequest request) {
		super.paginar(request);		
	}
	
	public List<MarcaDto> getMarcas() {
		return marcas;
	}

	protected void setCatalogoMarcas(ICatalogoMarcas catalogosMarcas) {
		this.catalogoMarcas = catalogosMarcas;
	}		

	protected ICatalogoMarcas getCatalogoMarcas() {
		return catalogoMarcas;
	}
		
	public ProductoDto getProducto() {
		return getDto();
	}
	
	public void setProducto(ProductoDto dto){
		setDto(dto);
	}
	
	
	
	
}
