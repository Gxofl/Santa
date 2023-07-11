<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../common/common_top.jsp" %>
<%@ include file="../../common/common_nav_admin.jsp"%>

    <!-- admin_noticeList.jsp<br> -->
    

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>   
<script type="text/javascript">
	
$(document).ready(function(){
	//전체 선택 및 해제
	$('input[name=allcheck]').change(function(){
		$('input[name=rowcheck]').prop('checked', this.checked); 
	});	
})
	
	/* 삭제 버튼 클릭 */
	function delete_notice_check(){
		
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
					location.href = "/notice/admin/deleteadmin.no?num="+b_num; //
				}else{ // 다중 선택
					for(i=0; i<chkArray.length; i++){ // 확인
						if(i == 0){
							b_num = chkArray[i];
						}else{
							b_num += ","+chkArray[i];
						}
					}//for
					location.href = "/notice/admin/deleteadmin.no?num="+b_num;
				}//else
			}//if
		}//else
	}//
	
</script>
   
   
    
<!-- Projects Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
			<p class="fs-5 fw-bold text-primary"></p>
            <h3 class="display-5 mb-5">공지사항 관리 페이지</h3>
        </div> 
		
		<!-- 검색 창 -->
		<nav class="navbar navbar-light bg-light">
  			<div class="container" style="display:table-cell; vertical-align:middle;">
   				<form class="d-flex" action="/notice/admin/list.no" method="post">
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
    		<a href="/notice/admin/list.no?whatColumn=reg_date">최신순</a> / 
    		<a href="/notice/admin/list.no?whatColumn=readcount">조회순</a>
    		<br>
    	</div>
    	<!-- //정렬 -->
    	
<table class="table table-hover table-borded align-middle">
	<thead>
		<tr align="center">
			<th><input type="checkbox" name="allcheck"></th>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일</th>		
		</tr>
	</thead>
	<c:if test="${fn:length(lists) == 0}">
		<tr>
			<td colspan="6" align="center">
				게시판에 게시된 글이 없습니다.
			</td>
		</tr>
	</c:if>
	
	<c:if test="${not empty lists}">
	<c:forEach var="notice" items="${lists}" varStatus="status">
		<tr align="center">
			<td><input type="checkbox" name="rowcheck" value="${notice.num}"></td>
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
	
	<div align="right">
		<input type="button" value="삭제" class="btn btn-success" onclick="javascript : delete_notice_check()">
	</div><br>		
 </div>
     
     <!-- 페이지 표시 -->
	<div align="center">
		${pageInfo.pagingHtml}
	</div>
	<!-- //페이지 표시 -->    
</div>
    
    
<%@ include file="../../common/common_bottom.jsp" %>    
    