<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Indianstage Admin - ${product.name }</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- 
        <link rel="stylesheet" href="/kendo-ui/content/integration/bootstrap-integration/css/bootstrap.min.css" />
        <link href="http://cdn.kendostatic.com/2014.2.1008/styles/kendo.common-bootstrap.min.css" rel="stylesheet" />
        <link href="http://cdn.kendostatic.com/2014.2.1008/styles/kendo.bootstrap.min.css" rel="stylesheet" />
        <link href="http://cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="http://cdn.kendostatic.com/2014.2.1008/styles/kendo.dataviz.bootstrap.min.css" rel="stylesheet" />

        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script src="http://cdn.kendostatic.com/2014.2.1008/js/kendo.all.min.js"></script>
        <script src="http://cdn.kendostatic.com/2014.2.1008/js/kendo.timezones.min.js"></script>

        <script src="/kendo-ui/content/shared/js/theme-chooser.js"></script>

        <link rel="stylesheet" href="/kendo-ui/content/integration/bootstrap-integration/styles.css" />
 -->        

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
        
        <script>var ctx = "${pageContext.request.contextPath}"</script>
        
        <script type="text/javascript">
        $(document).ready(function(){
        	$('input[type=text]').val('');
        	$('input[type=radio]').checked=false;
        	$('input[type=checkbox]').checked=false;
        });
        </script>
    </head>
    <body>
        <!-- Google Tag Manager -->
        <noscript>
            <iframe src="//www.googletagmanager.com/ns.html?id=GTM-6X92" height="0" width="0" style="display: none; visibility: hidden"></iframe>
        </noscript>
        <script>(function(w, d, s, l, i) { w[l] = w[l] || []; w[l].push({ 'gtm.start': new Date().getTime(), event: 'gtm.js' }); var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : ''; j.async = true; j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl; f.parentNode.insertBefore(j, f); })(window, document, 'script', 'dataLayer', 'GTM-6X92');</script>
        <!-- End Google Tag Manager -->
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

        <div id="example" class="container">
            <ul id="menu">
                <li><a href="#profile">Profile</a></li>
                <li><a href="#schedule">Schedule</a></li>
                <li><a href="#orders">Orders</a></li>
                <li><a href="#gallery">Gallery</a></li>
                <li><a href="#faq">FAQ</a></li>
                <li><a href="http://indianstage.in">Indianstage</a></li>
            </ul>

            <div class="row clearfix">
                <div class="col-lg-4">
                    <section id="profile" class="well">
                        <h2 class="ra-well-title">Profile</h2>

                        <div class="row">
                            <div class="col-lg-5 col-sm-2">
                                <img src="/kendo-ui/content/integration/bootstrap-integration/avatar.jpg" class="ra-avatar img-responsive" />
                            </div>

                            <div class="col-lg-7 col-sm-2">
                                <span class="ra-first-name">Varun Kumar</span>
                                <span class="ra-last-name">Dodsworth</span>
                                <div class="ra-position">Inside Sales Coordinator</div>
                            </div>
                        </div>
                    </section>
                </div>

                <div class="col-lg-8">
                    <div id="tabstrip" class="ra-section">
                        <ul>
                            <li class="k-state-active"><span class="km-icon revenue"></span><span class="hidden-xs">Revenue</span></li>
                            <li><span class="km-icon spd"></span><span class="hidden-xs">Sales / day</span></li>
                            <li><span class="km-icon spr"></span><span class="hidden-xs">Sales / region</span></li>
                        <li><span class="km-icon share"></span><span class="hidden-xs">Market share</span></li>
                        </ul>
                        <div><div id="revenue"></div></div>
                        <div><div id="sales-per-day"></div></div>
                        <div><div id="sales-per-region"></div></div>
                        <div>
                            <div id="market-alice-mutton" class="market-donut"></div>
                            <div id="market-gravad-lax" class="market-donut"></div>
                            <div id="market-inlagd-sill" class="market-donut"></div>
                            <div id="market-spegesild" class="market-donut"></div>
                        </div>
                    </div>
                </div>
            </div>

            <section class="well">
                <h2 class="ra-well-title">Profile Setup</h2>

                <div class="form-horizontal form-widgets col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="name">Title</label>
                        <div class="col-sm-8 col-md-6">
                            <input id="name" value="${product.name }" autocomplete="off" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="birthday">Birthday</label>
                        <div class="col-sm-8 col-md-6">
                            <input type="date" value="10/09/1979" autocomplete="off" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="gender">Go Live</label>
                        <div class="col-sm-8 col-md-6">
                        	<c:choose>
                            	<c:when test="${product.status eq '1' }">
                            		<input type="checkbox" name="status" id="status" value="1" autocomplete="off" checked="checked" />
                            	</c:when>
                            	<c:otherwise>
                            		<input type="checkbox" name="status" id="status" value="1" autocomplete="off" />
                            	</c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="display-rule">Display Rule</label>
                        <div class="col-sm-8 col-md-6">
                            <select id="display-rule" autocomplete="off">
                            	<option selected value="${displayRuleSelected }">${displayRuleSelected }</option>
		                    	<c:forEach items="${displayRules }" var="drule">
		                    		<c:if test="${drule ne displayRuleSelected }">
		                    			<option value="${drule }">${drule }</option>
		                    		</c:if>
		                    	</c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="rating">Rating</label>
                        <div class="col-sm-8 col-md-6">
                            <input id="rating" type="number" value="${product.rating }" autocomplete="off" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="director">Director</label>
                        <div class="col-sm-8 col-md-6">
                            <input id="director" value="${product.custom2 }" autocomplete="off" />
                        </div>
                    </div>
                </div>

                <div class="form-horizontal form-widgets col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="genre">Genre</label>
                        <div class="col-sm-8 col-md-6">
                            <select id="genre" multiple autocomplete="off">
                                <c:forEach items="${genresSelected }" var="genre">
	                    			<option selected value="${genre.id }">${genre.name }</option>
		                    	</c:forEach>
		                    	<c:forEach items="${genres }" var="genre">
	                    			<option value="${genre.id }">${genre.name }</option>
		                    	</c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="language">Language</label>
                        <div class="col-sm-8 col-md-6">
                            <select id="language" multiple autocomplete="off">
                                <c:forEach items="${langsSelected }" var="lang">
	                    			<option selected value="${lang.id }">${lang.name }</option>
		                    	</c:forEach>
		                    	<c:forEach items="${langs }" var="lang">
	                    			<option value="${lang.id }">${lang.name }</option>
		                    	</c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="language">City</label>
                        <div class="col-sm-8 col-md-6">
                            <select id="event-city" name="event-city" autocomplete="off"></select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="language">Venue</label>
                        <div class="col-sm-8 col-md-6">
                            <select id="event-venue" name="event-venue" disabled="disabled" autocomplete="off"></select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="duration">Duration</label>
                        <div class="col-sm-8 col-md-6">
                            <input id="duration" type="number" value="${product.weight }" autocomplete="off" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="event-organiser">Organiser</label>
                        <div class="col-sm-8 col-md-6">
                        	<input id="event-organiser" name="event-organiser" autocomplete="off" />
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="form-horizontal form-widgets col-sm-12">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="bio">Description</label>
                        <div class="col-sm-10">
                            <textarea id="bio" autocomplete="off">${product.description }</textarea>
                        </div>
                    </div>
                </div>

                <div class="buttons-wrap">
                    <button class="k-button k-state-default">Cancel</button>
                    <button class="k-button k-state-default">Update</button>
                </div>
            </section>

            <div id="orders" class="ra-section"></div>

            <div id="schedule" class="ra-section"></div>

            <section id="gallery" class="well">
                <h2 class="ra-well-title">Latest photo uploads</h2>

                <script id="listview-template" type="x-kendo-template">
                    <li class="col-xs-6 col-md-4 col-lg-2">
                        <figure>
                            <img src="/kendo-ui/content/integration/bootstrap-integration/photos/#= id #.jpg" class="img-responsive" />
                            <figcaption class="hidden-xs">
                                <h4>#: title #</h4>
                                <p class="hidden-sm">
                                    #: description #
                                </p>
                            </figcaption>
                        </figure>
                    </li>
                </script>

                <ul id="listview" class="ra-well-overlay row"></ul>
            </section>

            <section id="faq" class="well">
                <h2 class="ra-well-title"><abbr title="Frequently Asked Questions">FAQ</abbr></h2>

                <ul id="panelbar" class="ra-well-overlay">
                    <li class="k-state-active">
                        What is Kendo UI?
                        <div>
                            <p>Kendo UI is a HTML5, jQuery-based framework for building modern HTML apps. Kendo UI combines the best of emerging HTML5, CSS3, and JavaScript technologies with robust, cross-browser techniques to deliver a framework that is both powerfully rich and broadly compatible with older browsers.</p>
                            <p>Kendo UI combines everything that a developer needs to build a rich JavaScript app, eliminating the traditional challenge of manually researching and combining all of the needed plug-ins, frameworks, and libraries needed to build apps. Kendo UI includes rich UI controls for desktop, mobile and data vizualization, a JavaScript DataSource, fast Templates, cross-device Drag and Drop API, Globalization, Validation and MVVM framework extensions, and more.</p>
                            <p><a href="http://www.telerik.com/download/kendo-ui-complete">Download Kendo UI</a> and experience the difference today</p>
                        </div>
                    </li>
                    <li>
                        Who should use Kendo UI?
                        <div>
                            <p>Kendo UI is for all HTML developers. Kendo UI can be used with (or without) any server-side technology, making it the perfect front-end for every modern, browser-based HTML app. Kendo UI only requires jQuery. After that, everything that's needed is included.</p>
                        </div>
                    </li>
                    <li>
                        What are Kendo UI widgets?
                        <div>
                            <p>Kendo UI widgets are part of the Kendo UI Framework. Kendo UI widgets are grouped into three collections:</p>
                            <ul>
                                <li>Kendo UI Web for touch-enabled desktop development</li>
                                <li>Kendo UI DataViz for desktop and mobile data vizualizations</li>
                                <li>Kendo UI Mobile for mobile app development, including PhoneGap deployment</li>
                            </ul>
                            <p>All widgets complement the tooling you need for modern HTML5 and JavaScript development.<br /> <br /> The Kendo UI Framework provides more than UI, and in the future Kendo UI will offer additional tools to modern HTML developers.</p>
                        </div>
                    </li>
                    <li>
                        How do you use Kendo UI?
                        <div>
                            <p>Kendo UI is a pure JavaScript framework, so using Kendo UI is as simple as referencing JavaScript and CSS resources on your page. Once Kendo UI is configured on your page, the rich API gives you everything you need to initialize and configure an application.</p>
                            <p>To learn more about using and installing Kendo UI, visit the Kendo UI&nbsp;<a href="http://demos.telerik.com/kendo-ui">online demos</a>.</p>
                        </div>
                    </li>
                </ul>
            </section>

            <footer>Copyright &copy; 2013 <a href="http://telerik.com/">Telerik Inc</a>. All rights reserved.</footer>
        </div>

        <script>
            $(document).ready(function(){
                // init page controls

                function path(url) {
                    return /(.*\/)[^\/]*$/gi.exec(url)[1];
                }

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
                        { text: "Flat", value: "flat" }
                    ],
                    change: function(e) {
                        theme = this.value();
                        window.kendoThemeChooser.changeTheme(theme, true);
                        initCharts(theme);
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

                // init sample widgets
                var serviceBaseUrl = "http://demos.telerik.com/kendo-ui/service";

                $("#menu").kendoMenu();

                function resizeTabStripContent() {
                    kendo.resize("#tabstrip");
                }

                $("#tabstrip").kendoTabStrip({
                    animation: {
                        open: { effects: "fadeIn" }
                    }
                });

                // resize nested charts when window resizes
                $(window).resize(resizeTabStripContent);

                function initCharts(theme) {
                    $(".k-tabstrip .k-content .k-chart").empty().each(function() {
                        $(this).removeClass(".k-chart").removeData();
                    });

                    $("#sales-per-day").kendoChart({
                        transitions: false,
                        theme: theme,
                        chartArea: {
                            margin: {
                                right: 10
                            },
                            background: "transparent"
                        },
                        legend: {
                            visible: false
                        },
                        seriesDefaults: {
                            type: "verticalBullet"
                        },
                        axisDefaults: {
                            categories: ["Oct 1", "Oct 2", "Oct 3", "Oct 4", "Oct 5", "Oct 6",
                                         "Oct 7", "Oct 8", "Oct 9", "Oct 10", "Oct 11", "Oct 12",
                                         "Oct 13", "Oct 14", "Oct 15", "Oct 16", "Oct 17", "Oct 18",
                                         "Oct 19", "Oct 20", "Oct 21", "Oct 22", "Oct 23", "Oct 24",
                                         "Oct 25", "Oct 26", "Oct 27", "Oct 28", "Oct 29", "Oct 30", "Oct 31",
                                         "Nov 1", "Nov 2", "Nov 3", "Nov 4", "Nov 5", "Nov 6",
                                         "Nov 7", "Nov 8", "Nov 9", "Nov 10", "Nov 11", "Nov 12",
                                         "Nov 13", "Nov 14", "Nov 15", "Nov 16", "Nov 17", "Nov 18",
                                         "Nov 19", "Nov 20", "Nov 21", "Nov 22", "Nov 23", "Nov 24",
                                         "Nov 25", "Nov 26", "Nov 27", "Nov 28", "Nov 29", "Nov 30",],
                            line: {
                                visible: false
                            }
                        },
                        series: [{
                            name: "All Products",
                            data: [[1903, 5000], [2398, 5250], [1893, 5500], [3452, 5750], [6567, 6000], [5892, 6250], [2354, 6500],
                                    [7238, 6750], [11574, 7000], [6392, 7250], [8932, 7500], [9320, 7750], [7894, 8000],
                                    [9456, 8250], [12745, 8500], [16705, 8750], [19802, 9000], [15076, 9250], [17892, 9500],
                                    [12983, 9750], [9034, 10000], [8902, 10250], [7893, 10500], [14562, 10750], [10235, 11000],
                                    [23901, 11250], [17892, 11500], [11982, 11750], [1093, 12000], [4598, 12250], [3457, 12500],
                                    [6092, 12750], [7892, 13000], [14562, 13250], [13200, 13500], [16502, 13750], [18902, 14000],
                                    [16702, 14250], [10946, 14500], [12093, 14750], [19704, 15000], [17903, 15250], [17892, 15500],
                                    [16783, 15750], [9845, 16000], [22904, 16250], [8934, 16500], [12983, 16750], [22876, 17000],
                                    [20981, 17250], [21873, 17500], [24981, 17750], [23873, 18000], [18376, 18000], [21783, 18000],
                                    [15672, 18000], [19456, 18000], [17998, 18000], [21673, 18000], [18234, 18000],[26932, 18000]
                                   ]
                        }],
                        valueAxis: {
                            labels: {
                                step: 2
                            },
                            plotBands: [{
                                from: 10000, to: 20000, color: "#ff0000", opacity: .05
                            }]
                        },
                        categoryAxis: {
                            labels: {
                                visible: false
                            },
                            majorGridLines: {
                                visible: false
                            }
                        },
                        tooltip: {
                            visible: true
                        }
                    });

                    $("#sales-per-region").kendoChart({
                        transitions: false,
                        theme: theme,
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 10,
                                right: 8,
                                bottom: 0,
                                left: 10
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "area",
                            area: {
                                line: {
                                    style: "smooth"
                                }
                            },
                            stack: true
                        },
                        axisDefaults: {
                            categories: ["Oct 1", "Oct 2", "Oct 3", "Oct 4", "Oct 5", "Oct 6",
                                         "Oct 7", "Oct 8", "Oct 9", "Oct 10", "Oct 11", "Oct 12",
                                         "Oct 13", "Oct 14", "Oct 15", "Oct 16", "Oct 17", "Oct 18",
                                         "Oct 19", "Oct 20", "Oct 21", "Oct 22", "Oct 23", "Oct 24",
                                         "Oct 25", "Oct 26", "Oct 27", "Oct 28", "Oct 29", "Oct 30", "Oct 31",
                                         "Nov 1", "Nov 2", "Nov 3", "Nov 4", "Nov 5", "Nov 6",
                                         "Nov 7", "Nov 8", "Nov 9", "Nov 10", "Nov 11", "Nov 12",
                                         "Nov 13", "Nov 14", "Nov 15", "Nov 16", "Nov 17", "Nov 18",
                                         "Nov 19", "Nov 20", "Nov 21", "Nov 22", "Nov 23", "Nov 24",
                                         "Nov 25", "Nov 26", "Nov 27", "Nov 28", "Nov 29", "Nov 30",],
                            line: {
                                visible: false
                            }
                        },
                        series: [{
                            name: "Canterbury",
                            data: [345, 823, 672, 1200, 3456, 2901, 800, 4562,
                                    6721, 2453, 4521, 6712, 2891, 2932, 6721,
                                    7812, 3742, 7812, 7892, 8912, 4525, 5682,
                                    2453, 7562, 2435, 6781, 7891, 8991, 200,
                                    2000, 1892, 4891, 4352, 1234, 7891, 3921,
                                    8912, 6781, 8787, 8991, 5782, 10982, 12634,
                                    6891, 1892, 14512, 4561, 2573, 9347, 7792,
                                    8826, 7935, 19234, 7724, 9001, 8764, 13562,
                                    15671, 18924, 10992, 3646]
                        },{
                            name: "Machester",
                            data: [524, 782, 891, 901, 2123, 278, 892, 872, 2003,
                                    2010, 2831, 809, 1093, 2003, 3007, 5009, 4006,
                                    3005, 5002, 2032, 3094, 2893, 3456, 4213, 4567,
                                    6538, 4652, 1234, 234, 756, 234, 432, 678, 4567,
                                    2347, 4623, 6578, 4662, 1040, 1909, 3495, 2398,
                                    2654, 5842, 3556, 6233, 2613, 3452, 6772, 2553,
                                    4772, 7889, 2374, 7856, 2345, 4563, 3456, 1436,
                                    1123, 3445, 3456]
                        },{
                            name: "Rochester",
                            data: [1034, 793, 330, 1351, 988, 2713, 662, 1804, 2850,
                                    1929, 1580, 1799, 3910, 4521, 3017, 3884, 12054,
                                    4259, 4998, 2039, 1415, 327, 1984, 2787, 3233,
                                    10582, 5349, 1757, 659, 1842, 1331, 769, 2862,
                                    8761, 2962, 7958, 3412, 5259, 1119, 1193, 10427,
                                    4523, 2604, 4050, 4397, 2159, 1760, 6958, 6757,
                                    10636, 8275, 9157, 2265, 2796, 10437, 2345, 2438,
                                    891, 1626, 3797, 19830]
                        }],
                        valueAxis: {
                            labels: {
                                step: 2
                            }
                        },
                        categoryAxis: {
                            labels: {
                                visible: false
                            },
                            majorGridLines: {
                                visible: false
                            }
                        },
                        tooltip: {
                            visible: true,
                            template: "#= series.name # <br /> #= category #: #= value #"
                        }
                    });

                    $("#revenue").kendoChart({
                        transitions: false,
                        theme: theme,
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 10,
                                right: 5,
                                bottom: 0,
                                left: 10
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "column",
                            style: "smooth",
                            stack: true,
                            width: 2,
                            markers: {
                                visible: false
                            }
                        },

                        axisDefaults: {
                            categories: ["Oct 1", "Oct 2", "Oct 3", "Oct 4", "Oct 5", "Oct 6",
                                         "Oct 7", "Oct 8", "Oct 9", "Oct 10", "Oct 11", "Oct 12",
                                         "Oct 13", "Oct 14", "Oct 15", "Oct 16", "Oct 17", "Oct 18",
                                         "Oct 19", "Oct 20", "Oct 21", "Oct 22", "Oct 23", "Oct 24",
                                         "Oct 25", "Oct 26", "Oct 27", "Oct 28", "Oct 29", "Oct 30", "Oct 31",
                                         "Nov 1", "Nov 2", "Nov 3", "Nov 4", "Nov 5", "Nov 6",
                                         "Nov 7", "Nov 8", "Nov 9", "Nov 10", "Nov 11", "Nov 12",
                                         "Nov 13", "Nov 14", "Nov 15", "Nov 16", "Nov 17", "Nov 18",
                                         "Nov 19", "Nov 20", "Nov 21", "Nov 22", "Nov 23", "Nov 24",
                                         "Nov 25", "Nov 26", "Nov 27", "Nov 28", "Nov 29", "Nov 30",],
                            line: {
                                visible: false
                            }
                        },
                        series: [{
                            name: "Canterbury",
                            data: [686.55, 1637.77, 1337.28, 2388, 6877.44, 5772.99, 1592, 9078.38,
                                    13374.79, 4881.47, 8996.79, 13356.88, 5753.09, 5834.68, 13374.79,
                                    15545.88, 7446.58, 15545.88, 15705.08, 17734.88, 9004.75, 11307.18,
                                    4881.47, 15048.38, 4845.65, 13494.19, 15703.09, 17892.09, 398, 3980,
                                    3765.08, 9733.09, 8660.48, 2455.66, 15703.09, 7802.79, 17734.88,
                                    13494.19, 17486.13, 17892.09, 11506.18, 21854.18, 25141.66, 13713.09,
                                    3765.08, 28878.88, 9076.39, 5120.27, 18600.53, 15506.08, 17563.74,
                                    15790.65, 38275.66, 15370.76, 17911.99, 17440.36, 26988.38, 31185.29,
                                    37658.76, 21874.08, 7255.54]
                        },{
                            name: "Machester",
                            data: [1199.96, 1790.78, 2040.39, 2063.29, 4861.67, 636.62, 2042.68, 1996.88,
                                    4586.87, 4602.9, 6482.99, 1852.61, 2502.97, 4586.87, 6886.03, 11470.61,
                                    9173.74, 6881.45, 11454.58, 4653.28, 7085.26, 6624.97, 7914.24, 9647.77,
                                    10458.43, 14972.02, 10653.08, 2825.86, 535.86, 1731.24, 535.86, 989.28,
                                    1552.62, 10458.43, 5374.63, 10586.67, 15063.62, 10675.98, 2381.6, 4371.61,
                                    8003.55, 5491.42, 6077.66, 13378.18, 8143.24, 14273.57, 5983.77, 7905.08,
                                    15507.88, 5846.37, 10927.88, 18065.81, 5436.46, 17990.24, 5370.05,
                                    10449.27, 7914.24, 3288.44, 2571.67, 7889.05, 7914.24]
                        },{
                            name: "Rochester",
                            data: [1540.66, 1181.57, 491.7, 2012.99, 1472.12, 4042.37, 986.38, 2687.96, 4246.5,
                                    2874.21, 2354.2, 2680.51, 5825.9, 6736.29, 4495.33, 5787.16, 17960.46,
                                    6345.91, 7447.02, 3038.11, 2108.35, 487.23, 2956.16, 4152.63, 4817.17,
                                    15767.18, 7970.01, 2617.93, 981.91, 2744.58, 1983.19, 1145.81, 4264.38,
                                    13053.89, 4413.38, 11857.42, 5083.88, 7835.91, 1667.31, 1777.57, 15536.23,
                                    6739.27, 3879.96, 6034.5, 6551.53, 3216.91, 2622.4, 10367.42, 10067.93,
                                    15847.64, 12329.75, 13643.93, 3374.85, 4166.04, 15551.13, 3494.05, 3632.62,
                                    1327.59, 2422.74, 5657.53, 29546.7]
                        }],
                        valueAxis: {
                            labels: {
                                step: 2,
                                template: "$#= value #"
                            },
                            plotBands: [{
                                from: 30000, to: 50000, color: "#a7c9e6", opacity: .3
                            }]
                        },
                        categoryAxis: {
                            labels: {
                                visible: false
                            },
                            majorGridLines: {
                                visible: false
                            }
                        },
                        tooltip: {
                            visible: true,
                            template: "#= series.name # <br /> #= category #: $#= value #"
                        }
                    });


                    $("#market-alice-mutton").kendoChart({
                        transitions: false,
                        theme: theme,
                        title: {
                            text: "Alice Mutton",
                            position: "bottom",
                            padding: 4,
                            margin: 0
                        },
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 15,
                                bottom: 0
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "donut"
                        },
                        series: [{
                            name: "2011",
                            data: [{ category: "Canterbury", value: 30},
                                   { category: "Manchester", value: 45},
                                   { category: "Rochester", value: 25}],
                                    padding: 0
                        },{
                            name: "2012",
                            data: [{ category: "Canterbury", value: 64},
                                   { category: "Manchester", value: 12},
                                   { category: "Rochester", value: 24}]
                        }],
                        tooltip: {
                            visible: true,
                            template: "#= category # (#= series.name #): #= value #%"
                        }
                    });

                    $("#market-gravad-lax").kendoChart({
                        transitions: false,
                        theme: theme,
                        title: {
                            text: "Gravad lax",
                            position: "bottom",
                            padding: 4,
                            margin: 0
                        },
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 15,
                                bottom: 0
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "donut"
                        },
                        series: [{
                            name: "2011",
                            data: [{ category: "Canterbury", value: 22},
                                   { category: "Manchester", value: 18},
                                   { category: "Rochester", value: 60}],
                                    padding: 0
                        },{
                            name: "2012",
                            data: [{ category: "Canterbury", value: 35},
                                   { category: "Manchester", value: 20},
                                   { category: "Rochester", value: 45}]
                        }],
                        tooltip: {
                            visible: true,
                            template: "#= category # (#= series.name #): #= value #%"
                        }
                    });

                    $("#market-inlagd-sill").kendoChart({
                        transitions: false,
                        theme: theme,
                        title: {
                            text: "Inlagd Sill",
                            position: "bottom",
                            padding: 4,
                            margin: 0
                        },
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 15,
                                bottom: 0
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "donut"
                        },
                        series: [{
                            name: "2011",
                            data: [{ category: "Canterbury", value: 30},
                                   { category: "Manchester", value: 21},
                                   { category: "Rochester", value: 49}],
                                    padding: 0
                        },{
                            name: "2012",
                            data: [{ category: "Canterbury", value: 32},
                                   { category: "Manchester", value: 25},
                                   { category: "Rochester", value: 43}]
                        }],
                        tooltip: {
                            visible: true,
                            template: "#= category # (#= series.name #): #= value #%"
                        }
                    });

                    $("#market-spegesild").kendoChart({
                        transitions: false,
                        theme: theme,
                        title: {
                            text: "Spegesild",
                            position: "bottom",
                            padding: 4,
                            margin: 0
                        },
                        legend: {
                            visible: false
                        },
                        chartArea: {
                            margin: {
                                top: 15,
                                bottom: 0
                            },
                            background: "transparent"
                        },
                        seriesDefaults: {
                            type: "donut"
                        },
                        series: [{
                            name: "2011",
                            data: [{ category: "Canterbury", value: 37},
                                   { category: "Manchester", value: 42},
                                   { category: "Rochester", value: 21}],
                                    padding: 0
                        },{
                            name: "2012",
                            data: [{ category: "Canterbury", value: 32},
                                   { category: "Manchester", value: 30},
                                   { category: "Rochester", value: 38}]
                        }],
                        tooltip: {
                            visible: true,
                            template: "#= category # (#= series.name #): #= value #%"
                        }
                    });
                }

                initCharts("bootstrap");

                $("#orders").kendoGrid({
                    dataSource: {
                        type: "odata",
                        transport: {
                            read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Orders"
                        },
                        serverPaging: true,
                        serverSorting: true,
                        serverFiltering: true,
                        pageSize: 10
                    },
                    scrollable: false,
                    sortable: true,
                    groupable: true,
                    pageable: { buttonCount: 4 },
                    columns: [
                        { field: "OrderID", width: "70px" },
                        { field: "ShipCountry", title:"Ship Country", width: "20%" },
                        { field: "ShipAddress", title:"Ship Address" }
                    ]
                });

                $("#schedule").kendoScheduler({
                    date: new Date("2013/6/13"),
                    startTime: new Date("2013/6/13 07:00 AM"),
                    endTime: new Date("2013/6/13 08:00 PM"),
                    views: [
                        { type: "day", selected: true },
                        "week",
                        "month"
                    ],
                    editable: false,
                    timezone: "Etc/UTC",
                    dataSource: {
                        batch: true,
                        transport: {
                            read: {
                                url: serviceBaseUrl + "/meetings",
                                dataType: "jsonp"
                            },
                            update: {
                                url: serviceBaseUrl + "/meetings/update",
                                dataType: "jsonp"
                            },
                            create: {
                                url: serviceBaseUrl + "/meetings/create",
                                dataType: "jsonp"
                            },
                            destroy: {
                                url: serviceBaseUrl + "/meetings/destroy",
                                dataType: "jsonp"
                            },
                            parameterMap: function(options, operation) {
                                if (operation !== "read" && options.models) {
                                    return {models: kendo.stringify(options.models)};
                                }
                            }
                        },
                        schema: {
                            model: {
                                id: "meetingID",
                                fields: {
                                    meetingID: { from: "MeetingID", type: "number" },
                                    title: { from: "Title", defaultValue: "No title", validation: { required: true } },
                                    start: { type: "date", from: "Start" },
                                    end: { type: "date", from: "End" },
                                    startTimezone: { from: "StartTimezone" },
                                    endTimezone: { from: "EndTimezone" },
                                    description: { from: "Description" },
                                    recurrenceId: { from: "RecurrenceID" },
                                    recurrenceRule: { from: "RecurrenceRule" },
                                    recurrenceException: { from: "RecurrenceException" },
                                    roomId: { from: "RoomID", nullable: true },
                                    atendees: { from: "Atendees", nullable: true },
                                    isAllDay: { type: "boolean", from: "IsAllDay" }
                                }
                            }
                        }
                    }
                });

                $("#listview").kendoListView({
                    dataSource: {
                        data: [
                            { id: 1, title: "Sunrise", description: "And what a great July morning it was..." },
                            { id: 2, title: "Fisherman", description: "Fishing on the north shore" },
                            { id: 3, title: "Green Forest", description: "Summer is everywhere" },
                            { id: 4, title: "Cactus", description: "A yellow one..." },
                            { id: 5, title: "Moss", description: "Nice mossy green" },
                            { id: 6, title: "Red Boat", description: "In the middle of nowhere" },
                            { id: 7, title: "Country Road", description: "On my way home" },
                            { id: 8, title: "Bloom", description: "Cherry blossoms in full bloom" },
                            { id: 9, title: "San Francisco", description: "San Francisco by sunset" },
                            { id: 10, title: "Peaks", description: "Was a great holiday" },
                            { id: 11, title: "Thunderstorm", description: "Midnight storm on its way" },
                            { id: 12, title: "Leafs", description: "Autumn leafs on the patio" }
                        ]
                    },
                    template: $("#listview-template").html()
                });

                $("#panelbar").kendoPanelBar();

                $("#example .form-widgets")
                    .find("select:not([multiple])").kendoDropDownList().end()
                    .find("select[multiple]").kendoMultiSelect().end()
                    .find("input:not([type])").addClass("k-textbox").end()
                    .find("input[type=date]").kendoDatePicker().end()
                    .find("input[type=number]").kendoNumericTextBox();
                
                $("#duration").kendoNumericTextBox({
                    format: "# minutes",
                    min: 1,
                    step: 5
                })
                
                $("#rating").kendoNumericTextBox({
                    format: "#.00",
                    min: 1.00,
                    max: 9.00,
                    step: 0.50,
                    decimals: 2
                })

                $("#example textarea").kendoEditor({
                    tools: [
                        "formatting",
                        "bold", "italic", "underline",
                        "strikethrough", "subscript", "superscript",
                        "justifyLeft", "justifyCenter", "justifyRight", "justifyFull",
                        "insertUnorderedList", "insertOrderedList", "indent", "outdent"
                    ]
                });
                
                var eventCity;
                try {
                	eventCity = $("#event-city").kendoDropDownList({
                        optionLabel: "Select city...",
                        dataTextField: "text",
                        dataValueField: "value",
                        dataSource: {
                            transport: {
                                read: ctx + "/rest/manufacturer/read/all",
                                dataType: "json",
                                data: { }
                            },
                            schema: {
                                data: "results"
                            }
                        }
                    }).data("kendoDropDownList");
				} catch (e) {
					console.log(e.toString());
				}

                try {
                	var eventVenue = $("#event-venue").kendoDropDownList({
                        autoBind: false,
                        cascadeFrom: "event-city",
                        optionLabel: "Select venue...",
                        dataTextField: "text",
                        dataValueField: "value",
                        dataSource: {
                            transport: {
                                read: function() { return ctx + "/rest/venue/read/all?$filter=id eq " + eventCity.value() },
                                dataType: "json",
                            },
                            schema: {
                                data: "results"
                            }
                        }
                    }).data("kendoDropDownList");
				} catch (e) {
					console.log(e.toString());
				}
                
                var eventOrganiser = $("#event-organiser").kendoAutoComplete({
                    dataTextField: "ProductName",
                    filter: "contains",
                    minLength: 3,
                    dataSource: {
                        type: "odata",
                        serverFiltering: true,
                        transport: {
                            read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Products"
                        }
                    }
                }).data("kendoAutoComplete");
                
            	var director = $("#director").kendoAutoComplete({
                    dataTextField: "ProductName",
                    filter: "contains",
                    minLength: 3,
                    dataSource: {
                        type: "odata",
                        serverFiltering: true,
                        transport: {
                            read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Products"
                        }
                    }
                }).data("kendoAutoComplete");
            });
        </script>
    </body>
</html>
