<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<!-- Page Header Start -->
<div class="container-fluid page-header py-5 mb-5">
	<div class="container text-center py-5">
		<h1 class="display-3 text-white mb-4 animated slideInDown">거래 게시판</h1>
		<nav aria-label="breadcrumb animated slideInDown">
			<ol class="breadcrumb justify-content-center mb-0">
			</ol>
		</nav>
	</div>
</div>
<!-- Page Header End -->

<%@ include file = "../common/common_nav_products.jsp" %>

<!-- Projects Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="text-center mx-auto" style="max-width: 500px;">
				<p class="fs-5 fw-bold text-primary"></p>
            	<h3 class="display-5 mb-5">거래 게시판</h3>
        	</div> 
		
		<div class="col-12 text-center">
			<nav class="navbar navbar-light bg-light">
				<div class="container" style="display: table-cell; vertical-align: middle;">
					<form class="d-flex" action="/products/all/list.prd" method="post">
						<div class="col-sm-2">
							<select name="whatColumn" class="form-select">
								<option value="">전체</option>
								<option value="name">제품명</option>
							</select>
						</div>
						<div class="col-sm-8">
							<input class="form-control me-2" type="text" name="keyword">
						</div>
						<div class="col-sm-2" align="center">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</div>
					</form>
				</div>
			</nav>
		</div>
		<br>
		<!-- 정렬 -->
		<div align="right">
		<a href="/products/all/list.prd?whatColumn=inputdate">최신순</a> / 
    		<a href="/products/all/list.prd?whatColumn=readcount">조회순</a> / 
    		<a href="/products/all/list.prd?whatColumn=products_comments">댓글순</a>
    	</div>
		<!-- //정렬 -->
		<br>
		
		<div class="row g-4 portfolio-container">
			<c:forEach var="plist" items="${plist}">
				<!-- 대표 이미지 하나 가져오기 -->
				<c:set var="thumbnail" value="${fn:split(plist.image, ',')}" />
				<!-- //대표 이미지 하나 가져오기 -->

				<div class="col-lg-4 col-md-6 portfolio-item">
					<div class="portfolio-inner rounded">
						<!-- 상품 대표 이미지 -->
						<img src="<%=request.getContextPath()%>/resources/images/products/${thumbnail[0]}"
							alt="로딩중" width="100%" height="300px">
						<div class="portfolio-text">
							<h4 class="text-white mb-4">
								<c:if test="${plist.state == 1}">거래완료</c:if>
							</h4>
							<div class="d-flex">
								<a class="btn btn-lg-square rounded-circle mx-2" href="/products/user/detail.prd?num=${plist.productsnum}&pageNumber=${pageInfo.pageNumber}"><i class="fa fa-eye"></i></a>
							</div>
						</div>
						<!-- //상품 대표 이미지 -->
					</div>
					<br>
					
					<!-- 상품이름 가격 -->
					<table class="table table-borderless" width="100%">
						<tr>
							<td colspan="2">
								<c:if test="${fn:contains(phList, plist.productsnum)}">
								<!-- 좋아요 -->
									<img src="<%=request.getContextPath()%>/resources/images/icon/heart.png" 
									id="heart" width="30" height="30">
								</c:if>
								<c:if test="${not fn:contains(phList, plist.productsnum)}">
								<!-- 좋아요X -->
								</c:if>
								
								<!-- 상품명 -->
								<a href="/products/user/detail.prd?num=${plist.productsnum}&pageNumber=${pageInfo.pageNumber}">
									<font size="4px">${plist.name}  (${plist.comments})</font></a>
								<!-- //상품명 -->
							</td>
						</tr>
						<tr>	
							<!-- 구분 & 가격 -->
							<td align="left">
								<c:if test="${plist.kind == 'a'}">
									<font color="blue">[판매] </font>
									<font size="3px" style="font-weight:bold;"><fmt:formatNumber value="${plist.price}" pattern="###,###" /> 원</font>
								</c:if> 
								<c:if test="${plist.kind == 'b'}">
									<font color="orange">[나눔] </font>
								</c:if> 
								<c:if test="${plist.kind == 'c'}">
									<font color="green">[교환] </font>
								</c:if>
							</td>
							<!-- //구분 & 가격 -->
							<td align="right">
								<font size="1px" style="font-color:gray;">${plist.inputdate}</font>
							</td>
						</tr>
					</table>
					<!-- //상품이름 가격 -->
				</div>
			</c:forEach>
		</div>
		
		<!-- 글쓰기 -->
			<div align="right">
			<input type="button" class="btn btn-success" value="글쓰기"
				onclick="location.href='/products/user/insert.prd'"> <br>
			<br>
			</div>
		<!-- //글쓰기 -->
		
		<!-- 페이지 설정 -->
		<div align="center">
			${pageInfo.pagingHtml}
		</div>
		<!-- //페이지 설정 -->
	</div>
</div>
<!-- Projects End -->



<%@ include file="../common/common_bottom.jsp"%>