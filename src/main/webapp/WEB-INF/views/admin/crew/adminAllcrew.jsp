<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_admin.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<div class="container">
	<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
	 	<br><br>
        <h2 class="display-5 mb-5">산타 크루 통계</h2>
    </div>
	<canvas id="line-chart"></canvas>
	<br>
	<table class="table table-dark" width="80%">
		<tr align="center">
			<th>전체 크루</th>
			<th>전체 일일 크루</th>
			<th>전체 정기 크루</th>
			<th>전체 플로깅크루</th>
			<th>전체 등산 크루</th>
		</tr>
		<tr align="center">
			<td>${list.totalcrew}</td>
			<td>${list.onedaycrew}</td>
			<td>${list.regularcrew}</td>
			<td>${list.ploggingcrew}</td>
			<td>${list.mountaincrew}</td>
		</tr>
	</table>
	<br>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		getGraph();
	});
	
	function getGraph(){
		var ctx = document.getElementById('line-chart').getContext('2d');
		var datalist=[];
		var labellist=["전체 크루", "전체 1일 크루", "전체 정기 크루", "전체 플로깅크루", "전체 등산 크루"];
		$.ajax({
			url : '/crewgraph/admin/crew.grp',
			success : function(data){
				//alert(data);
				$.each(data,function(index, value){
					datalist.push(value.totalcrew);
					datalist.push(value.onedaycrew);
					datalist.push(value.regularcrew);
					datalist.push(value.ploggingcrew);
					datalist.push(value.mountaincrew);
				})
				var myChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: labellist,
				        datasets: [{
				            label: '크루 통계',
				            data: datalist,
				            backgroundColor: [
				                'rgba(255, 99, 132, 1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)'
				            ],
				            borderColor: [
				                'rgba(255, 99, 132, 1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero: true
				                }
				            }]
				        }
				    }
				});
			},//success
			error : function(error){
				alert('error');
			}
		})//ajax
	}//ready
	
	
</script>
<%@ include file ="../../common/common_bottom.jsp" %>
