






<!DOCTYPE html>
<html>
<head>
<title>Indianstage Admin - products::default::Listings</title>
		<link rel="stylesheet" href="/kendo-ui/content/integration/bootstrap-integration/css/bootstrap.min.css" />
        <link href="/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.common-bootstrap.min.css" rel="stylesheet" />
        <link href="/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.bootstrap.min.css" rel="stylesheet" />
        <link href="/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="/kendo-ui/cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.bootstrap.min.css" rel="stylesheet" />

        <script src="/kendo-ui/code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="/kendo-ui/ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script src="/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.all.min.js"></script>
        <script src="/kendo-ui/cdn.kendostatic.com/2014.2.1008/js/kendo.timezones.min.js"></script>

        <script src="/kendo-ui/content/shared/js/theme-chooser.js"></script>

        <link rel="stylesheet" href="/kendo-ui/content/integration/bootstrap-integration/styles.css" />
        
        <!--[if lt IE 9]>
        <script src="/kendo-ui/content/integration/bootstrap-integration/html5shiv.js"></script>
        <script src="/kendo-ui/content/integration/bootstrap-integration/respond.min.js"></script>
        <![endif]-->

		<script src="/resources/js/app.js"></script>
		        
        <script>var ctx = ""</script>
        
        <script type="text/javascript">
        $(document).ready(function(){
        	$('input[type=text]').val('');
        	$('input[type=radio]').checked=false;
        	$('input[type=checkbox]').checked=false;
        });
        </script>
</head>
<body>
	<script>var ctx = ""</script>

	
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

	
	
	

<script type="text/javascript">
function timeEditor(container, options) {
    $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoTimePicker({});
}
 
function dateTimeEditor(container, options) {
    $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoDateTimePicker({});
}

function timeEditorForFilter(element) {
	element.kendoTimePicker({
		format: "hh:mm tt"
	});
}

function dateTimeEditorForFilter(element) {
	element.kendoDateTimePicker({
		format: "dd-MM-yyyy hh:mm tt"
	});
}

function timeEditorForFilterSuggestion(arg) {
	arg.element.kendoTimePicker({
		format: "hh:mm tt"
	});
}

function dateTimeEditorForFilterSuggestion(arg) {
	arg.element.kendoDateTimePicker({
		format: "dd-MM-yyyy hh:mm tt"
	});
}

function parameterMap(options, operation) {
	if (operation !== "read" && options.models) {
	    return {models: kendo.stringify(options.models)};
	}
	return JSON.stringify(options); 
}


	function tbleventorganizerdetails_eoName__dropdown_18892(container, options) {var input = $('<input data-bind="value:' + options.field.split('\.')[0] + '"/>');input.attr('name', options.field.split('\.')[0]);input.attr('id', options.field.split('\.')[0]);input.appendTo(container);input.kendoDropDownList({
  serialVersionUID : 1,
  dataSource : {
    schema : {
      model : {
        fields : {
          id : {
            type : "number"
          },
          eoName : {
            type : "string"
          }
        }
      },
      data : "data",
      total : "total",
      groups : "data",
      aggregates : "aggregates"
    },
    transport : {
      read : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/dropdown/read/TblEventorganizerDetails?view=tbleventorganizerdetails_eoName__dropdown_18892"
      },
      parameterMap : parameterMap
    },
    pageSize : 10,
    serverGrouping : true,
    serverPaging : true,
    serverFiltering : true,
    serverSorting : true,
    serverAggregates : true
  },
  dataTextField : "eoName",
  dataValueField : "id",
  filter : "contains",
  minLength : 2
})}

	function taxclass_taxClassId__dropdown_19355(container, options) {var input = $('<input data-bind="value:' + options.field.split('\.')[0] + '"/>');input.attr('name', options.field.split('\.')[0]);input.attr('id', options.field.split('\.')[0]);input.appendTo(container);input.kendoDropDownList({
  serialVersionUID : 1,
  dataSource : {
    schema : {
      model : {
        fields : {
          taxClassId : {
            type : "number"
          }
        }
      },
      data : "data",
      total : "total",
      groups : "data",
      aggregates : "aggregates"
    },
    transport : {
      read : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/dropdown/read/TaxClass?view=taxclass_taxClassId__dropdown_19355"
      },
      parameterMap : parameterMap
    },
    pageSize : 10,
    serverGrouping : true,
    serverPaging : true,
    serverFiltering : true,
    serverSorting : true,
    serverAggregates : true
  },
  dataTextField : "taxClassId",
  dataValueField : "taxClassId",
  filter : "contains",
  minLength : 2
})}


