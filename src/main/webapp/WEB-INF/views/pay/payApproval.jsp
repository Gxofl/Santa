<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	/* 결제 취소 버튼 눌렀을때 */
	function payCancel(paynum){
		//alert(paynum);
		var result = confirm('결제를 취소하시겠습니까?');
		if(result == true){
			location.href="/pay/user/cancel2.pay?paynum="+paynum;
		}
	}
</script>
<!-- Service Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <p class="fs-5 fw-bold text-primary">
                	<c:if test="${pay_result.state == 0}">정상적으로 결제 완료됐습니다.</c:if>
                	<c:if test="${pay_result.state == 1}">정상적으로 결제 승인완료 됐습니다.</c:if>
                	<c:if test="${pay_result.state == 2}">정상적으로 결제 취소됐습니다.</c:if>
                	<c:if test="${pay_result.state == 3}">관리자 요청에 따라 결제 취소됐습니다.</c:if>
                </p>
                <h1 class="display-5 mb-5">결제 내역</h1>
            </div>
            
            <table class="table table-hover">
            	<tr align="center">
            		<th>상품</th>
            		<th>상품명</th>
            		<th>결제일자</th>
            		<th>결제금액</th>
            		<th>결제방법</th>
            	</tr>
            	<tr align="center">
            		<!-- 대표 이미지 하나 가져오기 -->
					<c:set var="thumbnail" value="${fn:split(pb.image, ',')}" />
					<!-- //대표 이미지 하나 가져오기 -->
					
            		<td>
            			<a href="/products/user/detail.prd?num=${pay_result.prdnum}&pageNumber=${pageNumber}">
            				<img src="<%=request.getContextPath()%>/resources/images/products/${thumbnail[0]}" width="200" height="300">
            			</a>
            		</td>
            		<td>${pb.name}</td>
            		<td><fmt:formatDate value="${pay_result.regdate}" type="date" dateStyle="medium" timeStyle="medium" pattern="yyyy-MM-dd hh:mm:ss"/></td>
            		<td><fmt:formatNumber value="${pay_result.price}" pattern="###,###" />원</td>
            		<td>${pay_result.payment}</td>
            	</tr>
            </table>
            
            <div class="container">
            	<div class="text-center">
            		<!-- 관리자 결제 승인전에 취소가능함 -->
	            	<c:if test="${pay_result.state == 0}">
	            		<input type="button" class="btn btn-success" value="결제취소" onclick="javascript:payCancel(${pay_result.paynum})">
	            		<hr>
	            	</c:if>
	            	<!-- //관리자 결제 승인전에 취소가능함 -->
            	</div>
            	<div class="text-center">
            		<input type="button" class="btn btn-success" value="마이페이지" onclick="location.href='/pay/user/myPage.pay'">
            		<input type="button" class="btn btn-success" value="상품 목록보기" onclick="location.href='/products/all/list.prd'">
            	</div>
            </div>
        </div>
    </div>
    <!-- Service End -->

<%@ include file="../common/common_bottom.jsp" %>