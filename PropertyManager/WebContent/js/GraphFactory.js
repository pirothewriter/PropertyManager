/**
 * 타입에 따른 그래프 생성 클래스 @ 신예슬
 */

var GraphFactory = function(chartdata , themeName , selectorName) {
	this.chartData = chartdata;
	this.themeName = themeName;
	this.selectorName = selectorName;
	this.chart = AmCharts.makeChart(this.selectorName, {
	    "type": "serial",
	    "theme": this.themeName,
	    "dataProvider": this.chartData,
	    "valueAxes": [{
	        "gridColor":"#FFFFFF",
			"gridAlpha": 0.2,
			"dashLength": 0
	    }],
	    "gridAboveGraphs": true,
	    "startDuration": 1,
	    "graphs": [{
	        "balloonText": "[[category]]: <b>[[value]]</b>",
	        "fillAlphas": 0.8,
	        "lineAlpha": 0.2,
	        "type": "column",
	        "valueField": "appAverage",
	        "colorField": "color"
	      
	    }],
	    "chartCursor": {
	        "categoryBalloonEnabled": false,
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "corp",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "gridAlpha": 0
	    }
	});
};

var GraphFactoryForVersion = function(chartdata , color) {
	this.chartData = chartdata;
	this.color=color;
}

GraphFactory.prototype.createLineTypeGraph = function(chart) {
	var chart = AmCharts
			.makeChart(
					this.selectorName,
					{
						"type" : "serial",
						"theme" : this.themeName,
						"pathToImages" : "http://www.amcharts.com/lib/3/images/",
						"dataProvider" : this.chartData,
						"colors" : [ "#FFBB00", "#6799FF", "#FF0000" ],
						"valueAxes" : [ {
							"axisAlpha" : 0.2,
							"dashLength" : 1,
							"position" : "left"
						} ],
						"graphs" : [
								{
									"id" : "tmonGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Tmon: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#FFBB00",
									"hideBulletsCount" : 50,
									"title" : "tmon",
									"valueField" : "tmon",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 3
								},
								{
									"id" : "coupangGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Coupang: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#6799FF",
									"hideBulletsCount" : 50,
									"title" : "coupang",
									"valueField" : "coupang",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 1
								},
								{
									"id" : "wemapGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Wemap: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#FF0000",
									"hideBulletsCount" : 50,
									"title" : "wemap",
									"valueField" : "wemap",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 1
								} ],
						"chartScrollbar" : {
							"autoGridCount" : true,
							"graph" : "g1",
							"scrollbarHeight" : 40
						},
						"chartCursor" : {
							"cursorPosition" : "mouse"
						},
						"categoryField" : "date",
						"categoryAxis" : {
							"parseDates" : false,
							"axisColor" : "#DADADA",
							"dashLength" : 1,
							"minorGridEnabled" : true
						}
					});
	
	return chart;
}




GraphFactory.prototype.createLineTypeGraphForDaily = function(chart) {
	var chart = AmCharts
			.makeChart(
					this.selectorName,
					{
						"type" : "serial",
						"theme" : this.themeName,
						"pathToImages" : "http://www.amcharts.com/lib/3/images/",
						"dataProvider" : this.chartData,
						"colors" : [ "#FFBB00", "#6799FF", "#FF0000" ],
						"valueAxes" : [ {
							"axisAlpha" : 0.2,
							"dashLength" : 1,
							"position" : "left",
							"reversed" : true
						} ],
						"graphs" : [
								{
									"id" : "tmonGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Tmon: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#FFBB00",
									"hideBulletsCount" : 50,
									"title" : "tmon",
									"valueField" : "tmon",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 3
								},
								{
									"id" : "coupangGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Coupang: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#6799FF",
									"hideBulletsCount" : 50,
									"title" : "coupang",
									"valueField" : "coupang",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 1
								},
								{
									"id" : "wemapGragh",
									"balloonText" : "<b><span style='font-size:14px;'>Wemap: [[value]]</span></b>",
									"bullet" : "round",
									"bulletBorderAlpha" : 1,
									"bulletColor" : "#FF0000",
									"hideBulletsCount" : 50,
									"title" : "wemap",
									"valueField" : "wemap",
									"useLineColorForBulletBorder" : true,
									"lineThickness" : 1
								} ],
						
						"chartCursor" : {
							"cursorPosition" : "mouse"
						},
						"categoryField" : "date",
						"categoryAxis" : {
							"parseDates" : false,
							"axisColor" : "#DADADA",
							"dashLength" : 1,
							"minorGridEnabled" : true
						}
					});
	
	return chart;
}



