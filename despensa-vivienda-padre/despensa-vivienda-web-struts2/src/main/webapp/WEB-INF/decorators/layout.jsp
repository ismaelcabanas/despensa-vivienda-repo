<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head> 
		<title><decorator:title default="Despensa" /></title>
		<c:set var="ctx" value="${pageContext.request.contextPath}" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css" />
		<decorator:head />		
	</head> 
	
	<body id="page-home"> 
	
		<div id="page">
		
			<!-- HEADER -->
			<div id="header">			
				<div class="inside">
					<div id="about">
						<p><img src="" alt="" align="right" height="46" width="46" /><s:text name="texto.titulo.aplicacion" /></p>						
					</div>
				</div>
			</div>
			<!-- HEADER -->
					
	        <div id="content"> 
	    	
	    		<decorator:body />
	    		
			</div>				
			<!-- FIN CONTENT -->
			
			<!-- PIE -->
			<div id="footer">
			
			</div>
			<!-- FIN PIE -->	
		</div>
	</body>		
</html>