$(document).ready(function() {
	$("#master-grid").kendoGrid({
  detailTemplate : kendo.template($('#products-default-detail-template').html()),
  detailInit : products_default_detail_init,
  editable : true,
  filterable : {
    mode : "row"
  },
  groupable : true,
  height : "600px",
  mobile : true,
  navigatable : true,
  pageable : true,
  reorderable : true,
  resizable : true,
  sortable : true,
  toolbar : [ {
    name : "save",
    text : "Save"
  }, {
    name : "create",
    text : "Add new"
  }, {
    name : "cancel",
    text : "Cancel changes"
  } ],
  dataSource : {
    schema : {
      model : {
        id : "productsId",
        fields : {
          productsId : {
            editable : false,
            nullable : true,
            type : "number"
          },
          productsModel : {
            type : "string"
          },
          rating : { },
          productsDateExpiry : {
            type : "date"
          },
          tblEventorganizerDetails : {
            defaultValue : {
              id : "",
              eoName : ""
            }
          },
          taxClass : {
            defaultValue : {
              taxClassId : ""
            }
          }
        }
      },
      data : "data",
      total : "total",
      groups : "data",
      aggregates : "aggregates"
    },
    transport : {
      create : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/grid/create/Products?view=default"
      },
      read : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/grid/read/Products?view=default"
      },
      update : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/grid/update/Products?view=default"
      },
      destroy : {
        contentType : "application/json",
        dataType : "json",
        type : "POST",
        url : "/rest/grid/destroy/Products?view=default"
      },
      parameterMap : parameterMap
    },
    pageSize : 10,
    serverGrouping : true,
    serverPaging : true,
    serverFiltering : true,
    serverSorting : true,
    serverAggregates : true
  },
  columns : [ {
    groupable : false,
    title : "Products Id",
    field : "productsId"
  }, {
    filterable : {
      cell : {
        dataSource : {
          schema : {
            model : {
              fields : {
                productsId : {
                  type : "number"
                },
                productsModel : {
                  type : "string"
                }
              }
            },
            data : "data",
            total : "total",
            groups : "data",
            aggregates : "aggregates"
          },
          transport : {
            read : {
              contentType : "application/json",
              dataType : "json",
              type : "POST",
              url : "/rest/dropdown/read/Products?view=products_productsModel__dropdown_11452"
            },
            parameterMap : parameterMap
          },
          pageSize : 10,
          serverGrouping : true,
          serverPaging : true,
          serverFiltering : true,
          serverSorting : true,
          serverAggregates : true
        },
        dataTextField : "productsModel",
        suggestionOperator : "contains",
        minLength : 2,
        operator : "contains"
      }
    },
    title : "Products Model",
    field : "productsModel"
  }, {
    title : "Rating",
    field : "rating"
  }, {
    editor : dateTimeEditor,
    filterable : {
      cell : {
        template : dateTimeEditorForFilterSuggestion
      },
      ui : dateTimeEditorForFilter
    },
    format : "{0:dd-MM-yyyy hh:mm tt}",
    title : "Products Date Expiry",
    field : "productsDateExpiry"
  }, {
    editor : tbleventorganizerdetails_eoName__dropdown_18892,
    filterable : {
      cell : {
        dataSource : {
          schema : {
            model : {
              fields : {
                id : {
                  type : "number"
                },
                eoName : {
                  type : "string"
                }
              }
            },
            data : "data",
            total : "total",
            groups : "data",
            aggregates : "aggregates"
          },
          transport : {
            read : {
              contentType : "application/json",
              dataType : "json",
              type : "POST",
              url : "/rest/dropdown/read/TblEventorganizerDetails?view=tbleventorganizerdetails_eoName__dropdown_18892"
            },
            parameterMap : parameterMap
          },
          pageSize : 10,
          serverGrouping : true,
          serverPaging : true,
          serverFiltering : true,
          serverSorting : true,
          serverAggregates : true
        },
        dataTextField : "eoName",
        suggestionOperator : "contains",
        minLength : 2,
        operator : "contains"
      }
    },
    template : "#=tblEventorganizerDetails.eoName#",
    title : "Tbl Eventorganizer Details",
    field : "tblEventorganizerDetails"
  }, {
    editor : taxclass_taxClassId__dropdown_19355,
    filterable : {
      cell : {
        dataSource : {
          schema : {
            model : {
              fields : {
                taxClassId : {
                  type : "number"
                }
              }
            },
            data : "data",
            total : "total",
            groups : "data",
            aggregates : "aggregates"
          },
          transport : {
            read : {
              contentType : "application/json",
              dataType : "json",
              type : "POST",
              url : "/rest/dropdown/read/TaxClass?view=taxclass_taxClassId__dropdown_19355"
            },
            parameterMap : parameterMap
          },
          pageSize : 10,
          serverGrouping : true,
          serverPaging : true,
          serverFiltering : true,
          serverSorting : true,
          serverAggregates : true
        },
        dataTextField : "taxClassId",
        suggestionOperator : "contains",
        minLength : 2,
        operator : "contains"
      }
    },
    template : "#=taxClass.taxClassId#",
    title : "Tax Class",
    field : "taxClass"
  } ]
});
});

function products_default_detail_init(e) {
	var detailRow = e.detailRow;

    detailRow.find(".tabstrip").kendoTabStrip({
        animation: {
            open: { effects: "fadeIn" }
        },
        contentUrls: [
                      null,
                      "http://demos.telerik.com/kendo-ui/content/web/tabstrip/ajax/ajaxContent1.html"
                  ]
    });
}
</script>

<script type="text/x-kendo-template" id="products-default-detail-template">
	<div class="tabstrip">
		<ul>
			
				<li>Customers Basket Attributeses</li>
			
				<li class="k-state-active">Customers Baskets</li>
			
		</ul>
		
			<div><div class="Products-customersBasketAttributeses-section"></div></div>
		
			<div><div class="Products-customersBaskets-section"></div></div>
		
	</div>
</script>

<script type="text/x-kendo-template" id="Products-simple-detail-template">
	<div class="tabstrip">
		<ul>
			
		</ul>
		
	</div>
</script>
	
</body>
</html>
