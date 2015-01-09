<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header>
	<div class="container" class="row">
		<button id="configure" class="visible-xs">
			<span class="glyphicon glyphicon-align-justify"></span>
		</button>

		<div id="configurator-wrap" class="col-sm-9 hidden-xs">
			<img alt="Administration Panel"
				src="<%=request.getContextPath() %>${logo }"
				style="padding: 10px 0px;">

			<div id="configurator" class="row">
				<label class="col-sm-4">
					<div class="description">Dimensions</div> <select id="dimensions"></select>
				</label> <label class="col-sm-4">
					<div class="description">Theme</div> <select id="theme"></select>
				</label> <label class="col-sm-4">
					<div class="description">Font-size</div> <select id="font-size"></select>
				</label>
			</div>
		</div>
	</div>
	<span id="config-toggle" class="k-icon k-i-arrowhead-s"></span>
</header>
