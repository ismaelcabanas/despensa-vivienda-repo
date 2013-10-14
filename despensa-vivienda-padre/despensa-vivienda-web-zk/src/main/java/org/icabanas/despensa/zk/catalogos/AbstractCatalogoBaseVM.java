package org.icabanas.despensa.zk.catalogos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icabanas.despensa.web.actions.catalogos.ICatalogoAction;
import org.icabanas.despensa.zk.util.validator.DespensaValidator;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dto.BaseDto;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ErrorValidacion;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Div;

public abstract class AbstractCatalogoBaseVM<ID extends Serializable, T extends BaseDto<ID>> implements ICatalogoVM {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractCatalogoBaseVM.class);
	
	private List<ErrorValidacion> errores;
	
	private Validator validador;
			
	@Init
	@NotifyChange({"filtro","pagina"})
	public void inicializa(@BindingParam(value="accion") String accion, @ExecutionArgParam("parametros") Object parametros){
		if(accion != null){			
			if(accion.equalsIgnoreCase("busqueda")){
				if(parametros != null){
					Object[] param = ((Object[]) parametros);
					IFiltro filtro = (IFiltro) param[0];
					setFiltro(filtro);
//					try{
//						this.mensajeUsuario = (Mensaje) ((Object[]) parametros)[1];
//					}
//					catch(ArrayIndexOutOfBoundsException e){}
				}
				getAction().busqueda();				
			}
			else if(accion.equalsIgnoreCase("alta")){
				if(parametros != null){
					IFiltro filtro = (IFiltro) ((Object[]) parametros)[0];
					//setSeleccionado(new ProductoDto());
					setFiltro(filtro);
				}
				getAction().alta();
			}
			else if(accion.equalsIgnoreCase("detalle") || accion.equalsIgnoreCase("edicion")){
				T dto = null;
				if(parametros != null){
					dto = (T) ((Object[]) parametros)[0];
					IFiltro filtro = (IFiltro) ((Object[]) parametros)[1];
					setFiltro(filtro);
					setSeleccionado(dto);
				}
				try {
					getAction().editar();
				} catch (NoExisteEntidadException e) {
					// TODO Gestionar excepción
					e.printStackTrace();
				}
			}
		}
	}	
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#nuevo()
	 */
	@Override
	@Command
	public void alta(){
		logger.debug("Nueva entidad");
		// se carga el zul de alta de un producto
		cargarZul(getZulAlta(),cargaParametros(getFiltro()));				
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#buscar()
	 */
	@Override
	@Command
	@NotifyChange({"pagina"})
	public void buscar(){
		logger.debug("Búsqueda de entidades por criterio de búsqueda " + getAction().getFiltro());
		
		// realizo la búsqueda paginada
		getAction().paginar(1);
				
	}

	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#limpiar()
	 */
	@Override
	@Command
	@NotifyChange({"filtro"})
	public void limpiar(){
		logger.debug("Limpiado del formulario de búsqueda");
		
		// limpio el dto
		getAction().limpiarFiltro();
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#guardar()
	 */
	@Override
	@Command
	@NotifyChange("errores")
	public void guardar(){
		logger.debug("Guardando la entidad " + getAction().getDto());
		
		try {
			getAction().guardar();
						
			// cargo la página de búsqueda
			cargarZul(getZulBusqueda(), cargaParametros(getFiltro()));
		} catch (RegistrarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
		}		
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#editar()
	 */
	@Override
	@Command
	public void editar(){				
		cargarZul(getZulEdicion(), "edicion", cargaParametros(getSeleccionado(),getFiltro()));
	}
	
	@Override
	@Command
	public void detalle(){				
		cargarZul(getZulEdicion(), "detalle", cargaParametros(getSeleccionado(),getFiltro()));
	}		

	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#modificar()
	 */
	@Override
	@Command
	@NotifyChange("errores")
	public void actualizar(){
		logger.debug("Actualizando el producto " + getAction().getDto());
		
		try {
			getAction().actualizar();
		} catch (ModificarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
		} catch(NoExisteEntidadException ex){
			// TODO Gestionar la excepción
		}
				
		// cargo la página de búsqueda
		cargarZul(getZulBusqueda(), cargaParametros(getFiltro()));
	}
	
	/* (non-Javadoc)
	 * @see org.icabanas.despensa.zk.catalogos.productos.ICatalogoBaseVM#eliminar()
	 */
	@Override
	@Command
	@NotifyChange({"pagina","errores"})
	public void eliminar(){
		// elimino la entidad seleccionada
		try {
			getAction().eliminar();
		} catch (NoExisteEntidadException e) {
			// TODO Gestionar la excepción
			e.printStackTrace();
		} catch (EliminarException e) {
			tratarErroresValidacion(e.getListaExcepciones());
		}
		
		// vuelvo a realizar la consulta y dejarlo en la primera página
		paginar(0);
	}
	
	@Command
	@NotifyChange({"pagina"})
	public void paginar(@BindingParam("paginaActiva") int paginaActiva){
		logger.debug("Mostrando la página " + paginaActiva + " del resultado de la búsqueda.");
		
		// realizo la paginación. Tengo que sumar 1 a la página a mostrar ya que el componente de paginación de ZK
		// empieza en 0
		getAction().paginar(paginaActiva+1);		
	}

	@Override
	@Command
	public void cancelar() {
		// se cancela la acción
		getAction().cancelar();
		
		// se carga el ZUL de la búsqueda
		cargarZul(getZulBusqueda(), cargaParametros(getFiltro()));
	}

	@Override
	@Command
	public void volver() {
		cargarZul(getZulBusqueda(), cargaParametros(getFiltro()));
	}
	
	public Pagina<T> getPagina(){
		return getAction().getPagina();
	}
	
	/**
	 * Obtiene la página zul de alta.
	 * 
	 * @return
	 */
	protected abstract String getZulAlta();
	
	/**
	 * Obtiene la página zul de búsqueda.
	 * 
	 * @return
	 */
	protected abstract String getZulBusqueda();
	
	/**
	 * Obtiene la página zul de edición.
	 * 
	 * @return
	 */
	protected abstract String getZulEdicion();

	/**
	 * Instancia del action.
	 * 
	 * @return
	 */
	protected abstract ICatalogoAction<ID, T> getAction();
	
	/**
	 * Es necesario crear los métodos getter y setter para la variable filtro para que 
	 * funcione la anotación @NotifyChange
	 * 
	 * @return
	 */
	public IFiltro getFiltro() {
		return getAction().getFiltro();
	}

	public void setFiltro(IFiltro filtro) {
		getAction().setFiltro(filtro);
	}

	public T getSeleccionado() {
		return getAction().getDto();
	}
	
	public void setSeleccionado(T seleccionado){
		getAction().setDto(seleccionado);
	}		


	public List<ErrorValidacion> getErrores() {
		return errores;
	}
	
	public Validator getValidador() {
		validador = new DespensaValidator();
		return validador;
	}
	

	public void setValidador(Validator validador) {
		this.validador = validador;
	}

	/**
	 * Carga un ZUL en el área de contenido pasándole ciertos parámetros e indicando 
	 * el modo de la página, detalle o edición.
	 * 
	 * @param pagina
	 * @param modo
	 * @param parametros
	 */
	protected void cargarZul(String pagina, String modo, Object[] parametros){
		logger.debug("Cargando el zul " + pagina);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		
//		Borderlayout borderLayout = (Borderlayout) Path.getComponent("/ventanaPrincipal/borderLayoutPrincipal");
//		
//		/* obtengo una instancia del área central donde va el contenido */
//		Center c1 = ((Borderlayout) borderLayout .getCenter().getChildren().get(0)).getCenter();
//
//		/* limpio el área central de cualquier componente que tenga */
//		c1.getChildren().clear();
		
		Div divContenido = (Div) Path.getComponent("/contenido");
		Div divContenidoForm = (Div) divContenido.getFellow("contenido-form");
		divContenidoForm.getChildren().clear();
		
		map.put("areaCentral", divContenidoForm);
		if(modo != null){
			map.put("modo", modo);
		}
		if(parametros != null){
			map.put("parametros", parametros);
		}

		/* cargo el zul */
		Executions.createComponents(pagina, divContenidoForm, map);
	}

	/**
	 * Carga un ZUL en el área de contenido.
	 * 
	 * @param pagina
	 * 		El fichero ZUL a cargar.
	 */
	protected void cargarZul(String pagina){
		cargarZul(pagina, null, null);
	}
	
	/**
	 * Carga un ZUL en el área de contenido pasándole ciertos parámetros.
	 * 
	 * @param pagina
	 * 		El ZUL a cargar
	 * @param parametros
	 * 		Los parámetros
	 */
	protected void cargarZul(String pagina, Object[] parametros){
		cargarZul(pagina, null, parametros);
	}
	
	/**
	 * Método que trata una lista de errores.
	 * 
	 * @param listaErrores
	 */
	protected void tratarErroresValidacion(final List<ErrorValidacion> listaErrores) {
//		if(listaErrores != null){
//			Iterator<ErrorValidacion> iterador = listaErrores.iterator();
//			while(iterador.hasNext()){
//				ErrorValidacion errorValidacion = iterador.next();
//				// TODO tratar errores
//				//addFieldError(errorValidacion.getCampo(), Labels.getLabel(errorValidacion.getMensaje()));
//			}		
//		}
		this.errores = listaErrores;
		
		// por cada uno de los errores, los traduzco
		for (ErrorValidacion errorValidacion : this.errores) {
			errorValidacion.setMensaje(Labels.getLabel(errorValidacion.getClaveMensaje()));
		}
	}
	
	protected Object[] cargaParametros(Object... parametros){				
		return parametros;
	}
}
