<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<%@ include file="../common/common_nav_admin.jsp"%>
<!-- mountainApiInsertList.jsp -->
<style>
	th, td {
	  text-align: center;
	}
</style>
    
    <!-- 산 목록 Start -->
                        
    	<form action="api.mnt" method="post">
            
            <input type="submit" class="btn btn-success" value="API DB 저장">
            
            <div class="text-center mx-auto" style="max-width: 500px;">
                <h1 class="display-5 mt-5 mb-5">API 산 정보</h1>
            </div>
				<div class="container-fluid py-5">
				        <div class="container">
				            <div class="text-center mx-auto" style="max-width: 500px;">
				                <h1 class="display-5 mb-5">산 목록</h1>
				            </div>
                            <!-- 산 목록 Start -->
                            <div class="container">
							<table class="table table-hover table-borded" border="1">
							  <thead>
							    <tr align="center">
							      <th scope="col">번호</th>
							      <th scope="col" class="w-25">이름</th>
							      <th scope="col" class="w-25">지역</th>
							      <th scope="col">높이</th>
							      <th scope="col">상세보기</th>
							      <th scope="col">이미지</th>
							      <th scope="col">100대명산</th>
							    </tr>
							  </thead>
							  <tbody>
							  <c:forEach var="i" begin="0" end="${fn:length(mountainnum)}">
							    <tr>
							      <th scope="row">${mountainnum[i]}</th>
							      <td>${mountainname[i]}</td>
							      <td>${mountainlocal[i]}</td>
							      <td>${mountainheight[i]}</td>
							      <td>${mountaincontent[i]}</td>
							      <td>${mountainimage[i]}</td>
							      <td>${mountaingreat[i]}</td>
							    </tr>
							  </c:forEach>
							  </tbody>
							</table>
							</div>
                            <!-- 페이지 표시 -->
							<!-- //페이지 표시 -->
                        </div>
                    </div>
    				</form>
<%@ include file="../common/common_bottom.jsp" %>