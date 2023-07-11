<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_admin.jsp" %>
	<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 800px;">
                <h1 class="display-5 mb-5">결제 취소 내역 </h1>
        	</div>
                
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         	<table class="table table-hover table-borded align-middle">
                         		<thead>
                         			<tr align="center">
                         				<th>구매자 결제일</th>
                         				<th>결제 취소일</th>
                         				<th>상품명</th>
                         				<th>가격</th>
                         				<th>결제방법</th>
                         				<th>구매자 아이디</th>
                         				<th>취소 사유</th>
                         			</tr>
                         		</thead>
                         			<c:if test="${cancel_paylist.size() == 0}">
                         				<td colspan="7" align="center">
                         					결과가 없습니다.
                         				</td>
                         			</c:if>
                         			<c:if test="${cancel_paylist.size() != 0}">
                         				<c:forEach var="cancel_paylist" items="${cancel_paylist}">
                         					<tr align="center">
                         						<td><font color="blue">${cancel_paylist.regdate}</font></td>
	                         					<td><font color="red">${cancel_paylist.aprvdate}</font></td>
	                         					<td>${cancel_paylist.prdname}</td>
	                         					<td><fmt:formatNumber value="${cancel_paylist.price}" pattern="###,###"/>원</td>
	                         					<td>${cancel_paylist.payment}</td>
	                         					<td>${cancel_paylist.buyer}</td>
	                         					<td>
	                         						<c:if test="${cancel_paylist.state == 2}">본인 요청</c:if>
	                         						<c:if test="${cancel_paylist.state == 3}">관리자 취소</c:if>
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
        </div>

<%@ include file ="../../common/common_bottom.jsp" %>