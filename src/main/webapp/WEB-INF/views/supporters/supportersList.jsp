<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>


<!-- Page Header Start -->
<div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
	<div class="container text-center py-5">
		<h1 class="display-3 text-white mb-4 animated slideInDown">서포터즈 모집 게시판</h1>		
		<div class="row g-5">
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${supporters_count[0]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">총 서포터즈</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${supporters_count[1]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집중인 서포터즈</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${supporters_count[2]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집완료 서포터즈</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${supporters_count[3]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집기한 마감 서포터즈</span>
                </div>
		</div>
	</div>
</div>
<!-- Page Header End -->
    
    <%@ include file ="../common/common_nav_supporters.jsp"%>


<!-- Projects Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
			<p class="fs-5 fw-bold text-primary"></p>
            <h3 class="display-5 mb-5">서포터즈 모집 게시판</h3>
        </div>
    
    <!-- 검색 기능 창 -->
    <div class="col-12 text-center">
		<nav class="navbar navbar-light bg-light">
			<div class="container" style="display: table-cell; vertical-align: middle;">
				<form class="d-flex" action="/supporters/all/list.su" method="post">
					<div class="col-sm-2">
						<select name="whatColumn" class="form-select">
							<option value="">전체</option>
							<option value="supporters_name">서포터즈</option>
							<option value="content">내용</option>
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
	</div>
</div>	
	<!-- //검색 기능 창 -->	
	
	
<div class="container">
	<!-- 정렬 -->
    <div align="right">
    	<a href="/supporters/all/list.su?whatColumn=reg_date">최신순</a> / 
    	<a href="/supporters/all/list.su?whatColumn=readcount">조회순</a>
    </div>
    <!-- //정렬 -->	
	
<!-- 목록보기 -->
<table class="table table-hover table-borded align-middle">
	<thead>
		<tr align="center">
			<th></th><!-- 좋아요 -->
			<th>번호</th>
			<th>모집 상태</th>
			<th>서포터즈</th>
			<th>모집 대상</th>		
			<th>신청 인원</th>
			<th>모집 마감일</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<c:if test="${fn:length(lists) == 0}">
		<tr>
			<td colspan="8" align="center">
				게시판에 게시된 글이 없습니다.
			</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty lists}">
	<c:forEach var="supporters" items="${lists}" varStatus="status">
		<tr align="center">
			<td>	
				<c:if test="${fn:contains(shList, supporters.num)}">
				<!-- 좋아요 -->
					<img src="<%=request.getContextPath()%>/resources/images/icon/heart.png" 
					id="heart" width="30" height="30">
				</c:if>
				<c:if test="${not fn:contains(shList, supporters.num)}">
				<!-- 좋아요X -->
				</c:if>
			</td>
			<td><%-- ${supporters.num} --%>
				${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} 
			</td>			
			<td>
				<!-- 디폴트는 모집중(0), 인원이 다 차면 모집완료(1), 모집기한이 지나면 모집마감(2) -->
				<%-- <font color="${statusColor}">${statusLabel}</font> --%>
				<c:if test="${supporters.state == 0}"><font color="blue">[모집중]</font></c:if>
	    		<c:if test="${supporters.state == 1}"><font color="red">[모집완료]</font></c:if>
	    		<c:if test="${supporters.state == 2}"><font color="black">[모집기한 마감]</font></c:if>	
			</td>
			<td align="left">
				<a href="/supporters/user/detail.su?num=${supporters.num}&pageNumber=${pageInfo.pageNumber}">${supporters.supportersname}</a>
			</td>
			<td>
				<c:if test="${supporters.target == 0}">전체회원</c:if>
				<c:if test="${supporters.target == 1}">우수회원</c:if>
			</td>			
			<td>${supporters.applicant}/${supporters.limit}</td>
			<td>
				<fmt:parseDate var="newDay2" value="${supporters.deadline}" pattern="yyyy-MM-dd"/>				
				<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd"/>
				${fNewDay2 }
			</td>
			<td align="center">${supporters.readcount}</td>
			<td>
				<fmt:parseDate var="newDay" value="${supporters.regdate}" pattern="yyyy-MM-dd"/>				
				<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
				${fNewDay }
			</td>
		</tr>
		</c:forEach>
	</c:if>		
</table>
<!-- //목록보기 -->

<!-- 로그인한 아이디가 관리자(admin)일때만 글쓰기 버튼이 보임 -->
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div align="right">
		<input type="button" value="글쓰기" class="btn btn-success" onclick="location.href='/supporters/admin/insert.su?pageNumber=${pageInfo.pageNumber}'">
	</div><br>
</sec:authorize>
<!-- 글쓰기 버튼 -->
     
	<!-- 페이지 표시 -->
	<div align="center">
		${pageInfo.pagingHtml}
	</div>
	<!-- //페이지 표시 -->   
</div>
	

<%@ include file="../common/common_bottom.jsp"%> 