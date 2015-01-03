<%@page import="org.openxava.annotations.parse.EntityUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 
<link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.${htmlClassAttribute }.min.css" rel="stylesheet" />
 -->

<% request.setAttribute("random", EntityUtil.randomInt()); %>
<c:set var="viewSection" value="${metaTab.sections[0] }" />
<c:set var="metaView" value="${metaTab.sections[0].metaView }" />

<script type="text/x-kendo-template" id="${fn:toLowerCase(entity)}-${fn:toLowerCase(view)}-detail-template">
	<div class="${htmlClassAttribute }">
		<div class="tabstrip">
			<ul>
				<c:forEach items="${metaTab.sections }" var="section" varStatus="loop">
					<li ${loop.first ? 'class="k-state-active"' : ''}>${section.title }</li>
				</c:forEach>
			</ul>
			
			<div>
				<div id='element_${random }' class='demo-section k-content plugmin-container' ng-controller='controller_${random }'>
					${viewSection.angularView }
					<button class="k-button" ng-click="submit()">Submit</button>
				</div>
			</div>

			<c:forEach items="${metaTab.sections }" var="section" varStatus="loop" begin="1">
				<div>${section.content }</div>
			</c:forEach>
		</div>
	</div>
</script>

<script type="text/javascript">
<c:forEach items="${metaTab.jsFunctions }" var="jsFun">
	${jsFun }
</c:forEach>

function ${fn:toLowerCase(entity)}_${fn:toLowerCase(view)}_detail_init(e) {
	var detailRow = e.detailRow;
	var parentEntityId = e.data.${entityId };

    detailRow.find(".tabstrip").kendoTabStrip({
        animation: {
            open: { effects: "fadeIn" }
        }
    });
    
    <c:forEach items="${metaTab.sections }" var="section" varStatus="loop" begin="1">
    	templateLoader.loadExtTemplate('<%=request.getContextPath() %>/plugmin/grid/detail-template/${section.forEntity }?view=${section.forView }&drill-depth=${drillDepth }', function() {
    		${section.init }
    	});
	</c:forEach>
	
	<c:forEach items="${metaView.widgetConfigs }" var="widgetConfig">
		var ${widgetConfig.name } = ${widgetConfig.json };
	</c:forEach>

	<c:if test="${not empty metaView.dataConfig.name }">
		${metaView.dataConfig.name } = ${metaView.dataConfig.json };
	</c:if>

	angular.module("module_${random }", [ "kendo.directives" ]).controller("controller_${random }", function($scope){
		<c:forEach items="${viewSection.angularScopes }" var="scope">
			${scope.name } = ${scope.variable };
		</c:forEach>

		$scope.submit = function () {
			console.log('submit invoked');
			var model = $scope.${metaView.dataConfig.name }
			console.log(model);
		};
	});

	angular.element(document).ready(function() {
		angular.bootstrap($('#element_${random }'), ["module_${random }"]);
	});
}
</script>