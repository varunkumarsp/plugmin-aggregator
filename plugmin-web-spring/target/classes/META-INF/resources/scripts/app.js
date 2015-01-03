//Creates a gloabl object called templateLoader with a single method "loadExtTemplate"
    var templateLoader = (function($,host){
        //Loads external templates from path and injects in to page DOM
        return{
            //Method: loadExtTemplate
            //Params: (string) path: the relative path to a file that contains template definition(s)
            loadExtTemplate: function(path, callback, param1, param2){
                //Use jQuery Ajax to fetch the template file
                var tmplLoader = $.get(path)
                    .success(function(result){
                        //On success, Add templates to DOM (assumes file only has template definitions)
                        $("body").append(result);
                    })
                    .error(function(result){
                        alert("Error Loading Templates -- TODO: Better Error Handling");
                    })

                tmplLoader.complete(function(){
                    //Publish an event that indicates when a template is done loading
                    $(host).trigger("TEMPLATE_LOADED", [path, callback, param1, param2]);
                });
            }
        };
    })(jQuery, document);

  //Subscribe to event triggered when templates loaded
  //(Don't load use templates before they are available)
  $(document).bind("TEMPLATE_LOADED", function(e, path, callback, param1, param2) {
  	console.log('Template loaded: ' + path);

      //Compile and cache templates
      //var _itemTemplate = kendo.template($("#feedItemTemplate").html(),{useWithBlock:false});
      
      callback.call(param1, param2);
  });
  
function path(url) {
	return /(.*\/)[^\/]*$/gi.exec(url)[1];
}

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

$(document).ready(function(){
		$("#config-toggle").on('click', function() {
			$("#configurator").slideToggle();
		});
		
		$("#clear-components").on('click', function() {
			$.get(ctx + '/plugmin/rest/grid/components/clear', function(data, status) {
				console.log('Components cleared: ' + data);
			});
		});
		
        $("#dimensions").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            value: "common-bootstrap",
            dataSource: [
                { text: "Default", value: "common" },
                { text: "Bootstrap", value: "common-bootstrap" }
            ],
            change: function(e) {
                window.kendoThemeChooser.changeCommon(this.value(), true);
            }
        });

        $("#theme").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            value: "bootstrap",
            dataSource: [
                { text: "Default", value: "default" },
                { text: "Blue Opal", value: "blueopal" },
                { text: "Bootstrap", value: "bootstrap" },
                { text: "Silver", value: "silver" },
                { text: "Uniform", value: "uniform" },
                { text: "Metro", value: "metro" },
                { text: "Black", value: "black" },
                { text: "Metro Black", value: "metroblack" },
                { text: "High Contrast", value: "highcontrast" },
                { text: "Moonlight", value: "moonlight" },
                { text: "Flat", value: "flat" },
                { text: "Material", value: "material" },
                { text: "Material Black", value: "materialblack" }
            ],
            change: function(e) {
                theme = this.value();
                window.kendoThemeChooser.changeTheme(theme, true);
            }
        });

        function changeFontSize(e) {
            var value = $("#font-size").kendoDropDownList("value");

            $("body").css("font-size", value + "px");
        }

        $("#font-size").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            value: 14,
            height: 204,
            dataSource: [
                { text: "10px", value: 10 },
                { text: "12px", value: 12 },
                { text: "14px", value: 14 },
                { text: "16px", value: 16 },
                { text: "18px", value: 18 },
                { text: "20px", value: 20 }
            ],
            change: changeFontSize
        });

        changeFontSize();

        $("#theme-list, #dimensions-list, #font-size-list").addClass("ra-list");

        $("#configure").on("click", function(e) {
            $("#configurator-wrap").toggleClass("hidden-xs");
            e.preventDefault();
        });
    });