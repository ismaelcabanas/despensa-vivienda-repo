package org.icabanas.despensa.catalogos.producto.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductoDtoTest {

	private static Validator validator;
	
	@BeforeClass
	public static void configuraTest(){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@Test
	public void no_deberia_validar_producto_si_codigo_es_nulo(){
		// dado un producto con código nulo
		ProductoDto producto = new ProductoDto();
		producto.setCodigo(null);
		producto.setNombre("Leche");
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// debería haber un error
		Assert.assertThat(1, IsEqual.equalTo(errores.size()));
	}
	
	@Test
	public void no_deberia_validar_producto_si_codigo_es_vacio(){
		// dado un producto con código vacío
		ProductoDto producto = new ProductoDto();
		producto.setCodigo("");
		producto.setNombre("Leche");
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// debería haber un error
		Assert.assertThat(1, IsEqual.equalTo(errores.size()));
	}
	
	@Test
	public void no_deberia_validar_producto_si_nombre_es_nulo(){
		// dado un producto con nombre nulo
		ProductoDto producto = new ProductoDto();
		producto.setCodigo("1234");
		producto.setNombre(null);
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// debería haber un error
		Assert.assertThat(1, IsEqual.equalTo(errores.size()));
	}
	
	@Test
	public void no_deberia_validar_producto_si_nombre_es_vacio(){
		// dado un producto con nombre nulo
		ProductoDto producto = new ProductoDto();
		producto.setCodigo("1234");
		producto.setNombre("");
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// debería haber un error
		Assert.assertThat(1, IsEqual.equalTo(errores.size()));
	}
	
	@Test
	public void no_deberia_validar_producto_si_codigo_y_nombre_es_nulo(){
		// dado un producto con código y nombre nulos
		ProductoDto producto = new ProductoDto();
		producto.setCodigo(null);
		producto.setNombre(null);
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// debería haber un error
		Assert.assertThat(2, IsEqual.equalTo(errores.size()));
	}
	
	@Test
	public void deberia_validar_producto(){
		// dado un producto correcto
		ProductoDto producto = new ProductoDto("1234","Leche");
		
		// si valido el producto
		Set<ConstraintViolation<ProductoDto>> errores = validator.validate(producto);
		
		// no debería haber un errores
		Assert.assertThat(0, IsEqual.equalTo(errores.size()));
	}
}
