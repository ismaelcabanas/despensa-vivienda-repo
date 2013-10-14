package org.icabanas.despensa.zk.util.validator;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.validator.AbstractValidator;

/**
 * <br/><br/>
 * <b>Responsabilidad</b>: Se encarga de realizar la validación de los DTOs de la capa de presentación.  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class DespensaValidator extends AbstractValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(DespensaValidator.class);
	
	private static javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	
	public DespensaValidator() {
		super();
	}


	@Override
	public void validate(ValidationContext context) {
		logger.debug("Validando...");		
		Object bean = context.getProperty().getBase();
		Set<ConstraintViolation<Object>> errores = validator.validate(bean);
		Iterator<ConstraintViolation<Object>> it = errores.iterator();
		while(it.hasNext()){
			ConstraintViolation<Object> error = it.next();
			addInvalidMessage(context, error.getPropertyPath().toString(), error.getMessage());
		}
		logger.debug("Errores: " + errores.size());
	}

}
