<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Indianstage Admin - ${entity }::${view }::Listing</title>
		<link href="/plugmin/kendo-ui/content/integration/bootstrap-integration/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.common-bootstrap.min.css" rel="stylesheet" />
        <link href="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.bootstrap.min.css" rel="stylesheet" />
        <link href="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.bootstrap.min.css" rel="stylesheet" />

        <script src="/plugmin/kendo-ui/code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="/plugmin/kendo-ui/ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script src="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.all.min.js"></script>
        <script src="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.timezones.min.js"></script>

        <script src="/plugmin/kendo-ui/content/shared/js/theme-chooser.js"></script>

        <link href="/plugmin/kendo-ui/content/integration/bootstrap-integration/styles.css" rel="stylesheet" />
        
        <!--[if lt IE 9]>
        <script src="/kendo-ui/content/integration/bootstrap-integration/html5shiv.js"></script>
        <script src="/kendo-ui/content/integration/bootstrap-integration/respond.min.js"></script>
        <![endif]-->

		<script src="/plugmin/scripts/app.js"></script>
		        
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
	
	<header>
            <div class="container" class="row">
                <h1 class="col-sm-3 hidden-sm">Indianstage &hearts; Admin</h1>
                <h1 class="col-sm-3 visible-sm">Indianstage &hearts;<br/> Admin</h1>

                <button id="configure" class="visible-xs"><span class="glyphicon glyphicon-align-justify"></span></button>

                <div id="configurator-wrap" class="col-sm-9 hidden-xs">
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
        </header>

     <div id="example">
     	<div id="master-grid"></div>
     </div>

	<%@include file="../footer.jsp"%>
	
	<%@include file="grid-master.jsp"%>
	
</body>
</html>
