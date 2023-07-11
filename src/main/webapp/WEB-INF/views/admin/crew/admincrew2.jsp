<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_admin.jsp"%>

<style>
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	})
</script>

<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">
                	<c:if test="${small == null}">
                		정기크루
                	</c:if>
                	<c:if test="${small == 'M'}">
                		정기 등산 크루
                	</c:if>
                	<c:if test="${small == 'P'}">
                		정기 플로깅 크루
                	</c:if>
                </h1>
               	 <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item" id="all"><a href="/crew/admin/list2.cr">전체</a></li>
                    <li class="breadcrumb-item" id="Mot"><a href="/crew/admin/list2.cr?small=M">등산</a></li>
                    <li class="breadcrumb-item" id="Plg" ><a href="/crew/admin/list2.cr?small=P">플로깅</a></li>
                </ol>
                <br>
                <br>
        	</div>
                
            <div class="row justify-content-center">
                <div class="col-lg-8 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                        
                        	<!-- 검색창 -->
                        	<nav class="navbar navbar-light bg-light">
								<div class="container" style="display: table-cell; vertical-align: middle;">
									<form class="d-flex" action="/crew/admin/list2.cr" method="post">
										<div class="col-sm-2">
											<select name="whatColumn" class="form-select">
												<option value="">전체</option>
												<option value="crewname">크루명</option>
												<option value="crewmanager">크루장</option>
											</select>
										</div>
										<div class="col-sm-8">
											<input type="hidden" name="small" value="${small}">
											<input class="form-control me-2" type="text" name="keyword">
										</div>
										<div class="col-sm-2" align="center">
											<button class="btn btn-outline-success" type="submit">Search</button>
										</div>
									</form>
								</div>
							</nav>
                        	<!-- //검색창 -->
                        	
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         			<tr align="center">
                         				<th>구분</th>
                         				<th>크루명</th>
                         				<th>크루 소개</th>
                         				<th>크루장</th>
                         				<th>현재 인원/ 정원</th>
                         				<th>생성일</th>
                         				<th>상태</th>
                         			</tr>
                         			</thead>
                         			<c:if test="${crewList.size() == 0}">
                         				<td colspan="7" align="center">
                         					결과가 없습니다.
                         				</td>
                         			</c:if>
                         			<c:if test="${crewList.size() != 0}">
                         				<c:forEach var="crewlist" items="${crewList}">
                         					<tr align="center">
	                         					<td>
	                         						<c:if test="${crewlist.small == 'M'}">등산</c:if>
	                         						<c:if test="${crewlist.small == 'P'}">플로깅</c:if>
	                         					</td>
	                         					<td>${crewlist.crewname}</td>
	                         					<td>${crewlist.crewcontents}</td>
	                         					<td>${crewlist.crewmanager}</td>
	                         					<td>${crewlist.crewnow}/${crewlist.crewlimit}</td>
	                         					<td>${crewlist.regdate}</td>
	                         					<td>
	                         						<c:if test="${crewlist.state == 0}">모집중</c:if>
	                         						<c:if test="${crewlist.state == 1}">모집완료</c:if>
	                         					</td>
                         					</tr>
                         				</c:forEach>
                         			</c:if>
                         		</table>
                         		<!-- 페이지 start -->
									<div align="center">${pageInfo.pagingHtml}</div>
								<!-- //페이지 End -->
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div>


<!-- 게시판 형태로 -->
<%-- <div class="container-fluid py-5">
	<div class="container">
		<div class="text-center mx-auto wow" data-wow-delay="0.1s"
			style="max-width: 500px;">
			<h1 class="display-5 mb-5">
				<c:if test="${small == null}">
                		정기크루
                	</c:if>
				<c:if test="${small == 'M'}">
                		정기 등산 크루
                	</c:if>
				<c:if test="${small == 'P'}">
                		정기 플로깅 크루
                	</c:if>
			</h1>
			<ol class="breadcrumb justify-content-center mb-0">
				<li class="breadcrumb-item" id="all"><a
					href="/crew/admin/list2.cr">전체</a></li>
				<li class="breadcrumb-item" id="Mot"><a
					href="/crew/admin/list2.cr?small=M">등산</a></li>
				<li class="breadcrumb-item" id="Plg"><a
					href="/crew/admin/list2.cr?small=P">플로깅</a></li>
			</ol>
			<br> <br>
		</div>

		<!-- 검색 Start -->
		<nav class="navbar navbar-light bg-light" style="margin-bottom: 30px;">
			<div class="container"
				style="display: table-cell; vertical-align: middle;">
				<form class="d-flex" action="/crew/admin/list2.cr" method="post">
					<div class="col-sm-2">
						<select name="whatColumn" class="form-select">
							<option value="">전체</option>
							<option value="crewname">크루명</option>
							<option value="crewmanager">크루장</option>
						</select>
					</div>
					<div class="col-sm-8">
						<input type="hidden" name="small" value="${small}"> <input
							class="form-control me-2" type="text" name="keyword">
					</div>
					<div class="col-sm-2" align="center">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</div>
				</form>
			</div>
			<!-- //container -->
		</nav>
		<!-- //navbar -->
	</div>
	<!-- //container - display:table -->
</div>
<!-- //container-fluid -->
<!-- //검색 End -->

<!-- 목록 start -->
<div class="container">
	<!-- 정렬 -->
	<div align="right">
		<a href="#">최신순</a> / <a href="#">조회순</a> / <a href="#">댓글순</a> <br>
	</div>
	<!-- //정렬 -->
	<table class="table table-hover table-borded align-middle">
		<thead>
			<tr align="center">
				<th>구분</th>
				<th>크루명</th>
				<th>크루 소개</th>
				<th>크루장</th>
				<th>현재 인원/ 정원</th>
				<th>생성일</th>
				<th>상태</th>
			</tr>
		</thead>
		<c:if test="${crewList.size() == 0}">
			<td colspan="7" align="center">결과가 없습니다.</td>
		</c:if>
		<c:if test="${crewList.size() != 0}">
			<c:forEach var="crewlist" items="${crewList}">
				<tr align="center">
					<td><c:if test="${crewlist.small == 'M'}">등산</c:if> <c:if
							test="${crewlist.small == 'P'}">플로깅</c:if></td>
					<td>${crewlist.crewname}</td>
					<td>${crewlist.crewcontents}</td>
					<td>${crewlist.crewmanager}</td>
					<td>${crewlist.crewnow}/${crewlist.crewlimit}</td>
					<td>${crewlist.regdate}</td>
					<td><c:if test="${crewlist.state == 0}">모집중</c:if> <c:if
							test="${crewlist.state == 1}">모집완료</c:if></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>
<!-- 목록 end -->

<!-- 페이지 start -->
<div align="center">${pageInfo.pagingHtml}</div>
<!-- //페이지 End --> --%>

<%@ include file="../../common/common_bottom.jsp"%>