<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="/plugmin/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.${htmlClassAttribute }.min.css" rel="stylesheet" />

<script type="text/x-kendo-template" id="${fn:toLowerCase(entity)}-${fn:toLowerCase(view)}-detail-template">
	<div class="${htmlClassAttribute }">
		<div class="tabstrip">
			<ul>
				<c:forEach items="${metaTab.sections }" var="section" varStatus="loop">
					<li ${loop.first ? 'class="k-state-active"' : ''}>${section.title }</li>
				</c:forEach>
			</ul>
			<c:forEach items="${metaTab.sections }" var="section" varStatus="loop">
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
    
    <c:forEach items="${metaTab.sections }" var="section" varStatus="loop">
    	templateLoader.loadExtTemplate('/plugmin/grid/detail-template/${section.forEntity }?view=${section.forView }&drill-depth=${drillDepth }', function() {
    		${section.init }
    	});
	</c:forEach>
}
</script>