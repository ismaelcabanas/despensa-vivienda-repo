package org.icabanas.despensa.modelo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Producto.class)
public class Producto_ {

	public static volatile SingularAttribute<Producto, Long> id;
	public static volatile SingularAttribute<Producto, String> nombre;
	public static volatile SingularAttribute<Producto, String> codigo;
	public static volatile SingularAttribute<Producto, Marca> marca;	
	public static volatile SingularAttribute<Producto, Categoria> categoria;
}
