<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_admin.jsp" %>

	<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">결제 승인완료 내역 </h1>
        	</div>
                
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         	<table class="table table-hover table-borded align-middle">
                         		<thead>
                         			<tr align="center">
                         				<th>구매자 결제일</th>
                         				<th>관리자 결제 승인일</th>
                         				<th>상품명</th>
                         				<th>가격</th>
                         				<th>결제방법</th>
                         				<th>구매자 아이디</th>
                         			</tr>
                         		</thead>
                         			<c:if test="${finish_paylist.size() == 0}">
                         				<td colspan="6" align="center">
                         					결과가 없습니다.
                         				</td>
                         			</c:if>
                         			<c:if test="${finish_paylist.size() != 0}">
                         				<c:forEach var="finish_paylist" items="${finish_paylist}">
                         					<tr align="center">
                         						<td>${finish_paylist.regdate}</td>
	                         					<td>${finish_paylist.aprvdate}</td>
	                         					<td>${finish_paylist.prdname}</td>
	                         					<td><fmt:formatNumber value="${finish_paylist.price}" pattern="###,###"/>원</td>
	                         					<td>${finish_paylist.payment}</td>
	                         					<td>${finish_paylist.buyer}</td>
                         					</tr>
                         				</c:forEach>
                         			</c:if>
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div>

<%@ include file ="../../common/common_bottom.jsp" %>