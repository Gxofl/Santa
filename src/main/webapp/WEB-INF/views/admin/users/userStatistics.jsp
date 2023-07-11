<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_admin.jsp"%>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<body>

	<div id="myChart" style="width: 100%; max-width: 600px; height: 500px; margin: 0 auto; text-align: center;"></div>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script>

$(document).ready(function() {
	google.charts.load('current', { 'packages' : [ 'corechart' ]});
	google.charts.setOnLoadCallback(drawChart);
});

function drawChart() {
	
	$.ajax({
		url : '/users/admin/statsData1.us',
		type : 'post',
		data : {},
		async : false,				// async : false 비동기처리 / true:동기처리 
		success : function(data) {

			var chartData = new google.visualization.DataTable();
			chartData.addColumn('string', '성별');
			chartData.addColumn('number', '회원 수');

			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var getStr1 = data[i].SEX;
					var getStr2 = Number(data[i].GENDER);

					chartData.addRow([ getStr1, getStr2 ]);
				}

				var options = {
					title : '회원별 성별 통계',
					width : '600px',
					height : '400px',
					bar : {
						groupWidth : '75%'
					},
					legend : {
						position : 'center'
					},
					colors: ['#FF6384', '#36A2EB']
				};

				var chart = new google.visualization.ColumnChart(document.getElementById('myChart'));
				chart.draw(chartData, options);
				
			} else {
				alert("데이터가 없습니다.");
			}
		},
		error : function() {
			alert("에이작스 요청이 실패하였습니다.");
		}

	});
}

</script>
	
<%@ include file="../../common/common_bottom.jsp"%>
</body>
</html>







