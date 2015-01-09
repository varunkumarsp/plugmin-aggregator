<script type="text/x-kendo-template"
	id="products-event_loading-detail-template">
	<%@include file="grid-master.jsp"%>	
</script>

<script type="text/javascript">
	function products_event_loading_detail_init(e) {
		var detailRow = e.detailRow;
		var parentEntityId = e.data.productsId;

		var uid = null;

		uid = parentEntityId + "_Basic";

		var productsDescription_productsName_config = {
			dataTextField : "productsName",
		};

		var duration_config = {
			min : 1,
		};

		var productsImage_config = {
			multiple : false
		};

		var rating_config = {
			min : 1,
		};

		var custom6_config = {
			dataSource : [ "pics", "std", "wide", "tall", "large", "trending" ]
		};

		var custom1int_config = {
			min : 1,
		};

		var custom2int_config = {
			min : 1,
		};

		var productsDescription_productsDescriptionField_config = {};

		var manufacturers_config = {
			dataTextField : "manufacturersName",
		};

		var venue_config = {
			dataTextField : "venueName",
		};

		var eventOrganizer_config = {
			dataTextField : "eoName",
		};

		var Products = {};

		var module;
		try {
			module = angular.module("module_" + uid);
		} catch (err) {
			module = null;
		}

		if (!module) {
			angular
					.module("module_" + uid, [ "kendo.directives" ])
					.controller(
							"controller_" + uid,
							function($scope, $http) {
								$scope.Products = Products;
								$scope.productsDescription_productsName = productsDescription_productsName_config;
								$scope.duration = duration_config;
								$scope.productsImage_changed = function(e) { };
								$scope.productsImage_removed = function(e) { };
								$scope.productsImage = productsImage_config;
								$scope.rating = rating_config;
								$scope.custom6 = custom6_config;
								$scope.custom1int = custom1int_config;
								$scope.custom2int = custom2int_config;
								$scope.productsDescription_productsDescriptionField = productsDescription_productsDescriptionField_config;
								$scope.manufacturers = manufacturers_config;
								$scope.venue = venue_config;
								$scope.eventOrganizer = eventOrganizer_config;
								$scope.onSubmit = function() { console.log(model); };
								$scope.updateModel = function(entityId, callback) {	};
							});

			angular.element(document).ready(function() {
				angular.bootstrap($('#element_' + uid), [ "module_" + uid ]);
			});
		}
		var $scope = angular.element($('#element_' + uid)).scope();
		$scope.updateModel(parentEntityId, function() {
			$('#element_' + uid).find('.shield').hide();
		});

		uid = parentEntityId + "_default_Shows_section";

		var productsDateAvailable_config = {
			format : "dd-MM-yyyy hh:mm tt"
		};

		var Products = {};

		var module;
		try {
			module = angular.module("module_" + uid);
		} catch (err) {
			module = null;
		}

		if (!module) {
			angular
					.module("module_" + uid, [ "kendo.directives" ])
					.controller(
							"controller_" + uid,
							function($scope, $http) {
								$scope.Products = Products;
								$scope.productsDateAvailable = productsDateAvailable_config;
								$scope.onSubmit = function() { };
								$scope.updateModel = function(entityId,	callback) { };
							});

			angular.element(document).ready(function() {
				angular.bootstrap($('#element_' + uid), [ "module_" + uid ]);
			});
		}
		var $scope = angular.element($('#element_' + uid)).scope();
		$scope.updateModel(parentEntityId, function() {
			$('#element_' + uid).find('.shield').hide();
		});

		uid = parentEntityId + "_default_Misc_section";

		var Products = {};

		var module;
		try {
			module = angular.module("module_" + uid);
		} catch (err) {
			module = null;
		}

		if (!module) {
			angular
					.module("module_" + uid, [ "kendo.directives" ])
					.controller(
							"controller_" + uid,
							function($scope, $http) {
								$scope.Products = Products;
								$scope.onSubmit = function() { };
								$scope.updateModel = function(entityId, callback) { };
							});

			angular.element(document).ready(function() {
				angular.bootstrap($('#element_' + uid), [ "module_" + uid ]);
			});
		}
		var $scope = angular.element($('#element_' + uid)).scope();
		$scope.updateModel(parentEntityId, function() {
			$('#element_' + uid).find('.shield').hide();
		});

	}
</script>