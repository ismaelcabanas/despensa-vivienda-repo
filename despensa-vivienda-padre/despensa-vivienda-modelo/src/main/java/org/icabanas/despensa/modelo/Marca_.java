package org.icabanas.despensa.modelo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Marca.class)
public class Marca_ {

	public static volatile SingularAttribute<Marca, Long> id;
	public static volatile SingularAttribute<Marca, String> nombre;
}
