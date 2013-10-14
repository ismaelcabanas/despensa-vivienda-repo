package org.icabanas.despensa.web.actions.catalogos.productos;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
import org.icabanas.despensa.catalogos.producto.dto.ProductoDto;
import org.icabanas.despensa.util.Constantes;
import org.icabanas.despensa.web.BaseStrutsTestCase;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogoProductosStruts2WrapperActionTest extends BaseStrutsTestCase {

	@Mock
	private CatalogoProductosAction _mockActionWrapped;
	
	@Test
	public void deberia_devolver_accion_correccto_si_alta_de_productos() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = 
			createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "alta");			
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String result = action.alta();
		
		// verificación
		Mockito.verify(_mockActionWrapped).alta();
		assertEquals(Constantes.ACTION_CORRECTO, result);
	}
	
	@Test
	public void deberia_devolver_accion_correccto_si_cancelar_accion() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "cancelar");
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.cancelar();
		
		// verificación
		Mockito.verify(_mockActionWrapped).cancelar();
		assertThat(resultado, equalTo(Constantes.ACTION_CORRECTO));
	}
	
	@Test
	public void deberia_devolver_accion_correcto_si_edita_producto() throws Exception{
		// preparación
		Long idProductoSeleccionado = 1L;
		ProductoDto productoEdicion = new ProductoDto(idProductoSeleccionado, "cod1", "Leche", new MarcaDto(1L,"Hacendado"));
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "editar");
		action.setAccion(_mockActionWrapped);
		Mockito.when(_mockActionWrapped.getProducto()).thenReturn(productoEdicion);
		
		// ejecución		
		String resultado = action.editar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).editar(idProductoSeleccionado);
		assertThat(resultado, equalTo(Constantes.ACTION_CORRECTO));
	}
	
	@Test
	public void deberia_devolver_accion_listado_si_edita_y_no_selecciona_producto() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "editar");
		action.setAccion(_mockActionWrapped);
		NoExisteEntidadException ex = new NoExisteEntidadException();
		Mockito.doThrow(ex).when(_mockActionWrapped).editar(Mockito.anyLong());
		
		// ejecución
		String resultado = action.editar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).editar(Mockito.anyLong());
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));
	}
	
	@Test
	public void deberia_devolver_accion_listado_al_eliminar_un_producto() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "eliminar");
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.eliminar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).eliminar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}
	
	@Test
	public void deberia_devolver_accion_listado_al_guardar_un_producto() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "guardar");
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.guardar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).guardar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));
	}
	
	@Test
	public void deberia_devolver_accion_input_cuando_guarda_un_producto_y_se_produce_excepcion_de_validacion() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "guardar");		
		action.setAccion(_mockActionWrapped);
		
		ValidacionException validacionEx = new ValidacionException();
		validacionEx.anade("prueba", "prueba");
		RegistrarException excepcion = new RegistrarException(validacionEx);
		Mockito.doThrow(excepcion).when(_mockActionWrapped).guardar();
		
		// ejecución
		String resultado = action.guardar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).guardar();
		assertThat(resultado, equalTo(Constantes.ACTION_INPUT));
	}	
	
	@Test
	public void deberia_devolver_accion_listado_al_modificar_producto() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "actualizar");
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}
	
	@Test
	public void deberia_devolver_accion_input_al_modificar_producto_si_se_produce_una_excepcion_de_validacion() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "actualizar");		
		action.setAccion(_mockActionWrapped);
		Mockito.doThrow(new ModificarException()).when(_mockActionWrapped).actualizar();
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_INPUT));
	}	
	
	@Test
	public void deberia_devolver_accion_listado_al_modificar_producto_si_no_existe_producto() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "actualizar");		
		action.setAccion(_mockActionWrapped);
		Mockito.doThrow(new NoExisteEntidadException()).when(_mockActionWrapped).actualizar();
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));
	}	
	
	@Test
	public void deberia_devolver_accion_listado_al_buscar_productos() throws Exception{
		// preparación
		CatalogoProductosStruts2WrapperAction action = createAction(CatalogoProductosStruts2WrapperAction.class, "/catalogos/producto", "paginar");
		action.setAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.paginar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).paginar(request);
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}	
}
