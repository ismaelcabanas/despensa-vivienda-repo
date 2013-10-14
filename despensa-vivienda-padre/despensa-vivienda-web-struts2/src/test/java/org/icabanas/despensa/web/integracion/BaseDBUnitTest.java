package org.icabanas.despensa.web.integracion;

import org.apache.commons.lang.NotImplementedException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.icabanas.despensa.util.FicheroConfiguracion;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase base que inicializa DBUnit.
 * 
 * Nota: Al usar DBUnit, las anotaciones @Test no funciona. Los métodos a testear deben comenzar con test
 * 
 * @author f009994r
 *
 */
public abstract class BaseDBUnitTest extends DBTestCase {

	private static final Logger logger = LoggerFactory.getLogger(BaseDBUnitTest.class);
	
	private final String ficheroDB = "db.properties";
	private static final String ficheroTest = "test.properties";
	protected static WebDriver driver;
	protected static String URI_WEB_APP = null;
	
	static{
		try {
			URI_WEB_APP = FicheroConfiguracion.getPropiedad(ficheroTest, "test.selenium.host") + 
					FicheroConfiguracion.getPropiedad(ficheroTest, "test.selenium.webContext");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
		
	public BaseDBUnitTest() {
		super();
		
		try{
			// inicializo DBUnit
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, FicheroConfiguracion.getPropiedad(ficheroDB, "db.url"));
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, FicheroConfiguracion.getPropiedad(ficheroDB, "db.username"));
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, FicheroConfiguracion.getPropiedad(ficheroDB, "db.password"));
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, FicheroConfiguracion.getPropiedad(ficheroDB, "db.driverClassName"));
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "PUBLIC");						
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}	
	

	@Override
	protected IDataSet getDataSet() throws Exception {
		throw new NotImplementedException("Hay que especificar el conjunto de datos del test para: " + this.getClass().getSimpleName());
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE;
	}
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		driver = new HtmlUnitDriver(true);
		// accedemos a la página inicial, pues se definen el fichero de propiedades global que lo establece 
		// en el application
		logger.debug("URL de la aplicación a testear: " + URI_WEB_APP);
		driver.get(URI_WEB_APP);
		iniciarTest();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		driver.quit();
	}
	
	protected abstract void iniciarTest();
	
//	@AfterClass
//	public static void despuesDeTodo(){
//		driver.quit();
//	}

	
}
