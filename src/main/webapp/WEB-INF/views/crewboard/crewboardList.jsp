<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../common/common_top.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">

	/* 신청하기 버튼 클릭 */
	function signUp(crewname, state, loginId, large, small) {
		if(loginId.length == 0){
			alert('회원만 신청 가능합니다');
			return false;
		}
		
		if (state == '1') {//모집완료 일때
			alert('이미 모집완료된 크루입니다');
			return false;
		}else { // 모집중 일때
			if(large == '2'){ // 정기 크루일때
				var join_regular_list = new Array();
				
				<c:forEach items="${join_regular_crew}" var="item">
					join_regular_list.push("${item.small}");
				</c:forEach>
			
				var regular_result = false;
				for(i=0; i<join_regular_list.length; i++){
					if(join_regular_list[i] == small){
						regular_result = true;
					}
				}//for
				
				if(regular_result == true){ // 이미가입한 해당 정기 크루가 있음
					if(small == 'M'){
						alert('이미 가입한 등산 정기크루가 있습니다');
						return false;
					}else{
						alert('이미 가입한 플로깅 정기크루가 있습니다');
						return false;
					}
				}else{
					var choose2 = confirm(crewname + "에 가입하시겠습니까?");
					if(choose2 == true) { // 확인 눌렀을때
						return true;
					}else {
						return false;
					}
				}
			}else{ // 일일 크루
				var joincrew_list = new Array();
			
				<c:forEach items="${join_crew}" var="item">
					joincrew_list.push("${item.crewname}");
				</c:forEach>
				
				var result = false;
				for(i=0; i<joincrew_list.length; i++){
					if(joincrew_list[i] == crewname){ // 가입한 크루 목록에 신청하기 누른 크루이름이 있다면
						result = true;
					}
				}
				
				if(result == true) { // 멤버에 loginId가 있음
					alert('이미 가입한 크루입니다');
					return false;
				}else {
					var choose = confirm(crewname + "에 가입하시겠습니까?");
					if(choose == true) { // 확인 눌렀을때
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}//signUp
	
</script>
<style>
	.subject{
		background-color:white;
	}
</style>
	<!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
        	<h1 class="display-3 text-white mb-4 animated slideInDown">크루 모집 게시판</h1>
        	 <div class="row g-5">
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${crew_count[0]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">총 산타 크루</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${crew_count[1]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집중인 등산크루</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${crew_count[2]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집중인 플로깅크루</span>
                </div>
                <div class="col-sm-6 col-lg-3 text-center wow fadeIn" data-wow-delay="0.1s">
                    <h1 class="display-4 text-white" data-toggle="counter-up">${crew_count[3]}</h1>
                    <span class="fs-5 fw-semi-bold text-light">모집완료 크루</span>
                </div>
               </div>
        </div>
    </div>
    <!-- Page Header End -->
    
    <%@ include file ="../common/common_nav_crewboard.jsp"%>

    <!-- Feature Start -->
    <!-- 검색창 -->
    <div class="container-fluid py-5">
        <div class="container" style="display:table">
        	<div class="text-center mx-auto" style="max-width: 500px;">
				<p class="fs-5 fw-bold text-primary"></p>
            	<h3 class="display-5 mb-5">크루 모집 게시판</h3>
        	</div>    
        
        	<br>
            <nav class="navbar navbar-light bg-light">
  				<div class="container" style="display:table-cell; vertical-align:middle;">
   				 	<form class="d-flex" action="list.bdcr" method="post">
   				 		<div class="col-sm-2">
	   				 		<select name="whatColumn" class="form-select">
	   				 			<option value="all">전체</option>
	   				 			<option value="crewname">크루명</option>
	   				 			<option value="M">등산크루</option>
	   				 			<option value="P">플로깅크루</option>
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
    <!-- //검색창 -->
    
    <!-- 크루 게시판 목록  -->
    <div class="container">
    	<!-- 정렬 -->
    	<div align="right">
    		<a href="/crewboard/all/list.bdcr?whatColumn=reg_date">최신순</a> / 
    		<a href="/crewboard/all/list.bdcr?whatColumn=readcount">조회순</a> / 
    		<a href="/crewboard/all/list.bdcr?whatColumn=crew_comments">댓글순</a>
    		<br>
    	</div>
    	<!-- //정렬 -->
	    <table class="table table-hover align-middle" style="width: 100%;">
	    	<thead>
		    	<tr align="center">
		    		<th></th><!-- 좋아요 -->
		    		<th>번호</th>
		    		<th>모집상태</th>
		    		<th>구분1/구분2</th>
		    		<th>제목</th>
		    		<th>작성자</th>
		    		<th>크루명</th>
		    		<th>현재인원/<br>전체인원</th>
		    		<th>신청하기</th>
				</tr>
			</thead>
	    	<c:if test="${crewboard_list.size() == 0 }">
	    		<tr>
	    			<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
	    		</tr>
	    	</c:if>
	    	
	    	<c:if test="${crewboard_list.size() != 0 }">
	    		<c:forEach var="lists" items="${crewboard_list}" varStatus="status">
	    			<tr align="center">
	    				<td>
							<c:if test="${fn:contains(cbhList, lists.crewboardnum)}">
							<!-- 좋아요 -->
								<img src="<%=request.getContextPath()%>/resources/images/icon/heart.png" 
								id="heart" width="30" height="30">
							</c:if>
							<c:if test="${not fn:contains(cbhList, lists.crewboardnum)}">
							<!-- 좋아요X -->
							</c:if>
						</td>
	    				<td> 
	    					${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} 
	    				</td>
	    				<td>
	    					<c:if test="${lists.state == 0}"><font color="blue">[모집중]</font></c:if>
	    					<c:if test="${lists.state == 1}"><font color="red">[모집완료]</font></c:if>
	    				</td>
	    				<td>
	    					<c:if test="${lists.large == 1}">1일 /</c:if>
	    					<c:if test="${lists.large == 2}">정기 /</c:if>
	    					<c:if test="${lists.small == 'M'}">등산</c:if>
	    					<c:if test="${lists.small == 'P'}">플로깅</c:if>
	    				</td>
	    				<td width="40%" align="left">
	    					<a href="/crewboard/user/detail.bdcr?num=${lists.crewboardnum}&pageNumber=${pageInfo.pageNumber}">${lists.subject} (${lists.comments})</a>
	    				</td>
	    				<td>
	    					${lists.writer}
	    				</td>
	    				<td>
	    					${lists.crewname}
	    				</td>
	    				<td>
	    					${lists.crewnow}/${lists.crewlimit}
	    				</td>
	    				<td>
	    					<form action="/crew/user/update.cr" method="post">
		    					<input type="hidden" name="crewname" value="${lists.crewname}">
		    					<input type="hidden" name="state" value="${lists.state}">
		    					<input type="hidden" name="pageNumber" value="${pageInfo.pageNumber}">
		    					<input type="submit" class="btn btn-secondary btn-sm" value="신청하기" onclick="return signUp('${lists.crewname}','${lists.state}', '${loginId}', '${lists.large}', '${lists.small}')">
	    					</form>
	    				</td>
	    			</tr>
	    		</c:forEach>
	    	</c:if>
	    </table>
    </div>
    <!-- //크루 게시판 목록  -->
    
    <!-- 글쓰기 -->
    <div class="container">
    	<div align="right">
    		<input type="button" class="btn btn-success" value="글쓰기" onclick="location.href='/crewboard/user/insert.bdcr'">
    	</div>
    </div>
    <!-- //글쓰기 -->
    
     <!-- 페이지 표시 -->
	 <div align="center">
	 	${pageInfo.pagingHtml}
	 </div>
	 <!-- //페이지 표시 -->
    <!-- Feature End -->
    
    

<%@ include file ="../common/common_bottom.jsp"%>