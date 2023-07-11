<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp" %> 

    <!-- noticeList.jsp<br> -->

<!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">공지사항</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                </ol>
            </nav>
        </div>
    </div>
<!-- Page Header End -->    
    


<!-- Projects Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
			<p class="fs-5 fw-bold text-primary"></p>
            <h3 class="display-5 mb-5">공지사항 게시판</h3>
        </div> 
		
		<!-- 검색 창 -->
		<nav class="navbar navbar-light bg-light">
  			<div class="container" style="display:table-cell; vertical-align:middle;">
   				<form class="d-flex" action="/notice/all/list.no" method="post">
   				 	<div class="col-sm-2">
	   				 	<select name="whatColumn" class="form-select">
							<option value="">전체검색
							<option value="subject">제목
							<option value="content">내용
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
		</nav><br>
	</div>	
		<!-- //검색 창 -->
	
	<div class="container">	
		<!-- 정렬 -->
    	<div align="right">
    		<a href="/notice/all/list.no?whatColumn=reg_date">최신순</a> / 
    		<a href="/notice/all/list.no?whatColumn=readcount">조회순</a>
    		<br>
    	</div>
    	<!-- //정렬 -->
    	
<table class="table table-hover table-borded align-middle">
	<thead>
		<tr align="center">
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일</th>		
		</tr>
	</thead>
	<c:if test="${fn:length(lists) == 0}">
		<tr>
			<td colspan="5" align="center">
				게시판에 게시된 글이 없습니다.
			</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty lists}">
	<c:forEach var="notice" items="${lists}" varStatus="status">
		<tr align="center">
			<td><%-- ${notice.num} --%>
				${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} 
			</td>
			<td>${notice.userid}</td>
			<td align="left"><a href="/notice/all/detail.no?num=${notice.num}&pageNumber=${pageInfo.pageNumber}">${notice.subject}</a></td>
			<td>${notice.readcount}</td>
			<td>
				<fmt:parseDate var="newDay" value="${notice.regdate}" pattern="yyyy-MM-dd"/>				
				<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
				${fNewDay }
			</td>		
		</tr>
	</c:forEach>
	</c:if>		
</table>
	
	<!-- 로그인한 아이디가 관리자(admin)일때만 글쓰기 버튼이 보임 -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div align="right">
			<input type="button" value="글쓰기" class="btn btn-success" onclick="location.href='/notice/admin/insert.no?pageNumber=${pageInfo.pageNumber}'">
		</div><br>
	</sec:authorize>
	<!-- //로그인한 아이디가 관리자일때만 글쓰기 버튼이 보임 -->		
    </div>
     
     <!-- 페이지 표시 -->
	<div align="center">
		${pageInfo.pagingHtml}
	</div>
	<!-- //페이지 표시 -->    
</div>



<%@ include file="../common/common_bottom.jsp"%>
