<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

$(document).ready(function() {
	templateLoader.loadExtTemplate('/plugmin/grid/detail-template/${entity }?view=${view }&drill-depth=0', function() {
		var gridConfig = ${metaTab.json };
		$("#master-grid").kendoGrid(gridConfig);
	});
});
</script>