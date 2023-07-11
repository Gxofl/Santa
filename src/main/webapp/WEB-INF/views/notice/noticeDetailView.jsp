<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp" %> 

    <!-- noticeDetailView.jsp<br> -->
    
<script type="text/javascript">
	
	function deletenotice(num, pageNumber){ //get방식 요청
		
		var isDel = confirm("정말 삭제하시겠습니까?"); //확인:true, 취소:false
		if(isDel == true){
			location.href="/notice/admin/delete.no?num="+num+"&pageNumber="+pageNumber;
		}			
	}
</script>

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


<div class="container-xxl py-4">
	<div class="container">
        <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
        	<h2 class="display-5 mb-5">공지사항</h2>
        </div>

		<!-- 게시글 상세보기 -->
		<table class="table">
			<tr>
				<th width="20%">글번호</th>
				<td>${notice.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${notice.userid}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><%-- <fmt:parseDate var="newDay" value="${notice.regdate}" pattern="yyyy-MM-dd hh:mm:ss" /> 
					<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd hh:mm:ss" /> ${fNewDay }</td> --%>
					<fmt:formatDate value="${notice.regdate}" type="date" dateStyle="medium" timeStyle="medium" pattern="yyyy-MM-dd hh:mm:ss"/>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${notice.readcount }</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td>${notice.subject }</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td height="210">${notice.content }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 수정, 삭제 버튼은 관리자에게만 보이도록 -->
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<%-- <c:if test="${loginId == 'admin'}"> --%>
						<input type="button" value="수정" class="btn btn-success" onClick="location.href='/notice/admin/update.no?num=${notice.num}&pageNumber=${pageNumber}'">
						<input type="button" value="삭제" class="btn btn-success" onClick="deletenotice('${notice.num}','${pageNumber}')">
					</sec:authorize>
					<input type="button" value="목록" class="btn btn-success" onclick="location.href='/notice/all/list.no?pageNumber=${pageNumber}'">
				</td>
			</tr>
		</table>
		<!-- //게시글 상세보기 -->
	</div>
</div>

		<%@ include file="../common/common_bottom.jsp"%>  