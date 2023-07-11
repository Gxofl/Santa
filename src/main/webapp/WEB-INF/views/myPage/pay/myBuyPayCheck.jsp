<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file ="../../common/common_top.jsp" %>

<%@ include file ="../../common/common_nav_myPage.jsp"%>

	<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">나의 구매 거래 내역</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         				<tr align="center">
                         					<th>번호</th>
                         					<th>결제일</th>
                         					<th>상품명</th>
                         					<th>가격</th>
                         					<th>결제 승인 여부</th>
                         				</tr>
                         			</thead>
                         			<c:if test="${myPayList.size() == 0}">
                         				<tr align="center">
                         					<td colspan="5">
                         					결제 내역이 없습니다.
                         					</td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${myPayList.size() != 0}">
	                         			<c:forEach var="myPayList" items="${myPayList}" varStatus="status">
	                         				<tr align="center">
	                         					<td>${status.count }</td>
	                         					<td>${myPayList.regdate}</td>
	                         					<td><a href="/products/user/detail.prd?num=${myPayList.prdnum}">${myPayList.prdname}</a></td>
	                         					<td><fmt:formatNumber value="${myPayList.price}" pattern="###,###" />원</td>
	                         					<td align="left">
	                         						<c:if test="${myPayList.state == 0}">
	                         							<a href="/pay/user/myPage2.pay?prdnum=${myPayList.prdnum}&paynum=${myPayList.paynum}">승인 대기중</a>
	                         						</c:if>
	                         						<c:if test="${myPayList.state == 1}">
	                         							<a href="/pay/user/myPage2.pay?prdnum=${myPayList.prdnum}&paynum=${myPayList.paynum}">승인완료 (${myPayList.aprvdate})</a>
	                         						</c:if>
	                         						<c:if test="${myPayList.state == 2}">
	                         							<a href="/pay/user/myPage2.pay?prdnum=${myPayList.prdnum}&paynum=${myPayList.paynum}">본인 결제취소 (${myPayList.aprvdate})</a>
	                         						</c:if>
	                         						<c:if test="${myPayList.state == 3}">
	                         							<a href="/pay/user/myPage2.pay?prdnum=${myPayList.prdnum}&paynum=${myPayList.paynum}">관리자 결제취소 (${myPayList.aprvdate})</a>
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