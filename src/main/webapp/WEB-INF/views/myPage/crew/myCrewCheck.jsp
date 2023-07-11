<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_myPage.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	function withdraw(crewname){
		var check = confirm("정말 탈퇴하시겠습니까?");
		if(!check){
			return false;
		}else{
			location.href="/crew/user/withdraw.cr?crewname="+crewname;
		}
	}
</script>

<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">내가 만든 
                	<c:if test="${large == 1}">일일</c:if>
                	<c:if test="${large == 2}">정기</c:if>
                	 크루</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         			<tr align="center">
                         				<th>크루명</th>
                         				<th>현재 인원</th>
                         				<th>정원</th>
                         				<th>크루 소개</th>
                         				<th>모집글 보러가기</th>
                         			</tr>
                         			</thead>
                         			<c:if test="${myCrewList.size() == 0}">
                         				<tr align="center">
                         					<td colspan="5"> 만든 크루가 없습니다. </td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${myCrewList.size() != 0}">
	                         			<c:forEach var="myCrew" items="${myCrewList}">
	                         				<tr align="center">
	                         					<td>${myCrew.crewname}</td>
	                         					<td>${myCrew.crewnow}</td>
	                         					<td>${myCrew.crewlimit}</td>
	                         					<td>${myCrew.crewcontents}</td>
	                         					<td><a href="/crewboard/user/detail.bdcr?crewname=${myCrew.crewname}&num=0">모집글</a></td>
	                         				</tr>
	                         			</c:forEach>
                         			</c:if>
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
            
            <br>
            <br>
            <hr>
            <br>
            <br>
            
            
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">내가 가입한 
                	<c:if test="${large == 1}">일일</c:if>
                	<c:if test="${large == 2}">정기</c:if>
                	 크루</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         			<tr align="center">
                         				<th>크루명</th>
                         				<th>현재 인원</th>
                         				<th>정원</th>
                         				<th>크루 소개</th>
                         				<th>모집글 보러가기</th>
                         			</tr>
                         			</thead>
                         			<c:if test="${myJoinCrewList.size() == 0}">
                         				<tr align="center">
                         					<td colspan="5"> 가입한 크루가 없습니다. </td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${myJoinCrewList.size() != 0}">
	                         			<c:forEach var="myJoinCrew" items="${myJoinCrewList}">
	                         				<tr align="center">
	                         					<td>${myJoinCrew.crewname}</td>
	                         					<td>${myJoinCrew.crewnow}</td>
	                         					<td>${myJoinCrew.crewlimit}</td>
	                         					<td>${myJoinCrew.crewcontents}</td>
	                         					<td><a href="/crewboard/user/detail.bdcr?crewname=${myJoinCrew.crewname}&num=0">모집글</a></td>
	                         				</tr>
	                         			</c:forEach>
                         			</c:if>
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //findUserId End -->

<%@ include file ="../../common/common_bottom.jsp"%>