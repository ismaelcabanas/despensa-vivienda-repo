<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head> 
		<title><s:text name="producto.titulo.edicion"></s:text></title> 
	</head> 
	
	<body> 
	
    		<!-- <div id="ltcol"> --> 
    		<!-- 
    		<h1><s:text name="producto.titulo.alta"/></h1>
    		<p><s:text name="producto.titulo.alta.descripcion" /></p>
    		<p></p>
    		 -->
    		 
    		<!-- Errores -->
    		<s:if test="hasFieldErrors()"> 
    		<div id="divErrores" class="formerror">
    			<p>
    				<img src="${application.contextPath}/img/triangle_error.gif" alt="" height="16" hspace="5" width="16">Por favor, compruebe los datos y vuelva a intentarlo:
    			</p>
    			<s:fielderror />
    		</div>
    		<p></p>
    		</s:if>
    		<!-- Fin Errores -->	    			    	
    		
    		<div id="contentForm" class="frame tbar" style="border-bottom: 1px; solid;">
	    		<h3><s:text name="producto.titulo.edicion"/></h3>
	    		
	    		<!-- FORMULARIO -->	    		
	    		<s:form namespace="/catalogos/producto" id="id_form" action="actualizar" method="post">
	    			<s:hidden name="accion.producto.id" />
	    			<s:hidden name="accion.producto.codigo" />
	    			<p>
	    			<label for="producto.codigo"><s:text name="producto.codigo" /> <span><s:text name="texto.obligatorio" /></span></label>&nbsp;	    			
					<s:textfield id="producto.codigo" name="accion.producto.codigo" size="10" maxlength="10" disabled="true"></s:textfield>
					<%--					
					Para mostrar el error de un solo campo
					<s:fielderror cssClass="errorText">						
						<s:param value="%{'producto.codigo'}"></s:param>
					</s:fielderror>
					 --%>
					</p>			 
					<p>
					<label for="producto.nombre"><s:text name="producto.nombre" /> <span><s:text name="texto.obligatorio" /></span></label>&nbsp;
					<s:textfield id="producto.nombre" name="accion.producto.nombre" size="100" maxlength="100" tabindex="1" accesskey="n" />					
					</p>
					<p>					
					<label for="producto.marca.id"><s:text name="producto.marca" /> <span><s:text name="texto.opcional" /></span></label>&nbsp;
					<s:select name="accion.producto.marca.id" id="producto.marca.id" list="accion.marcas" listKey="id" listValue="nombre" key="producto.marca" emptyOption="true" tabindex="2" accesskey="m"  />
					</p>
					<p>
					&nbsp;
					</p>
					
					<s:submit id="btnCancelar" key="boton.cancelar" action="cancelar"  tabindex="3" accesskey="c" />
					<s:submit id="btnEliminar" key="boton.eliminar" action="eliminar"  tabindex="4" accesskey="e"  />
					<s:submit id="btnActualizar" key="boton.actualizar" action="actualizar"  tabindex="5" accesskey="a"  />											
					
				</s:form>
				<!-- FIN FORMULARIO -->
			
			</div> 				    		
	
	</body>		
</html>
