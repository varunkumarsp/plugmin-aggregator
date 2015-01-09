<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 
<link href="<%=request.getContextPath() %>/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.${htmlClassAttribute }.min.css" rel="stylesheet" />
 -->

<script type="text/x-kendo-template" id="${fn:toLowerCase(entity)}-${fn:toLowerCase(view)}-detail-template">
	<div class="${htmlClassAttribute }">
		<div class="tabstrip">
			<ul>
				<c:forEach items="${metaTab.viewSections }" var="section" varStatus="loop">
					<li ${loop.first ? 'class="k-state-active"' : ''}>${section.metaView.label }</li>
				</c:forEach>
				<c:forEach items="${metaTab.gridSections }" var="section" varStatus="loop">
					<li>${section.title }</li>
				</c:forEach>
			</ul>
			
			<c:forEach items="${metaTab.viewSections }" var="section" varStatus="loop">
				<div>
					<div id='element_#: ${entityId }#_${section.title }' class='demo-section k-content plugmin-container' ng-controller='controller_#: ${entityId }#_${section.title }'>
						<div class="angular-view-wrapper">
							<div style="display: block;" class="shield">
								<div class="shield-ldr"></div>
							</div>
							${section.metaView.angularViewHtml }
						</div>
					</div>
				</div>
			</c:forEach>

			<c:forEach items="${metaTab.gridSections }" var="section" varStatus="loop">
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
    
    <c:forEach items="${metaTab.gridSections }" var="section" varStatus="loop">
    	templateLoader.loadExtTemplate('<%=request.getContextPath() %>/plugmin/grid/detail-template/${section.forEntity }?view=${section.forView }&drill-depth=${drillDepth }', function() {
    		${section.init }
    	});
	</c:forEach>
	
	<c:forEach items="${metaTab.viewSections }" var="section" varStatus="loop">
		<c:set var="q" value="${section.metaView.name }" />
		var uid_${q } = parentEntityId + "_${section.title }";
		var module_${q } = null;
		var $scope_${q } = null;
	
		<c:forEach items="${section.metaView.widgetConfigs }" var="widgetConfig">
			var ${widgetConfig.name } = ${widgetConfig.json };
		</c:forEach>

		<c:if test="${not empty section.metaView.dataConfig.name }">
			var ${section.metaView.dataConfig.variable } = {};
		</c:if>

		try {
			module_${q } = angular.module("module_" + uid);
		} catch(err) {
			module_${q } = null;
		}
	
		if(!module_${q }) {
			angular.module("module_" + uid_${q }, [ "kendo.directives" ]).controller("controller_" + uid_${q }, function($scope, $http){
				<c:forEach items="${section.metaView.angularScopes }" var="scope">
					${scope.name } = ${scope.variable };
				</c:forEach>
			
				$scope.updateModel = function(entityId, callback) {
					var viewReadUrl = "<%=request.getContextPath() %>/plugmin/rest/view/read/${entity }?view=${section.metaView.name }&entity-id=" + entityId;
				
					$http.post(viewReadUrl).
					success(function (data, status, headers, config) {
						$scope.${section.metaView.dataConfig.name } = data;
						callback();
					}).
					error(function (data, status, headers, config) {
						console.log(status);
					});
				};
			});

			angular.element(document).ready(function() {
				angular.bootstrap($('#element_' + uid_${q }), ["module_" + uid_${q }]);
			});
		}
		$scope_${q } = angular.element($('#element_' + uid_${q })).scope();
		$scope_${q }.updateModel(parentEntityId, function() {
			$('#element_' + uid_${q }).find('.shield').hide();
		});	
	</c:forEach>
}
</script>

