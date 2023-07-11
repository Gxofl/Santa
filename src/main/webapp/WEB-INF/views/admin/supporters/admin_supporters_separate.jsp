<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp" %>
<%@ include file="../../common/common_nav_admin.jsp"%>	

    <!-- admin_supporters_separate.jsp<br>
    관리자페이지 - 서포터즈 관리의 '서포터즈 별 회원 조회' -->
    
    
<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">서포터즈 별 회원 조회</h1>
        	</div>
                
            <div class="row justify-content-center" align="center">
                <div class="col-lg-8 mx-auto" >
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                        
                        	<!-- 검색창 -->
                        	<nav class="navbar navbar-light bg-light" >
								<div class="container" style="display: table-cell; vertical-align: middle;">
									<!-- <form class="d-flex" action="/supportersapply/admin/user.sua" method="post"> -->
										<div class="col-sm-8" >
											<select class="form-control me-6" id="keyword" name="keyword" onChange="change_supporters(this.value)" data-bs-toggle="dropdown">
												<c:forEach var="list" items="${suLists}">
													<option value="${list.supportersname}" <c:if test="${paramMap.keyword eq list.supportersname}"> selected</c:if> >${list.supportersname}</option>
												</c:forEach>
											</select>
										</div>
										<!-- <div class="col-sm-2" align="center">
											<button class="btn btn-outline-success" type="button" onclick="fn_search();">Search</button>
										</div> -->
									<!-- </form> -->
								</div>
							</nav>
                        	<!-- //검색창 -->                        	
                        	
                         		<table class="table table-hover table-borded align-middle">
                         			<thead>
                         			<tr align="center">                     				
										<th>ID</th>
										<th>이름</th>
                         				<th>등급</th>
                         				<th>신청일</th>
                         				<th>성별</th>
                         				<th>생년월일</th>
                         			</tr>
                         			</thead>
                         			<tbody id="tb-body">
	                         			<tr>
	                         				<td colspan="6" align="center">
		                       					해당 서포터즈에 신청한 회원이 없습니다.
		                       				</td>
                         				</tr>
                         			</tbody>
                         			
                         			<%-- <c:if test="${lists.size() == 0}">
                         				<tr>
	                         				<td colspan="6" align="center">
		                       					신청한 서포터즈가 없습니다.
		                       				</td>
                         				</tr>
                         			</c:if>
                         			<c:if test="${lists.size() != 0}">
                         				<c:forEach var="applicant" items="${lists}">
                         					<tr align="center">
	                         					<td>${applicant.user_id}</td>
	                         					<td>${applicant.name}</td>
	                         					<td>${applicant.user_role}
	                         						<c:if test="${applicant.user_role == 'ro1'}">일반</c:if>
													<c:if test="${applicant.user_role == 'r02'}">우수</c:if>
													<c:if test="${applicant.user_role == 'r99'}">관리자</c:if>
	                         					</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay2" value="${applicant.apply_date}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd"/>
													${fNewDay2 }
	                         					</td>
	                         					<td>${applicant.gender}</td>
	                         					<td>
	                         						<fmt:parseDate var="newDay" value="${applicant.birth}" pattern="yyyy-MM-dd"/>				
													<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
													${fNewDay }
	                         					</td>
                         					</tr>
                         				</c:forEach>
                         			</c:if> --%>
                         		</table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>      
    
    
    <script type="text/javaScript">
    
    	$(document).ready(function(){
    		change_supporters($("#keyword").val()) //서포터즈명 담아서 change_supporters() 함수로
    	})
    	
    	/* function fn_search(){ //검색버튼 클릭하면 실행
    		change_supporters($("#keyword").val());
    	} */
    	
    	
    	function getUserRoleName(userRole) { //userRole 코드 값으로 명칭을 치환
    		var userRoleName = '';
    		
    		switch(userRole) {
    			case 'r01' :
    			userRoleName = '일반';
    			break;
    			case 'r02': 
    				userRoleName = '우수';
    			break;
    			case 'r99': 
    				userRoleName = '관리자';
    			break;
    			default :
    				userRoleName = '';
    			break;
    		}
    		
    		return userRoleName;
    	}
    	
    	function getUserBirth(birth) {
    		
    		var date = new Date(birth);
	   		var year = date.getFullYear();
	   		var month = date.getMonth() + 1; 
	   		month = month < 10 ? '0' + month : month;
	   		var day = date.getDate();
	   		day = day < 10 ? '0' + day : day;
	   		
    		return year + '-' + month + '-' + day ;
    	}
    	
    	//select-option이 바뀔때마다 이 함수를 호출함
    	function change_supporters(value){
    		$.ajax({
				url : "/supportersapply/admin/supportersList.sua", //SupportersSepaAdminListController
				data : ({	
					keyword : value //서포터즈명
				}),
				type : 'POST',
				success : function(data){
					var htmls = "";
					$("#tb-body").html("");
					if(data.suLists.length > 0){
					
						for(let i=0; i < data.suLists.length; i++){
							htmls += "<tr align='center'>";
							htmls += "<td>"+ data.suLists[i].userId +"</td>";
							htmls += "<td>"+ data.suLists[i].name +"</td>";
							htmls += "<td>"+ getUserRoleName(data.suLists[i].userRole) +"</td>";
							htmls += "<td>"+ data.suLists[i].applydate +"</td>";
							htmls += "<td>"+ data.suLists[i].gender +"</td>";
							htmls += "<td>"+ getUserBirth(data.suLists[i].birth) +"</td>";
							htmls += "</tr>";
													
						}					
						$("#tb-body").append(htmls);
					}
					else{
						$("#tb-body").append("<tr><td colspan='6' style='text-align:center;'>해당 서포터즈에 신청한 회원이 없습니다.</td></tr>");
					}
				}//success		
			});//ajax	
    	}//fn_change_supporters
    </script>
    
    <%-- 
    getUserBirth(data.suLists[i].birth)
    
    htmls += "<td><c:if test='${"+ data.suLists[i].userRole + "= 'r01'}'>일반</c:if>";
	htmls += "<c:if test='${"+ data.suLists[i].userRole + "= 'r02'}'>우수</c:if>";
	htmls += "<c:if test='${"+ data.suLists[i].userRole + "= 'r99'}'>관리자</c:if></td>"; 
    
    <c:if test="${data.suLists[i].userRole == 'r01'}">일반</c:if>
	<c:if test="${data.suLists[i].userRole == 'r02'}">우수</c:if>
	<c:if test="${data.suLists[i].userRole == 'r99'}">관리자</c:if>
	
	<fmt:parseDate var="newDay" value="${data.suLists[i].birth}" pattern="yyyy-MM-dd"/>				
	<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
	${fNewDay } 
	 --%>
	
    
<%@ include file="../../common/common_bottom.jsp" %>      
