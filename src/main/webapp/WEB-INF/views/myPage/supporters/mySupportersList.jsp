<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>

<%@ include file ="../../common/common_nav_myPage.jsp"%>
    
    <!-- mySupportersList.jsp<br> -->
    <!-- common_nav_myPage.jsp에서 '내 서포터즈 보기'를 클릭하면 supportersApplyListController을 통해 이곳으로 이동한다. -->

<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">내 서포터즈</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-8 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                        
                         	<table class="table table-hover">
                         			<tr align="center">
                         				<!-- <th>번호</th> -->
                         				<th>서포터즈</th>
                         				<th>모집 대상</th> 
                         				<th>신청일</th> 
                         				<th>활동 시작일</th>
                         				<th>활동 종료일</th>
                         			</tr>
                         			<c:if test="${applybean.size() == 0}">
                         				<tr align="center">
                         					<td colspan="5"> 신청한 서포터즈가 없습니다. </td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${applybean.size() != 0}">
	                         			<c:forEach var="mysupporters" items="${applybean}">
	                         				<tr align="center">
	                         					<%-- <td>${mysupporters.num}</td> --%>
	                         					<td><a href="/supporters/user/detail.su?num=${mysupporters.joinnum}">${mysupporters.supportersname}</a></td>
	                         					<td>
	                         						<c:if test="${mysupporters.target == 0}">전체회원</c:if>
													<c:if test="${mysupporters.target == 1}">우수회원</c:if>
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay" value="${mysupporters.applydate}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
													${fNewDay }
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay2" value="${mysupporters.startdate}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd"/>
													${fNewDay2 }
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay3" value="${mysupporters.enddate}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay3" value="${newDay3}" pattern="yyyy-MM-dd"/>
													${fNewDay3 }
	                         					</td>
	                         				</tr>
	                         			</c:forEach>
                         			</c:if>
                         		</table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    	</div>
    
    
    
    
    
<%@ include file ="../../common/common_bottom.jsp"%>    
    