package org.icabanas.despensa.modelo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.icabanas.jee.api.integracion.modelo.Entidad;

@MappedSuperclass
public abstract class EntidadBase<ID extends Serializable> implements Entidad<ID>, Validable {
	
	private ID id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)	
	public ID getId(){
		return id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;		
	}

	
}
