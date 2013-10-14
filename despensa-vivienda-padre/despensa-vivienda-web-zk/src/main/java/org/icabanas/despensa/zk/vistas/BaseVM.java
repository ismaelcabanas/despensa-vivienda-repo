package org.icabanas.despensa.zk.vistas;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

public class BaseVM {

	private static final Logger logger = LoggerFactory.getLogger(BaseVM.class);
	
//	@Wire(value="#areaContenido")
//	private Borderlayout borderLayout;
	/**
	 * Este método se llamará después que se haya realizado la composición de los componentes.
	 * 
	 * @param view
	 *            Root Component del ZUL.
	 */

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component vista) {
		logger.debug("Iniciando MenuVM");		
		
		/* Injecto los componentes al controlador */
		Selectors.wireComponents(vista, this, false);

//		/* obtengo una instancia del área central donde va el contenido */
//		Center c1 = borderLayout.getCenter();
//
//		/* limpio el área central de cualquier componente que tenga */
//		c1.getChildren().clear();
//
//		map.put("centerArea", c1);
//
//		/* Load the left navigation menu for patient cases */
//		Executions.createComponents("/WEB-INF/zul/home.zul", c1, map);		
	}
	
	@Command
	public void cargarZul(@BindingParam("pagina") String pagina){
		logger.debug("Cargando el zul " + pagina);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		
		//Borderlayout borderLayout = (Borderlayout) Path.getComponent("/ventanaPrincipal/borderLayoutPrincipal");
		Div divContenido = (Div) Path.getComponent("/contenido");
		
		if(divContenido == null){
			divContenido = (Div) Path.getComponent("/contenido-form");
		}
		
		/* obtengo una instancia del área central donde va el contenido */
		//Center c1 = ((Borderlayout) borderLayout .getCenter().getChildren().get(0)).getCenter();
		//Div include = (Div) divContenido.getChildren().get(0);

		/* limpio el área central de cualquier componente que tenga */
		//c1.getChildren().clear();
		divContenido.getChildren().clear();

		//map.put("areaCentral", c1);
		map.put("areaCentral", divContenido);

		/* cargo el zul */
		//Executions.createComponents(pagina, c1, map);
		Executions.createComponents(pagina, divContenido, map);
	}
}