GraphFactory.prototype.createStackTypeGraph = function(chart) {
	var chart = AmCharts.makeChart(this.selectorName, {
		"titles":[{
			"text":"소셜3사의 최근 한달 리뷰개수"
		}],
	    "type": "serial",
	    "theme": this.themeName,
	    "dataProvider": this.chartData,
	    "valueAxes": [{
	        "gridColor":"#FFFFFF",
			"gridAlpha": 0.2,
			"dashLength": 0
	    }],
	    "gridAboveGraphs": true,
	    "startDuration": 1,
	    "graphs": [{
	        "balloonText": "[[category]]: <b>[[value]]</b>",
	        "fillAlphas": 0.8,
	        "lineAlpha": 0.2,
	        "type": "column",
	        "valueField": "appAverage",
	        "colorField": "color",
	        "labelText" : "[[value]]"
	      
	    }],
	    "chartCursor": {
	        "categoryBalloonEnabled": false,
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "corp",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "gridAlpha": 0
	    }
	});
	
	return chart;
}

GraphFactory.prototype.createStackTypeGraphForAverage = function(chart) {
	var chart = AmCharts.makeChart(this.selectorName, {
		"titles":[{
			"text":"소셜3사의 마켓 평균평점"
		}],
	    "type": "serial",
	    "theme": this.themeName,
	    "dataProvider": this.chartData,
	    "valueAxes": [{
	        "gridColor":"#FFFFFF",
			"gridAlpha": 0.2,
			"dashLength": 0,
			"maximum":5,
			"minimum":3
	    }],
	    "gridAboveGraphs": true,
	    "startDuration": 1,
	    "graphs": [{
	        "balloonText": "[[category]]: <b>[[value]]</b>",
	        "fillAlphas": 0.8,
	        "lineAlpha": 0.2,
	        "type": "column",
	        "valueField": "appAverage",
	        "colorField": "color",
	        "labelText" : "[[value]]"
	      
	    }],
	    "chartCursor": {
	        "categoryBalloonEnabled": false,
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "corp",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "gridAlpha": 0
	    }
	});
	
	return chart;
}


GraphFactory.prototype.createPieTypeGraph = function(chart) {
	
	var chart = AmCharts.makeChart(this.selectorName, {
		"titles":[{
			"text":"별점 분포도"
		}],
	    "type": "pie",
		"theme": this.themeName,
	    "dataProvider":this.chartData,
	   
	    "valueField": "value",
	    "titleField": "grade"
	});
	return chart;
}


GraphFactoryForVersion.prototype.createLineTypeGraphForVersion = function(chart) {
	var chart = AmCharts
	.makeChart(
			"appLinechartVersion",
			{
				"titles":[{
					"text":"버전별 평점"
				}],
				"type" : "serial",
				"theme" :"none",
				"pathToImages" : "http://www.amcharts.com/lib/3/images/",
				"dataProvider" : this.chartData,
				"colors" : [this.color],
				"valueAxes" : [ {
					"axisAlpha" : 0.2,
					"dashLength" : 1,
					"position" : "left",
					"maximum":5,
					"minimum":2
				} ],
				"graphs" : [
						{
							"id" : "gragh",
							"balloonText" : "<b><span style='font-size:14px;'>grade: [[grade]]</span></b>",
							"bullet" : "round",
							"bulletBorderAlpha" : 1,
							"bulletColor" : this.color,
							"hideBulletsCount" : 50,
							"valueField" : "grade",
							"useLineColorForBulletBorder" : true,
							"lineThickness" : 3
						}],
				
				"chartCursor" : {
					"cursorPosition" : "mouse"
				},
				"categoryField" : "version",
				"categoryAxis" : {
					"parseDates" : false,
					"axisColor" : "#DADADA",
					"dashLength" : 1,
					"minorGridEnabled" : true
				}
			});
	return chart;
}