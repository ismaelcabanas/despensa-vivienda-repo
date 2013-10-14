<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
  <!-- <meta http-equiv="content-type" content="text/html; charset=utf-8" />-->
  <title><decorator:title default="Despensa" /></title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout-simple/main.css" />
  <decorator:head />
</head>
<body id="page-home">

	<div id="page">
		<div id="header">
			<div class="inside">
				<div id="about">
					<p><img src="" alt="" align="right" height="46" width="46" /><s:text name="texto.titulo.aplicacion" /></p>						
				</div>
			</div>
		</div>
	
		<div id="menu">
			<%@ include file="/WEB-INF/jsp/menu.jsp" %>
		</div>
	
		<div id="content">
			<decorator:body />
		</div>
	
		<div id="footer">
			
		</div>
	</div>
</body>
</html>