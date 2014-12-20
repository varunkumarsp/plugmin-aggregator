<!DOCTYPE html>
<html>
<head>
    <style>html { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }</style>
    <title></title>
    <link href="<%=request.getContextPath() %>/resources/2014.2.716/styles/kendo.common.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/2014.2.716/styles/kendo.default.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/2014.2.716/styles/kendo.dataviz.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/2014.2.716/styles/kendo.dataviz.default.min.css" rel="stylesheet" />
    <script src="<%=request.getContextPath() %>/resources/2014.2.716/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/2014.2.716/js/angular.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/2014.2.716/js/kendo.all.min.js"></script>
</head>
<body>
    <div id="example" ng-app="KendoDemos">
    <div ng-controller="MyCtrl">
        <kendo-grid options="mainGridOptions"></kendo-grid>

        <script type="text/x-kendo-template" id="template">
            <kendo-tab-strip>
            <ul>
                <li class="k-state-active">Orders</li>
                <li>Contact information</li>
            </ul>
            <div>
                <div kendo-grid k-options="detailGridOptions(dataItem)"></div>
            </div>
            <div>
                <ul>
                    <li>Country: <input ng-model="dataItem.Country" /></li>
                    <li>City: <input ng-model="dataItem.City" /></li>
                    <li>Address: {{dataItem.Address}}</li>
                    <li>Home phone: {{dataItem.HomePhone}}</li>
                </ul>
            </div>
            </kendo-tab-strip>
        </script>

    </div>
</div>

<script>
    angular.module("KendoDemos", [ "kendo.directives" ]);
    function MyCtrl($scope) {
        $scope.mainGridOptions = {
            dataSource: {
                type: "odata",
                transport: {
                    read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Employees"
                },
                pageSize: 5,
                serverPaging: true,
                serverSorting: true
            },
            sortable: true,
            pageable: true,
            detailTemplate: kendo.template($("#template").html()),
            dataBound: function() {
                this.expandRow(this.tbody.find("tr.k-master-row").first());
            },
            columns: [{
                field: "FirstName",
                title: "First Name",
                width: "120px"
                },{
                field: "LastName",
                title: "Last Name",
                width: "120px"
                },{
                field: "Country",
                width: "120px"
                },{
                field: "City",
                width: "120px"
                },{
                field: "Title"
            }]
        };

        $scope.detailGridOptions = function(dataItem) {
            return {
                dataSource: {
                    type: "odata",
                    transport: {
                        read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Orders"
                    },
                    serverPaging: true,
                    serverSorting: true,
                    serverFiltering: true,
                    pageSize: 5,
                    filter: { field: "EmployeeID", operator: "eq", value: dataItem.EmployeeID }
                },
                scrollable: false,
                sortable: true,
                pageable: true,
                columns: [
                { field: "OrderID", title:"ID", width: "56px" },
                { field: "ShipCountry", title:"Ship Country", width: "110px" },
                { field: "ShipAddress", title:"Ship Address" },
                { field: "ShipName", title: "Ship Name", width: "190px" }
                ]
            };
        };
    }
</script>


</body>
</html>
