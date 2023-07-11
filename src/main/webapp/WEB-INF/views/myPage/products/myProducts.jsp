<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_myPage.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
</script>	

	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
				style="max-width: 500px;">
				<h1 class="display-5 mb-5">작성한 게시글<br>[거래 게시판]</h1>
			</div>
			<!-- 검색 Start -->
			<nav class="navbar navbar-light bg-light" style="margin-bottom: 30px;">
				<div class="container" style="display: table-cell; vertical-align: middle;">
					<form class="d-flex" action="/products/user/myPage.prd" method="post">
						<div class="col-sm-2">
							<select name="whatColumn" class="form-select">
								<option value="">전체</option>
								<option value="name">상품명</option>
								<option value="sell">판매</option>
								<option value="give">나눔</option>
								<option value="change">교환</option>
							</select>
						</div>
						<div class="col-sm-8">
							<input class="form-control me-2" type="text" name="keyword">
						</div>
						<div class="col-sm-2" align="center">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</div>
					</form>
				</div><!-- //container -->
			</nav><!-- //navbar -->
        </div><!-- //container - display:table -->
    </div><!-- //container-fluid -->
	<!-- //검색 End -->
					
	<!-- 작성한 글 목록 start -->
	<div class="container">
	   	<!-- 정렬 -->
	   	<div align="right">
    		<a href="/products/user/myPage.prd">전체</a> / 
    		<a href="/products/user/myPage.prd?whatColumn=state0">거래중</a> / 
    		<a href="/products/user/myPage.prd?whatColumn=state1">거래완료</a>
    		<br>
    	</div>
	   	<!-- //정렬 -->
		<table class="table table-hover table-borded align-middle">
			<thead>
				<tr align="center">
					<th>번호</th>
					<th>구분</th>
					<th>상태</th>
					<th>상품명(댓글수)</th>
					<th>가격</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${mylist == null || mylist.size() == 0}">
					<td colspan="6" align="center">
						등록된 게시글이 없습니다.
					</td>
				</c:if>
				<c:if test="${mylist != null || mylist.size() != 0 }">
					<c:forEach var="mylist" items="${mylist}" varStatus="status">
					   	<tr align="center">
							<td scope="col">${(pageNumber-1)*pageSize + status.count}</td>
							<td scope="col">
								<c:if test="${mylist.kind == 'a'}">판매</c:if>
								<c:if test="${mylist.kind == 'b'}">나눔</c:if>
								<c:if test="${mylist.kind == 'c'}">교환</c:if>
							</td>
							<td>
								<c:if test="${mylist.state == 0}"><font color="blue">거래중</font></c:if>
								<c:if test="${mylist.state == 1}"><font color="red">거래완료</font></c:if>
							</td>
							<td scope="col" align="left">
								<a href="/products/user/detail.prd?num=${mylist.productsnum}">
								${mylist.name} (${mylist.comments})</a>
							</td>
							<td scope="col">
								<fmt:formatNumber value="${mylist.price}" pattern="###,###"/>원</td>
							<td scope="col">
								${mylist.inputdate}
							</td>
					   </tr>
					</c:forEach>
			  	</c:if>
			</tbody>
		</table>
	</div>
	<!-- 작성한 글 목록 end -->
	
	<!-- 페이지 start -->
	<div align="center">${pageInfo.pagingHtml}</div>
	<!-- //페이지 End -->


<%@ include file ="../../common/common_bottom.jsp" %>