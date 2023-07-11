<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_myPage.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
</script>	
<% 
	String[] thArr_crewBoard 	= {"번호", "크루이름", "제목", "작성자", "좋아요한 날짜"};
	String[] thArr_board 	 	= {"번호", "카테고리", "제목", "작성자", "좋아요한 날짜"};
	String[] thArr_supporters 	= {"번호", "서포터즈명", "신청기한", "서포터즈활동기간", "좋아요한 날짜"};
	String[] thArr_products 	= {"번호", "서포터즈명", "신청기한", "서포터즈활동기간", "좋아요한 날짜"};
%>
	<div class="container-fluid py-5">
		<div class="container" style="display:table">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
				<h1 class="display-5 mb-5">좋아요한 게시글 목록</h1>
			</div>
			<!-- 좋아요한 게시글 목록 start -->
			<div class="container">
			   	
			   	<!-- 크루 게시판 -->
			   	<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
					style="max-width: 500px;">
					<h4 class="mb-4">크루 게시판</h4>
				</div>
				<table class="table table-hover table-borded align-middle">
					<thead>
						<tr>
							<c:forEach var="th_crewBoard" items="<%= thArr_crewBoard %>">
								<th>${th_crewBoard}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:if test="${cbhList == null || cbhList.size() == 0}">
					  		 <tr>
						  		<td colspan="5" align="center">좋아요한 게시글이 없습니다.</td>
						  	</tr>
						</c:if>
					  	<c:if test="${cbhList != null && cbhList.size() != 0}">
						  	<c:forEach var="cbheart" items="${cbhList}" varStatus="status">
							   <tr>
									 <td>${status.index + 1}</td>
									<td scope="col">${cbheart.crewBoard.crewname}</td>
									<td scope="col">
										<a href="/crewboard/user/detail.bdcr?num=${cbheart.crewboardNum}">
											${cbheart.crewBoard.subject}
										</a>
									</td>
									<td scope="col">${cbheart.crewBoard.writer}</td>
									<td>
										<fmt:parseDate 	var="parse_heartDate" value="${cbheart.heartDate}" pattern="yyyy-MM-dd"/>​
										<fmt:formatDate	var="fmt_heartDate" value="${parse_heartDate}" pattern="yyyy-MM-dd"/>
										${fmt_heartDate}
									</td> 
							    </tr> 
						  	</c:forEach>
					  	</c:if>
					</tbody>
				</table>
				<!-- //크루 게시판 -->
				
				<!-- 자유 게시판 -->
			  	<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px; margin-top: 100px;">
					<h4 class="mb-4">자유 게시판</h4>
				</div>
				<table class="table table-hover table-borded align-middle">
					<thead>
						<tr>
							<c:forEach var="th_board" items="<%= thArr_board %>">
								<th>${th_board}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:if test="${bhList == null || bhList.size() == 0}">
					  		 <tr>
						  		<td colspan="5" align="center">좋아요한 게시글이 없습니다.</td>
						  	</tr>
						</c:if>
					  	<c:if test="${bhList != null && bhList.size() != 0}">
						  	<c:forEach var="bheart" items="${bhList}"  varStatus="status">
							  <tr>
									<td>${status.index + 1}</td>
									<td scope="col">${bheart.board.category}</td>
									<td scope="col">
										<a href="/board/user/detail.br?num=${bheart.board.num}">
											${bheart.board.subject}
										</a>
									</td>
									<td scope="col">${bheart.board.userid}</td>
									<td>
										<fmt:parseDate 	var="parse_heartDate" value="${bheart.heartDate}" pattern="yyyy-MM-dd"/>​
										<fmt:formatDate	var="fmt_heartDate" value="${parse_heartDate}" pattern="yyyy-MM-dd"/>
										${fmt_heartDate}
									</td> 
							    </tr> 
						  	</c:forEach>
					  	</c:if>
					</tbody>
				</table> 
				<!-- //자유 게시판 -->
				
				<!-- 서포터즈 게시판 -->
			  	<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px; margin-top: 100px;">
					<h4 class="mb-4">서포터즈 게시판</h4>
				</div>
				<table class="table table-hover table-borded align-middle">
					<thead>
						<tr>
							<c:forEach var="th_supporters" items="<%= thArr_supporters %>">
								<th>${th_supporters}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:if test="${shList == null || shList.size() == 0}">
					  		 <tr>
						  		<td colspan="5" align="center">좋아요한 게시글이 없습니다.</td>
						  	</tr>
						</c:if>
					  	<c:if test="${shList != null && shList.size() != 0}">
							<c:forEach var="sheart" items="${shList}"  varStatus="status">
							    <tr>
							    	<td>${status.index + 1}</td>
							    	<td scope="col">${sheart.supporters.supportersname}</td>
							    	 <td scope="col">
							        	<fmt:parseDate var="parse_deadline" value="${sheart.supporters.deadline}" pattern="yyyy-MM-dd" />
							            <fmt:formatDate var="fmt_deadline" value="${parse_deadline}" pattern="yyyy-MM-dd" />
							        	${fmt_deadline}
							        </td> 
							        <td scope="col">
							        	<fmt:parseDate var="parse_startdate" value="${sheart.supporters.startdate}" pattern="yyyy-MM-dd" />
							            <fmt:formatDate var="fmt_startdate" value="${parse_startdate}" pattern="yyyy-MM-dd" />
							            <fmt:parseDate var="parse_enddate" value="${sheart.supporters.enddate}" pattern="yyyy-MM-dd" />
							            <fmt:formatDate var="fmt_enddate" value="${parse_enddate}" pattern="yyyy-MM-dd" />
							        	${fmt_startdate}~${fmt_enddate}
							        </td> 
							        <td>
							            <fmt:parseDate var="parse_heartDate" value="${sheart.heartDate}" pattern="yyyy-MM-dd" />
							            <fmt:formatDate var="fmt_heartDate" value="${parse_heartDate}" pattern="yyyy-MM-dd" />
							            ${fmt_heartDate}
							        </td> 
							    </tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table> 
				<!-- //서포터즈 게시판 -->
				
				<!-- 거래 게시판 -->
			   	<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
					style="max-width: 500px; margin-top: 100px;" >
					<h4 class="mb-4">거래 게시판</h4>
				</div>
				<table class="table table-hover table-borded align-middle">
					<thead>
						<tr>
							<c:forEach var="th_products" items="<%= thArr_products %>">
								<th>${th_products}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:if test="${phList == null || phList.size() == 0}">
					  		 <tr>
						  		<td colspan="5" align="center">좋아요한 게시글이 없습니다.</td>
						  	</tr>
						</c:if>
					  	<c:if test="${phList != null && phList.size() != 0}">
						  	<c:forEach var="pheart" items="${phList}" varStatus="status">
							   <tr>
									 <td>${status.index + 1}</td>
									<td scope="col">${pheart.products.productsNum}</td>
									<td scope="col">
										<a href="#">
											${pheart.products.name}
										</a>
									</td>
									<td scope="col">${pheart.products.price}</td>
									<td scope="col">${pheart.products.state}</td>
									<td>
										<fmt:parseDate 	var="parse_heartDate" value="${pheart.heartDate}" pattern="yyyy-MM-dd"/>​
										<fmt:formatDate	var="fmt_heartDate" value="${parse_heartDate}" pattern="yyyy-MM-dd"/>
										${fmt_heartDate}
									</td> 
							    </tr> 
						  	</c:forEach>
					  	</c:if>
					</tbody>
				</table>
				<!-- //거래 게시판 -->
				
				
				
			</div><!-- //container -->
		</div><!-- //container - display:table -->
	</div><!-- //container-fluid -->
	<!-- 좋아요한 게시글 목록 end -->
		
	<!-- 페이지 start -->
	<div align="center">${pageInfo.pagingHtml}</div>
	<!-- //페이지 End -->

<%@ include file="../../common/common_bottom.jsp"%>

