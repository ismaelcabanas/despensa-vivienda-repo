package org.icabanas.despensa.catalogos.productos;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icabanas.despensa.catalogos.CRUDTest;
import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.catalogos.productos.impl.CatalogoProductosImpl;
import org.icabanas.despensa.modelo.Producto;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoProductoTest extends CRUDTest<ProductoDto, Long, Producto> {

	@Mock
	private IGenericDao<Long, Producto> _mockDao;
	
	private ICatalogoProductos _catalogoProductos;

	
	@Override
	protected IGenericDao<Long, Producto> getMockDao() {
		return _mockDao;
	}

	@Override
	protected IGenericManager<Long, ProductoDto> getServicio() {
		return _catalogoProductos;
	}

	@Override
	protected Map<String, ProductoDto> getTestDtoEsperados() {
		Map<String, ProductoDto> testDtos = new HashMap<String, ProductoDto>();
		
		ProductoDto dtoEsperadoRegistrar = new ProductoDto(1L,"cod-0001", "Leche", new MarcaDto(1L));
		testDtos.put(KEY_DTO_ESPERADO_REGISTRAR, dtoEsperadoRegistrar);
		
		ProductoDto dtoEsperadoModificar = new ProductoDto(1L, "cod-0002", "Yogurt");		
		testDtos.put(KEY_DTO_ESPERADO_MODIFICAR, dtoEsperadoModificar);
		
		ProductoDto dtoEsperadoBusqueda = new ProductoDto(1L, "cod-0003", "Pan");		
		testDtos.put(KEY_DTO_ESPERADO_BUSCAR, dtoEsperadoBusqueda);
		
		return testDtos;
	}

	@Override
	protected Map<String, ProductoDto> getTestDtos() {		
		Map<String, ProductoDto> testDtos = new HashMap<String, ProductoDto>();
		ProductoDto dtoARegistrar = new ProductoDto("cod-0001", "Leche", new MarcaDto(1L));
		testDtos.put(CRUDTest.KEY_DTO_A_REGISTRAR, dtoARegistrar);
		ProductoDto dtoNoValido = new ProductoDto();
		testDtos.put(CRUDTest.KEY_DTO_NO_VALIDO, dtoNoValido);
		ProductoDto dtoAModificar = new ProductoDto(1L, "cod-0002", "Yougur", new MarcaDto(1L));
		testDtos.put(CRUDTest.KEY_DTO_A_MODIFICAR, dtoAModificar);
		ProductoDto dtoNoValidoModificacion = new ProductoDto(1L, "cod-0001");
		testDtos.put(CRUDTest.KEY_DTO_NO_VALIDO_PARA_MODIFICACION, dtoNoValidoModificacion);
		
		return testDtos;
	}
		
	@Override
	protected Map<String, Producto> getTestEntidades() {
		Map<String, Producto> testEntidades = new HashMap<String, Producto>();

		Producto entidadAModificar = new Producto("Yogurt", "cod-0002");
		entidadAModificar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_MODIFICAR, entidadAModificar);
		Producto  entidadModificada = new Producto("Yogurt","cod-0002");
		entidadModificada.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_MODIFICADA,  entidadModificada);
		Producto entidadAEliminar = new Producto();
		entidadAEliminar.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_A_ELIMINAR, entidadAEliminar);
		Producto entidadBusqueda = new Producto("Pan", "cod-0003");
		entidadBusqueda.setId(1L);
		testEntidades.put(CRUDTest.KEY_ENTIDAD_BUSQUEDA, entidadBusqueda);

		return testEntidades;
	}
	
	

	@Override
	protected List<Producto> getTestDatosPaginacion() {
		return productosBusqueda1();
	}

	@Before
	public void configuraTest(){
		_catalogoProductos = new CatalogoProductosImpl(_mockDao);				
	}
		
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_lanzar_excepcion_si_valida_producto_nulo(){
		// preparaci�n
		ProductoDto productoDto = null;		
		
		// ejecuci�n
		try {
			boolean esValido = _catalogoProductos.validar(productoDto);
		} catch (ValidacionException e) {
			fail("Deber�a lanzar una IllegalArgumentException");
		}		
		
		// verificaci�n
		
	}
	
	@Test
	public void deberia_fallar_si_valida_producto_sin_nombre(){
		// preparaci�n
		ProductoDto productoDto = new ProductoDto(1L,"prod-0001");		
		
		// ejecuci�n
		ValidacionException testExcepcion = null;
		try{			
			boolean esValido = _catalogoProductos.validar(productoDto);
		}
		catch (ValidacionException e) {
			testExcepcion = e;
		}
		
		// verificaci�n
		assertThat(testExcepcion, notNullValue());
		assertThat(testExcepcion.getListaExcepciones(), notNullValue());
		assertThat(testExcepcion.getListaExcepciones().size(), equalTo(1));
	}
	
	@Test
	public void deberia_fallar_si_valida_producto_sin_codigo(){
		// preparaci�n
		ProductoDto productoDto = new ProductoDto();
		productoDto.setNombre("Leche");
		
		// ejecuci�n
		ValidacionException testExcepcion = null;
		try{			
			boolean esValido = _catalogoProductos.validar(productoDto);
		}
		catch (ValidacionException e) {
			testExcepcion = e;
		}
		
		// verificaci�n
		assertThat(testExcepcion, notNullValue());
		assertThat(testExcepcion.getListaExcepciones(), notNullValue());
		assertThat(testExcepcion.getListaExcepciones().size(), equalTo(1));
	}
	
	@Test
	public void deberia_validar_producto(){
		// preparaci�n
		ProductoDto productoDto = new ProductoDto(1L,"prod-0001","Leche",new MarcaDto(1L));
		boolean esValido = false;
		
		// ejecuci�n
		try{			
			esValido = _catalogoProductos.validar(productoDto);
		}
		catch (ValidacionException e) {
			fail("No deber�a lanzar excepci�n");
		}
		
		// verificaci�n
		assertThat(esValido, equalTo(true));		
	}			
	

	private List<Producto> productosBusqueda1() {
		List<Producto> productos = new ArrayList<Producto>();
				
		productos.add(creaProducto("Leche","Pascual"));
		productos.add(creaProducto("Leche","Clesa"));
		productos.add(creaProducto("Mahonesa","Ligeresa"));
		productos.add(creaProducto("Salchichas","Mayer"));		
		productos.add(creaProducto("Ketchup","Hacendado"));

		return productos;
	}

	private Producto creaProducto(String nombre, String marca) {
		return new Producto(nombre, marca);
	}
	
}
