<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp" %>
<%@ include file="../../common/common_nav_admin.jsp"%>	
	
    <!-- admin_supporters_user.jsp<br>
	관리자페이지 - 서포터즈 관리의 '회원 별 조회' -->
    
    
<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">회원 별 서포터즈 조회</h1>
        	</div>
                
            <div class="row justify-content-center" align="center">
                <div class="col-lg-9 mx-auto" >
                    <div class="bg-light rounded p-4 p-sm-5" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" align="center">
                        
                        	<!-- 검색창 -->
                        	<nav class="navbar navbar-light bg-light">
								<div class="container" style="display: table-cell; vertical-align: middle;">
									<form class="d-flex" action="/supporters/admin/user.su" method="post">
										<div class="col-sm-10">
											<input class="form-control me-8" type="text" name="keyword" value="${paramMap.keyword}" placeholder="회원 ID를 입력하세요">
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
										<th>회원 ID</th>
										<th>서포터즈</th>
										<th>모집상태</th>
                         				<th>신청인원</th>
                         				<th>모집마감일</th>
                         				<th>활동시작일</th>
                         				<th>활동종료일</th>
                         			</tr>
                         			</thead>
                         			<c:if test="${lists.size() == 0}">
                         				<tr>
                         					<td colspan="7" align="center">
                         						신청한 서포터즈가 없습니다.
                         					</td>
                         				</tr>
                         			</c:if>
                         			
                         			<c:if test="${lists.size() != 0}">
                         				<c:forEach var="supporters" items="${lists}">
                         					<tr align="center">
                         						<td>${supporters.applicantid}</td>
                         						<td><a href="/supporters/user/detail.su?num=${supporters.num}"">${supporters.supportersname}</a></td>
	                         					<td>
	                         						<c:if test="${supporters.state == 0}"><font color="blue">[모집중]</font></c:if>
	    											<c:if test="${supporters.state == 1}"><font color="red">[모집완료]</font></c:if>
	    											<c:if test="${supporters.state == 2}"><font color="black">[모집기한 마감]</font></c:if>	
	                         					</td>	                         					
	                         					<td>${supporters.applicant}/${supporters.limit}</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay" value="${supporters.deadline}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
													${fNewDay }
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay2" value="${supporters.startdate}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd"/>
													${fNewDay2 }
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay3" value="${supporters.enddate}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay3" value="${newDay3}" pattern="yyyy-MM-dd"/>
													${fNewDay3 }
	                         					</td>
                         					</tr>
                         				</c:forEach>
                         			</c:if>
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div>    
    
    
    
    
<%@ include file="../../common/common_bottom.jsp" %>    