<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원등급 수정</title>
<!-- 관리자페이지(회원관리) - 회원아이디 클릭했을 때 -> 회원의 등급변경가능 -->
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_admin.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		//alert(123);
		//회원가입(submit) 클릭 
		$('#submit').click(function(){
			var before_update = confirm($('input[name=userId]').val() + "님 의 등급을 변경하시겠습니까?");
			if(before_update == true){
				$.ajax({
					url : '/users/admin/usersUpdateRole.us', 
					type : 'post',
					data : ({
						input_userId	: $('input[name=userId]').val(),
						select_userRole : $('#userRole_update option:selected').val()
					}),
					success : function(data){ 
						//alert(data);
						if(data == 1){
							alert('회원등급이 변경되었습니다.');
						}else{
							alert("회원등급 변경에 실패했습니다.");
						} 
					}//success
				});//ajax 
			}else if(user_confirm == false){
				alert('회원등급 변경을 취소하셨습니다.');
			}
		});//click
	
	})//ready

	function List(){
		location.href = "/users/admin/usersList.us"; 
	}
</script>

<%
	String[] genderArr = {"남자", "여자"};
%>
	
	<!-- myPage Start -->
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">회원 등급 수정</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto">
                        	<!-- hidden -->
							<input type="hidden" name="userNum" 	value="${usersBean.userNum}">
                        	<input type="hidden" name="password" 	value="${usersBean.password}">
                        	<input type="hidden" name="status" 		value="${usersBean.status}">
                        	<input type="hidden" name="udate" 		value="${usersBean.udate}"> 
                        	<!-- //hidden -->
                        	<!-- 등록된 이미지 -->
                        	<div class="col-9">
								<img 
                            		alt="${usersBean.image}"
                            		src="<%=request.getContextPath() %>/resources/images/users/${usersBean.image}" 
                            		style="max-width: 100px; max-height: 100px;"  >
                             </div>	
                            <!-- //등록된 이미지 -->

                            <!-- 아이디 -->
							<div class="col-9">
                                <div class="form-floating">
                                	<input type="text" class="form-control border-0" id="userId" name="userId" 
                                    value="${usersBean.userId}" readonly="readonly">
                                    <label for="name">아이디</label>
                                </div>
                            </div>
                            <!-- //아이디 -->
                            
                            <!-- 이름 -->
							<div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="name" name="name" 
                                    value="${usersBean.name}" readonly="readonly">
                                    <label for="name">이름</label>
                                </div>
                                <form:errors cssClass="err" path="name"/>
                            </div>
                            <!-- //이름 -->
                            
                            <!-- 생년월일 -->
                            <fmt:parseDate var="parse_birth" value="${usersBean.birth}" pattern = "yyyy-MM-dd"/>​
							<fmt:formatDate  var="fmt_birth" value="${parse_birth}" pattern="yyyy-MM-dd"/>
							<div class="col-9">
							 	<div class="form-floating">
									<input type="date" class="form-control border-0" id="birth" name="birth" 
									value="${fmt_birth}" readonly="readonly">
                                    <label for="birth">생년월일</label>
								 </div>
							</div>
                            <!-- //생년월일 -->
                            
                            <!-- 등급 -->
                            <div class="col-12" >
								<div class="col-9" style="float: left"> 
									<div class="form-floating">
										<select id="userRole_update" class="form-select">
											<option value="r01" 
												<c:if test="${usersBean.userRole == 'r01'}">selected</c:if>
											>일반</option>
											<option value="r02" 
												<c:if test="${usersBean.userRole == 'r02'}">selected</c:if>
											>우수</option>
											<option value="r99" 
												<c:if test="${usersBean.userRole == 'r99'}">selected</c:if>
											>관리자</option>
										</select>
										<label for="userRole">등급</label>
									</div>
								</div>
                            </div>
                            <!-- //등급 --> 
                            
                            <!-- 성별 -->
							<div class="col-9">
							 	<div class="form-floating">
									<c:set var="gender" value="<%= genderArr %>"/>
									<c:forEach var="gender" items="${gender}">
											<input type="radio" class=" form-check-input border-0" name="gender" id="gender" 
											value="${gender}" readonly="readonly"
											<c:if test="${usersBean.gender eq gender}">checked</c:if>
											>${gender} &nbsp;&nbsp;&nbsp;
									</c:forEach>
								  </div>
							</div>
                            <!-- //성별 -->
                            
                            <!-- 이메일 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="email" name="email" 
                                    value="${usersBean.email}" readonly="readonly">
                                    <label for="email">이메일</label>
                                </div>
                            </div>
                            <!-- //이메일 -->
                             
                            <!-- 연락처 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="phone" name="phone" 
                                    value="${usersBean.phone}" readonly="readonly">
                                    <label for="phone">연락처</label>
                                </div>
                            </div>
                            <!-- //연락처 -->
                            
                           <!-- 주소 -->
                            <input type="hidden" class="form-control border-0" id="postcode" name="postcode" style="width: 50%" >
                           
							<div class="col-12" >
								<div class="col-9" style="float: left;">
									<div class="form-floating" ">
										<input type="text" class="form-control border-0" name="address" id="address" 
										value="${usersBean.address}" readonly="readonly"><br>
										<label for="address">주소</label>
									</div>	
								</div>
                            </div>		
                            
                            <div class="col-9" style="margin-top: 0" ><!-- style="line-height: 10px" -->	
									<div class="form-floating">
										<input  path="addressSub" class="form-control border-0" name="addressSub"  id="addressSub" 
										value="${usersBean.addressSub}" readonly="readonly"/>
										<label for="addressSub">상세주소</label>
										<input type="hidden" class="form-control border-0" id="address_extra" placeholder="참고항목">
	                           		</div>
							</div>	
                            <!-- //주소 -->
                           
                            <!-- submit -->
                            <div class="col-12 text-center" style="line-height: 10px">
								<input type="submit" id="submit" class="btn btn-success" value="수정하기" >
								<input type="button" class="btn btn-success" value="목록" onclick="List()">
							</div>
                            <!-- //submit -->
                         </div><!--//row g-3  -->
                    	</div><!-- //bg-light -->
                    </div><!-- //col-lg-9  -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //myPage End -->
    

<%@ include file="../../common/common_bottom.jsp"%>
</html>