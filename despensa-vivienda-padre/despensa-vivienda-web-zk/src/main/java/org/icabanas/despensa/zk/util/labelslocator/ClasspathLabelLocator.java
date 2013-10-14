package org.icabanas.despensa.zk.util.labelslocator;

import java.io.InputStream;
import java.util.Locale;

import org.zkoss.util.resource.LabelLocator2;

public class ClasspathLabelLocator implements LabelLocator2 {

	/**
	 * Ruta del fichero de propiedades.
	 */
	private String fichero;

	public ClasspathLabelLocator(String fichero) {
		this.fichero = fichero;
	}

	@Override
	public String getCharset() {
		return "UTF-8";
	}

	@Override
	public InputStream locate(Locale locale) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fichero);
		return is;
	}

}
