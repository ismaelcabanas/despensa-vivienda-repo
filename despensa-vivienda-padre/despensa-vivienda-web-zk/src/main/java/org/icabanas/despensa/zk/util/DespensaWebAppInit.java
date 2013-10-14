package org.icabanas.despensa.zk.util;

import org.icabanas.despensa.zk.util.labelslocator.ClasspathLabelLocator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

public class DespensaWebAppInit implements WebAppInit {

	@Override
	public void init(WebApp webapp) throws Exception {
		// cuando arrancamos la aplicaci�n registramos los ficheros de propiedades a utilizar por la aplicaci�n
		Labels.register(new ClasspathLabelLocator("global.properties"));
		Labels.register(new ClasspathLabelLocator("org/icabanas/despensa/web/actions/catalogos/productos/messages.properties"));
		Labels.register(new ClasspathLabelLocator("org/icabanas/despensa/web/actions/catalogos/marcas/messages.properties"));
		Labels.register(new ClasspathLabelLocator("org/icabanas/despensa/web/actions/catalogos/categorias/messages.properties"));
	}

}
