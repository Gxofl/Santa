<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file ="../../common/common_top.jsp" %>

<%@ include file ="../../common/common_nav_myPage.jsp"%>

	<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">나의 판매 거래 내역</h1>
                <h5>내 포인트 : <fmt:formatNumber value="${Mypoint}" pattern="###,###"/> point</h5>
                <br><br><br>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         				<tr align="center">
                         					<th>번호</th>
                         					<th>상품명</th>
                         					<th>가격</th>
                         					<th>구매자</th>
                         					<th>결제 승인 여부</th>
                         				</tr>
                         			</thead>
                         			<c:if test="${sell_prd_list.size() == 0}">
                         				<tr align="center">
                         					<td colspan="5">
                         					거래 내역이 없습니다.
                         					</td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${sell_prd_list.size() != 0}">
	                         			<c:forEach var="sell_prd_list" items="${sell_prd_list}" varStatus="status">
		                         				<tr align="center">
		                         					<td>${status.count}</td>
		                         					<td><a href="/products/user/detail.prd?num=${sell_prd_list.productsnum}">${sell_prd_list.name}</a></td>
		                         					<td><fmt:formatNumber value="${sell_prd_list.price}" pattern="###,###" />원</td>
		                         					<td>${sell_prd_list.buyer}</td>
		                         					<td align="left">
		                         						<c:if test="${sell_prd_list.paystate == 0}">
		                         							승인 대기중
		                         						</c:if>
		                         						<c:if test="${sell_prd_list.paystate == 1}">
		                         							승인완료
		                         						</c:if>
		                         					</td>
		                         				</tr>
	                         			</c:forEach>
                         			 </c:if> 
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
	</div><!-- //container-fluid -->
    <!-- //findUserId End -->
    
<%@ include file ="../../common/common_bottom.jsp"%>