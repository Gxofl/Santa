<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp" %>
<%@ include file="../../common/common_nav_admin.jsp"%>

    <!-- admin_boardList.jsp<br> -->

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>   
<script type="text/javascript">
	
$(document).ready(function(){
	//전체 선택 및 해제
	$('input[name=allcheck]').change(function(){
		$('input[name=rowcheck]').prop('checked', this.checked); 
	});	
})
	
	/* 삭제 버튼 클릭 */
	function delete_board_check(){
		
		var chkArray = new Array(); // 배열 선언
			 
	    $('input[name=rowcheck]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
	        chkArray.push(this.value);
	    });
		
		if(chkArray.length == 0){ // 선택하지 않고, 삭제 버튼 클릭했을때
			alert('삭제할 글을 선택하세요');
		}else{
			var msg = chkArray.length +"개 글을 삭제하시겠습니까?";
			
			var b_num = "";
			var result = confirm(msg);
			if(result == true){
				if(chkArray.length == 1){ // 1개만 선택했을때
					b_num = chkArray[0];
					location.href = "/board/admin/deleteadmin.br?num="+b_num; //
				}else{ // 다중 선택
					for(i=0; i<chkArray.length; i++){ // 확인
						if(i == 0){
							b_num = chkArray[i];
						}else{
							b_num += ","+chkArray[i];
						}
					}//for
					location.href = "/board/admin/deleteadmin.br?num="+b_num;
				}//else
			}//if
		}//else
	}//
	
</script>
    
    
<!-- Projects Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 530px;">
			<p class="fs-5 fw-bold text-primary"></p>
            <h3 class="display-5 mb-5">자유 게시판 관리 페이지</h3>
        </div>                  
		<!-- 정렬&검색창 -->			
		<nav class="navbar navbar-light bg-light">		
	  			<div class="container" style="display:table-cell; vertical-align:middle;">
	  			<form class="d-block" name="list_form" action="/board/admin/list.br" method="get">
   				 	<div class="row">
						<!-- 검색 주제 -->
   				 		<div class="col-sm-2">
		   				 	<select name="whatColumn" class="form-select">
								<option value="">전체검색
								<option value="user_id" <c:if test="${paramMap.whatColumn eq 'user_id'}"> selected</c:if>>작성자
								<option value="subject" <c:if test="${paramMap.whatColumn eq 'subject'}"> selected</c:if>>제목
								<option value="content" <c:if test="${paramMap.whatColumn eq 'content'}"> selected</c:if>>내용
		   				 	</select>
	   				 	</div>
	   				 	<!-- //검색 주제 --> 	
	   				 	<!-- 검색창 -->
	   				 	<div class="col-sm-8">
	     					<input class="form-control me-2" type="text" name="keyword">
	      				</div>
	      				<!-- //검색창 -->	      				
	      				<!-- Search 버튼 -->
	      				<div class="col-sm-2" align="center">
	      					<button class="btn btn-outline-success" type="submit">Search</button>
	      				</div>
   				 	   	<!-- //Search 버튼 -->			 	
   				 	</div>     				
      			</form>
	 		</div>			
		</nav><br>
		<!-- //정렬&검색창 -->			
	</div>
</div>
<!-- //Projects Start -->	
	

<!-- 자유게시판 글 목록 출력 -->
<div class="container">

	<!-- 정렬 -->
    <div align="right">
    	<a href="/board/admin/list.br?whatColumn=reg_date">최신순</a> / 
    	<a href="/board/admin/list.br?whatColumn=readcount">조회순</a> / 
    	<a href="/board/admin/list.br?whatColumn=reply">댓글순</a>
    	<br>
    </div>
    <!-- //정렬 -->
	
<table class="table table-hover table-borded align-middle">
	<thead>
		<tr align="center">
			<th><input type="checkbox" name="allcheck"></th>
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
			<td colspan="8" align="center">
				등록된 게시물이 없습니다.
			</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty lists}">
	<c:forEach var="board" items="${lists}" varStatus="status">
		<tr align="center"> <!-- width="40%" -->
			<td><input type="checkbox" name="rowcheck" value="${board.num}"></td>
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
	<!-- 글쓰기 버튼 -->
	<div align="right">
		<input type="button" value="삭제" class="btn btn-success" onclick="javascript : delete_board_check()">
	</div>
	<!-- //글쓰기 버튼 -->
</div>
<!-- //자유게시판 글 목록 출력 -->


<!-- 페이지 표시 -->
<div align="center">
	${pageInfo.pagingHtml}
</div>
<!-- //페이지 표시 -->    
    
    
    
<%@ include file="../../common/common_bottom.jsp" %> 