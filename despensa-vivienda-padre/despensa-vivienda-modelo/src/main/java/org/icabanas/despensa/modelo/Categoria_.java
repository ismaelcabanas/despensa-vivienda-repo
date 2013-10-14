package org.icabanas.despensa.modelo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Categoria.class)
public class Categoria_ {
	public static volatile SingularAttribute<Categoria, Long> id;
	public static volatile SingularAttribute<Categoria, String> nombre;
}
