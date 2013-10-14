<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url action="catalogos/producto/paginar" var="actionProductoListado" />

<ul>
	<li><s:text name="menu.administracion" />
		<ul>
			<li><a id="linkCatalogoProductos" href="${actionProductoListado}"><s:text name="menu.administracion.productos" /></a></li>	
		</ul>
	</li>
	
</ul>