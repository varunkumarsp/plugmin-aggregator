<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="view-wrapper">
	<div class="tabstrip">
		<ul>
			<c:forEach items="${metaViews }" var="metaView" varStatus="loop">
				<li ${loop.first ? 'class="k-state-active"' : ''}>${metaView.label }</li>
			</c:forEach>
			
			<c:forEach items="${metaCollectionViews }" var="metaCollectionView" varStatus="loop">
				<li>${metaCollectionView.label }</li>
			</c:forEach>
		</ul>
	
		<c:forEach items="${metaViews }" var="metaView" varStatus="loop">
			<div>
				<div id='element_${entityId }_${metaView.name }' class='demo-section k-content plugmin-container' ng-controller='controller_${entityId }_${metaView.name }'>
					<div class="angular-view-wrapper">
						<div style="display: block;" class="shield">
							<div class="shield-ldr">
							</div>
						</div>
						${metaView.angularViewHtml }
					</div>
				</div>
			</div>
		</c:forEach>
		
		<c:forEach items="${metaCollectionViews }" var="metaCollectionView" varStatus="loop">
			<div>
				<div class="grid-wrapper">
     				<div id="${metaCollectionView.id }" style="font-size: 12px;"></div>
     			</div>
     		</div>	
		</c:forEach>
	</div>
</div>

<script type="text/javascript">
<c:forEach items="${metaViews }" var="metaView" varStatus="loop">
	<c:forEach items="${metaView.jsFunctions }" var="jsFun">
		${jsFun }
	</c:forEach>
</c:forEach>

<c:forEach items="${metaCollectionViews }" var="metaCollectionView" varStatus="loop">
	<c:forEach items="${metaCollectionView.jsFunctions }" var="jsFun">
		${jsFun }
	</c:forEach>
</c:forEach>

$(document).ready(function() {
	<c:forEach items="${metaViews }" var="metaView" varStatus="loop">
		<c:forEach items="${metaView.onDocReadyjsFunctions }" var="jsFun">
			${jsFun }
		</c:forEach>
	</c:forEach>
	
	<c:forEach items="${metaCollectionViews }" var="metaCollectionView" varStatus="loop">
		<c:forEach items="${metaCollectionView.onDocReadyjsFunctions }" var="jsFun">
			${jsFun }
		</c:forEach>
	</c:forEach>
	
	init();
});

function init() {
	var parentEntityId = ${entityId };

    $('div.view-wrapper').find(".tabstrip").kendoTabStrip({
        animation: {
            open: { effects: "fadeIn" }
        }
    });
    
    <c:forEach items="${metaCollectionViews }" var="metaCollectionView" varStatus="loop">
    	templateLoader.loadExtTemplate('<%=request.getContextPath() %>/plugmin/grid/detail-template/${metaCollectionView.entity.simpleName }?view=${metaCollectionView.metaTab.name }&drill-depth=0', function() {
			var gridConfig = ${metaCollectionView.metaTab.json };
			$("#${metaCollectionView.id }").kendoGrid(gridConfig);
		});
	</c:forEach>

    <c:forEach items="${metaViews }" var="metaView" varStatus="loop">
		<c:set var="q" value="${metaView.name }" />
		var uid_${q } = parentEntityId + "_${metaView.name }";
		var module_${q } = null;
		var $scope_${q } = null;

		<c:forEach items="${metaView.widgetConfigs }" var="widgetConfig">
			var ${widgetConfig.name } = ${widgetConfig.json };
		</c:forEach>

		<c:if test="${not empty metaView.dataConfig.name }">
			var ${metaView.dataConfig.variable } = {};
		</c:if>

		try {
			module_${q } = angular.module("module_" + uid);
		} catch(err) {
			module_${q } = null;
		}

		if(!module_${q }) {
			angular.module("module_" + uid_${q }, [ "kendo.directives" ]).controller("controller_" + uid_${q }, function($scope, $http){
				<c:forEach items="${metaView.angularScopes }" var="scope">
					${scope.name } = ${scope.variable };
				</c:forEach>
		
				$scope.updateModel = function(entityId, callback) {
					var viewReadUrl = "<%=request.getContextPath() %>/plugmin/rest/view/read/${entity }?view=${metaView.name }&entity-id=" + entityId;
			
					$http.post(viewReadUrl).
					success(function (data, status, headers, config) {
						$scope.${metaView.dataConfig.name } = data;
						callback();
					}).
					error(function (data, status, headers, config) {
						console.log(status);
					});
				};
			});
			angular.bootstrap($('#element_' + uid_${q }), ["module_" + uid_${q }]);
		}
		$scope_${q } = angular.element($('#element_' + uid_${q })).scope();
		$scope_${q }.updateModel(parentEntityId, function() {
			$('#element_' + uid_${q }).find('.shield').hide();
		});	
	</c:forEach>
}
</script>