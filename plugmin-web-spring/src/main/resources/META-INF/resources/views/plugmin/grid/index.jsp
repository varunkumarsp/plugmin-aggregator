<%@page import="org.plugmin.core.config.PlugminWebApplicationInitializer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	request.setAttribute("logo", PlugminWebApplicationInitializer.PLUGMIN_LOGO);
%>

<!DOCTYPE html>
<html>
<head>
<title>Indianstage Admin - ${entity }::${view }::Listing</title>
		<link href="<%=request.getContextPath() %>/plugmin/kendo-ui/content/integration/bootstrap-integration/css/bootstrap.min.css" rel="stylesheet" />
		<!-- 
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.common-bootstrap.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.bootstrap.min.css" rel="stylesheet" />
         -->
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.common-material.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.material.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.material.min.css" rel="stylesheet" />
        <link href="<%=request.getContextPath() %>/plugmin/styles/style.css" rel="stylesheet" />

        <script src="<%=request.getContextPath() %>/plugmin/kendo-ui/code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/plugmin/kendo-ui/ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script src="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.all.min.js"></script>
        <script src="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.timezones.min.js"></script>

        <script src="<%=request.getContextPath() %>/plugmin/kendo-ui/content/shared/js/theme-chooser.js"></script>
        
        <link href="<%=request.getContextPath() %>/plugmin/kendo-ui/content/integration/bootstrap-integration/styles.css" rel="stylesheet" />

        <!--[if lt IE 9]>
        <script src="/kendo-ui/content/integration/bootstrap-integration/html5shiv.js"></script>
        <script src="/kendo-ui/content/integration/bootstrap-integration/respond.min.js"></script>
        <![endif]-->

		<script src="<%=request.getContextPath() %>/plugmin/scripts/app.js"></script>
		
		<!-- If this is placed above <html> tag, then serious issues ocuuer in the ui of the page. -->
		<script>var ctx = "${pageContext.request.contextPath}"</script>
		        
        <script type="text/javascript">
        $(document).ready(function(){
        	$('input[type=text]').val('');
        	$('input[type=radio]').checked=false;
        	$('input[type=checkbox]').checked=false;
        });
        </script>
</head>
<body>
	<%@include file="../header.jsp"%>

     <div id="example">
     	<div id="master-grid" style="font-size: 12px;"></div>
     </div>

	<%@include file="../footer.jsp"%>
	
	<%@include file="grid-master.jsp"%>
	
	<button class="k-button" id="clear-components" style="margin: 10px 5px;">Clear Components</button>
</body>
</html>
