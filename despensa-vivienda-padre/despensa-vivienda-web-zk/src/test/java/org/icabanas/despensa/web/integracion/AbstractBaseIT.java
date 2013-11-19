package org.icabanas.despensa.web.integracion;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.icabanas.despensa.util.FicheroConfiguracion;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.CompositeDataSet;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSet;
//import org.dbunit.operation.DatabaseOperation;
import org.icabanas.despensa.web.integracion.actions.pages.ErrorPage;
import org.icabanas.despensa.web.integracion.actions.pages.PageException;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBaseIT {

	protected static String URI_WEB_APP = null;
	private static final String ficheroTest = "test.properties";
	private static final Logger logger = LoggerFactory.getLogger(AbstractBaseIT.class);
	
//	private IDataSet[] datasets = null;
	
	static{
		try {
			URI_WEB_APP = FicheroConfiguracion.getPropiedad(ficheroTest, "test.selenium.host") + 
					FicheroConfiguracion.getPropiedad(ficheroTest, "test.selenium.webContext");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected WebDriver driver;
	protected Properties props;
//	private IDatabaseConnection connection = null;
	
	public AbstractBaseIT() {
		super();
		
	}

	@Before
	public void iniciarTest() throws Exception{		
        // inicio el driver
		driver = new HtmlUnitDriver(true);
		
		// cargo el fichero de propiedades adecuado			
		try {
			props = cargarFicherosPropiedades(getFicheroPropiedades());
			//props.load(getClass().getResourceAsStream(getFicheroPropiedades()));
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		// cargo datos con DBUnit si fuese necesario
//		if(getDatasets() != null && getDatasets().length > 0){
//			connection = getConexion();		
//			String[] rutasDatasets = getDatasets();
//			datasets = new IDataSet[rutasDatasets.length];
//			for (int i = 0;i < datasets.length;i++) {
//				IDataSet datasetTemp = new FlatXmlDataSet(new InputSource(this.getClass().getResourceAsStream(rutasDatasets[i])));
//				datasets[i] = datasetTemp;
//			}
//			IDataSet dataset = new CompositeDataSet(datasets);
//			
//			DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
//		}
	}		

	private Properties cargarFicherosPropiedades(String[] ficheroPropiedades) throws IOException {
		Properties props = new Properties();
		for (String fichero : ficheroPropiedades) {
			Properties propsTemp = new Properties();
			propsTemp.load(new InputStreamReader(getClass().getResourceAsStream(fichero), "UTF-8"));
			props.putAll(propsTemp);
		}
		return props;
	}

//	private IDatabaseConnection getConexion() throws Exception {
//		Class driverClass = Class.forName(FicheroConfiguracion.getPropiedad("db.properties", "db.driverClassName"));
//        Connection jdbcConnection = DriverManager.getConnection(
//        		FicheroConfiguracion.getPropiedad("db.properties", "db.url"), 
//        		FicheroConfiguracion.getPropiedad("db.properties", "db.username"), 
//        		FicheroConfiguracion.getPropiedad("db.properties", "db.password"));
//        return new DatabaseConnection(jdbcConnection);
//	}

	@After
	public void finalizarTest() throws Exception{
		// cierro el driver
		driver.quit();
		
		// elimino los datos introducidos por DBUnit y cierro la conexión dbunit
		//DatabaseOperation.DELETE.execute(connection, dataset);
//		if(connection != null){
////			if(datasets != null){
////				for(int i = datasets.length-1; i >= 0; i--){
////					DatabaseOperation.DELETE.execute(connection, datasets[i]);
////				}
////			}			
//			connection.close();
//		}
	}
	
	/**
	 * @return Los ficheros de propiedades necesarios para el test.
	 */
	protected abstract String[] getFicheroPropiedades();
	
	/**
	 * 
	 * @return Los datasets necesarios para el test.
	 */
	protected abstract String[] getDatasets();
	
	protected void paginaErroresCorrecta(final ErrorPage page) throws PageException{		
		// el título de la página es el esperado
		assertThat(page.getTituloPagina(), equalTo(props.getProperty("error.titulo")));		
	}
}
