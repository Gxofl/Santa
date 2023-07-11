<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_admin.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">

</script>	

<% 
	String[] thArr = {"번호", "아이디", "회원명", "성별", "생년월일", "등급", "가입일", "point"};
%>

	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
				style="max-width: 500px;">
				<h1 class="display-5 mb-5">회원 목록</h1>
			</div>
			<!-- 검색 Start -->
			<nav class="navbar navbar-light bg-light" style="margin-bottom: 30px;">
				<div class="container" style="display: table-cell; vertical-align: middle;">
					<form class="d-flex" action="/users/admin/usersList.us" method="post">
						<div class="col-sm-2">
							<select name="whatColumn" class="form-select">
								<option value="">전체</option>
								<option value="user_id">아이디</option>
								<option value="name">이름</option>
								<option value="user_role">등급</option>
								<option value="gender">성별</option>
								<option value="udate" hidden>날짜</option>
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
					
	<!-- 회원 목록 start -->
	<div class="container">
	   	<!-- 정렬 -->
	   	<div align="right">
    		<a href="/users/admin/usersList.us?whatColumn=udate">가입일자순</a> / 
    		<a href="/users/admin/usersList.us?whatColumn=userRole">등급순</a> / 
    		<a href="/users/admin/usersList.us?whatColumn=name">이름순</a>
    		<br>
    	</div>
	   	<!-- //정렬 -->
		<table class="table table-hover table-borded align-middle">
			<thead>
				<tr>
					<c:forEach var="thArr" items="<%= thArr %>">
						<th>${thArr}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:if test="${usersList == null || usersList.size() == 0}">
					<td colspan="10" align="center">
						등록된 데이터가 없습니다.
					</td>
				</c:if>
				<c:if test="${usersList != null || usersList.size() != 0 }">
					<c:forEach var="usersList" items="${usersList}"  varStatus="status">
					   	<tr>
							<td scope="col">
								${(pageInfo.pageNumber-1)*pageInfo.limit+status.count}
							</td>
							<td scope="col">
								<a href="/users/admin/usersUpdateRole.us?userId=${usersList.userId}">
									${usersList.userId}
								</a>
							</td>
							<td scope="col">${usersList.name}</td>
							<td scope="col">${usersList.gender}</td>
							<td scope="col">
								<fmt:parseDate var="parse_birth" value="${usersList.birth}" pattern = "yyyy-MM-dd"/>​
								<fmt:formatDate  var="fmt_birth" value="${parse_birth}" pattern="yyyy-MM-dd"/>
								${fmt_birth}
							</td>
							<td scope="col">
								<c:if test="${usersList.userRole eq 'r01'}">
									일반
								</c:if>
								<c:if test="${usersList.userRole eq 'r02'}">
									우수
								</c:if>
								<c:if test="${usersList.userRole eq 'r99'}">
									관리자
								</c:if>
							</td>
							<td scope="col">
								<fmt:parseDate var="parse_udate" value="${usersList.udate}" pattern = "yyyy-MM-dd"/>​
								<fmt:formatDate  var="fmt_udate" value="${parse_udate}" pattern="yyyy-MM-dd"/>
								${fmt_udate}
							</td>
							<td scope="col" align="right" >
								<fmt:formatNumber value="${usersList.point}" pattern="###,###"/>원
							</td>
					   </tr>
					</c:forEach>
			  	</c:if>
			</tbody>
		</table>
	</div>
	<!-- 회원 목록 end -->
	
	<!-- 페이지 start -->
	<div align="center">${pageInfo.pagingHtml}</div>
	<!-- //페이지 End -->


<%@ include file="../../common/common_bottom.jsp"%>