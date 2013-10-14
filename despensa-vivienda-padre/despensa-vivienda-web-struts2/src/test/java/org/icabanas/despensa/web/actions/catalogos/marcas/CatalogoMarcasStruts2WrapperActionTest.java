package org.icabanas.despensa.web.actions.catalogos.marcas;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.icabanas.despensa.catalogos.marca.dto.MarcaDto;
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
public class CatalogoMarcasStruts2WrapperActionTest extends BaseStrutsTestCase{

	@Mock
	private CatalogoMarcasAction _mockActionWrapped;
	
	@Test
	public void deberia_devolver_accion_correccto_si_alta_de_marcas() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = 
			createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "alta");			
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String result = action.alta();
		
		// verificación
		Mockito.verify(_mockActionWrapped).alta();
		assertEquals(Constantes.ACTION_CORRECTO, result);
	} 
	
	@Test
	public void deberia_devolver_accion_correccto_si_cancelar_accion() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "cancelar");
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.cancelar();
		
		// verificación
		Mockito.verify(_mockActionWrapped).cancelar();
		assertThat(resultado, equalTo(Constantes.ACTION_CORRECTO));
	}

	@Test
	public void deberia_devolver_accion_correcto_si_edita_producto() throws Exception{
		// preparación
		Long idMarcaSeleccionada = 1L;
		MarcaDto marcaEdicion = new MarcaDto(idMarcaSeleccionada, "Pascual");
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "editar");
		action.setWpAccion(_mockActionWrapped);
		Mockito.when(_mockActionWrapped.getMarca()).thenReturn(marcaEdicion);
		
		// ejecución		
		String resultado = action.editar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).editar(idMarcaSeleccionada);
		assertThat(resultado, equalTo(Constantes.ACTION_CORRECTO));
	}
	
	@Test
	public void deberia_devolver_accion_listado_si_edita_y_no_selecciona_producto() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "editar");
		action.setWpAccion(_mockActionWrapped);
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
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "eliminar");
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.eliminar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).eliminar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}
	
	@Test
	public void deberia_devolver_accion_listado_al_guardar_un_producto() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "guardar");
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.guardar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).guardar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));
	}
	
	@Test
	public void deberia_devolver_accion_input_cuando_guarda_una_marca_y_se_produce_excepcion_de_validacion() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "guardar");		
		action.setWpAccion(_mockActionWrapped);
		
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
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "actualizar");
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}
	
	@Test
	public void deberia_devolver_accion_input_al_modificar_marca_si_se_produce_una_excepcion_de_validacion() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "actualizar");		
		action.setWpAccion(_mockActionWrapped);
		Mockito.doThrow(new ModificarException()).when(_mockActionWrapped).actualizar();
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_INPUT));
	}
	
	@Test
	public void deberia_devolver_accion_listado_al_modificar_marca_si_no_existe_marca() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "actualizar");		
		action.setWpAccion(_mockActionWrapped);
		Mockito.doThrow(new NoExisteEntidadException()).when(_mockActionWrapped).actualizar();
		
		// ejecución
		String resultado = action.actualizar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).actualizar();
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));
	}	
	
	@Test
	public void deberia_devolver_accion_listado_al_buscar_marcas() throws Exception{
		// preparación
		CatalogoMarcasStruts2WrapperAction action = createAction(CatalogoMarcasStruts2WrapperAction.class, "/catalogos/marcas", "paginar");
		action.setWpAccion(_mockActionWrapped);
		
		// ejecución
		String resultado = action.paginar();
				
		// verificación
		Mockito.verify(_mockActionWrapped).paginar(request);
		assertThat(resultado, equalTo(Constantes.ACTION_LISTADO));		
	}
}
