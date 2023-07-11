<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_myPage.jsp"%>

    <!-- myBoard.jsp<br> -->

<div class="container-fluid py-5">
	<div class="container">
		<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
			<h1 class="display-5 mb-5">작성한 게시글<br>[자유 게시판]</h1>
		</div>
		<!-- 검색 Start -->
		<nav class="navbar navbar-light bg-light" style="margin-bottom: 30px;">
			<div class="container" style="display: table-cell; vertical-align: middle;">
				<form class="d-flex" action="/crewboard/user/myPage.bdcr" method="post">
					<div class="col-sm-2">
						<select name="whatColumn" class="form-select">
							<option value="">전체검색
							<option value="user_id" <c:if test="${paramMap.whatColumn eq 'user_id'}"> selected</c:if>>작성자
							<option value="subject" <c:if test="${paramMap.whatColumn eq 'subject'}"> selected</c:if>>제목
							<option value="content" <c:if test="${paramMap.whatColumn eq 'content'}"> selected</c:if>>내용
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


<!-- 자유게시판 글 목록 출력 -->
<div class="container">
	<!-- 정렬 -->
    <div align="right">
    	<a href="/board/all/list.br?whatColumn=reg_date">최신순</a> / 
    	<a href="/board/all/list.br?whatColumn=readcount">조회순</a> / 
    	<a href="/board/all/list.br?whatColumn=reply">댓글순</a>
    	<br>
    </div>
    <!-- //정렬 -->	
<table class="table table-hover table-borded align-middle">
	<thead>
		<tr align="center">
			<th>좋아요</th>
			<th>번호</th>
			<th>카테고리</th>	
			<th>이미지</th>	
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<c:if test="${fn:length(lists) == 0}">
		<tr>
			<td colspan="7" align="center">
				등록된 게시물이 없습니다.
			</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty lists}">
	<c:forEach var="board" items="${lists}" varStatus="status">
		<tr align="center"> <!-- width="40%" -->
			<td>
				<c:if test="${fn:contains(bhList, board.num)}">
				<!-- 좋아요 -->
					<img src="<%=request.getContextPath()%>/resources/images/icon/heart.png" 
					id="heart" width="30" height="30">
				</c:if>
				<c:if test="${not fn:contains(bhList, board.num)}">
				<!-- 좋아요X -->
				</c:if>
			</td>
			<td><%-- ${board.num} --%>
				${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} 
			</td>
			<td>[${board.category}]</td>
			<td>
				<img src="<%=request.getContextPath() %>/resources/images/board/${board.image}" width="100" height="100">
			</td>		
			<td align="left">
				<a href="/board/user/detail.br?num=${board.num}&pageNumber=${pageInfo.pageNumber}">${board.subject}(${board.boardcommentscount})</a>
			</td>
			<td>${board.userid}</td>
			<td>${board.readcount}</td>
			<td>
				<fmt:parseDate var="newDay" value="${board.regdate}" pattern="yyyy-MM-dd"/>				
				<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
				${fNewDay }
			</td>
		</tr>
	</c:forEach>
	</c:if>		
</table>
</div>
<!-- //자유게시판 글 목록 출력 -->
    
<!-- 페이지 표시 -->
<div align="center">
	${pageInfo.pagingHtml}
</div>
<!-- //페이지 표시 -->   
    
<%@ include file ="../../common/common_bottom.jsp"%>       