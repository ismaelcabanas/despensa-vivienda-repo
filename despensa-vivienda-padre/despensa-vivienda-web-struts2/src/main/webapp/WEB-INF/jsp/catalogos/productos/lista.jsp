<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head> 
		<title><s:text name="producto.titulo.listado" /></title>
		<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
			function resetForm(){
				document.getElementById("filtro.codigo").value = "";
				document.getElementById("filtro.nombre").value = "";
				document.getElementById("filtro.marca").value = "";				
			}
		</script>
	</head> 
	
	<body> 
	
				<!-- Mensajes -->
				<s:if test="hasActionMessages()"> 
		    		<div id="divMensajes" class="divMensajes">
		    			<s:actionmessage/>
		    		</div>
		    		<p></p>
	    		</s:if>
	    		<!-- Fin Mensajes -->
	    		
				<div id="contentForm" class="frame tbar" >
					
					<h3><s:text name="producto.titulo.listado"  /></h3>
					<!-- FORMULARIO -->		    			    		 	    		
		    		<s:form namespace="/catalogos/producto" id="id_form" method="post" >		    			
		    			<p>
		    			<label for="producto.codigo" style="width:50px;"><s:text name="producto.codigo" /></label>&nbsp;	    			
						<s:textfield id="filtro.codigo" name="accion.filtro.codigo" size="10" maxlength="10" tabindex="1" accesskey="c" ></s:textfield>					
						</p>
		    			<p>
						<label for="producto.nombre" style="width:50px;"><s:text name="producto.nombre" /></label>&nbsp;
						<s:textfield id="filtro.nombre" name="accion.filtro.nombre" size="100" maxlength="100" tabindex="2" accesskey="n" />					
						</p>
						<p>					
						<label for="producto.marca" style="width:50px;"><s:text name="producto.marca" /></label>&nbsp;
						<s:select id="filtro.marca" name="accion.filtro.marca" list="accion.marcas" listKey="id" listValue="nombre" key="filtro.marca" emptyOption="true" tabindex="3" accesskey="m"  />
						</p>
						<s:submit id="btnNuevo" action="alta" key="boton.nuevo" tabindex="4" accesskey="n" />						
						<s:submit id="btnBuscar" key="boton.buscar" action="paginar" tabindex="5" accesskey="b"  />
						<s:reset key="boton.limpiar" tabindex="6" accesskey="l" onclick="resetForm();"></s:reset>
					</s:form> 		
					<!-- FIN FORMULARIO -->										
					<p>
					</p>
					<hr />
					
					<p></p>
				
					<s:if test="not paginacion.vacia">
						<!-- 
						form name="tabla" action="buscar" method="post"
						display:table name="listadoPaginacion" uid="resultado" form="tabla" export="true"
						 -->
						 <display:table name="paginacion" requestURI="paginar" uid="resultado">
							<display:column property="codigo" title="Código" url="/catalogos/producto/editar" paramId="accion.producto.id" paramProperty="id" />
							<display:column property="nombre" sortable="true" sortProperty="producto.nombre" title="Nombre" url="/catalogos/producto/editar" paramId="accion.producto.id" paramProperty="id" />
							<display:column property="marca.nombre" sortable="true" title="Marca" url="/catalogos/producto/editar" paramId="accion.producto.id" paramProperty="id" />							
						</display:table>
						<!-- form -->
					</s:if>
				</div>    		
												
	</body>
 
</html>
