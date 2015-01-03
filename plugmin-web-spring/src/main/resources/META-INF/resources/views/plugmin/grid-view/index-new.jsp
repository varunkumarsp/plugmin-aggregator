<%@page import="org.plugmin.core.config.PlugminWebApplicationInitializer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>var ctx = "${pageContext.request.contextPath}"</script>

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
	
	<header>
            <div class="container" class="row">
                <button id="configure" class="visible-xs"><span class="glyphicon glyphicon-align-justify"></span></button>

                <div id="configurator-wrap" class="col-sm-9 hidden-xs">
                	<img alt="Administration Panel" src="<%=request.getContextPath() %>${logo }" style="padding: 10px 0px;">
                	
                    <div id="configurator" class="row">
                        <label class="col-sm-4">
                            <div class="description">Dimensions</div>
                            <select id="dimensions"></select>
                        </label>

                        <label class="col-sm-4">
                            <div class="description">Theme</div>
                            <select id="theme"></select>
                        </label>

                        <label class="col-sm-4">
                            <div class="description">Font-size</div>
                            <select id="font-size"></select>
                        </label>
                    </div>
                </div>
            </div>
            <span id="config-toggle" class="k-icon k-i-arrowhead-s"></span>
        </header>

     <div id="example">
     	<div id="master-grid" style="font-size: 12px;"></div>
     </div>

	<%@include file="../footer.jsp"%>
	
	<%@include file="grid-master.jsp"%>
	
	<button id="clear-components" class="k-button">Clear Components</button>
</body>
</html>
