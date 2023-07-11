<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 변경</title>
<!-- 마이페이지-비밀번호 변경 클릭했을 때, 비밀번호 변경페이지(usersUpdatePassword.jsp) -->
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_myPage.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	
	function myPage(){
		location.href = "/users/user/mypage.us"; 
	}	
	/* 비밀번호 동일여부 확인하기 위한 param 값 aJax로 확인 */
	$(document).ready(function(){
		
		$('#submit').click(function(){ // 확인(submit) 버튼 눌렀을 때
			if($('input[name=password]').val() == ""){
				alert('비밀번호를 입력하세요.');
				return false;
			}
		
			if($('input[name=password_new]').val() == ""){
				alert('새 비밀번호를 입력하세요.');
				return false;
			}
			
			if($('input[name=password_new_check]').val() == ""){
				alert('새 비밀번호 확인을 입력하세요.');
				return false;
			}
			
			if(	($('input[name=password]').val()) ==
				($('input[name=password_new]').val())	){
				alert('기존과 동일한 비밀번호입니다.');
				return false;
			}
			
			if(	($('input[name=password_new]').val()) !=
				($('input[name=password_new_check]').val())	){
				alert('비밀번호가 일치하지않습니다.');
				return false;
			}
			$.ajax({
				url : '/users/user/usersPasswordUpdate.us', 
				type : 'post',
				data : ({
					input_password		: $('input[name=password]').val(),
					input_password_new	: $('input[name=password_new]').val(),
				}),
				success : function(data){ 4
					if(data == "NO" || data != "YES"){
						alert("비밀번호가 일치하지않습니다.");
					} else{
						alert("비밀번호가 수정되었습니다.");
						location.href = "/users/user/mypage.us";
					}
				}//data
			});//ajax 
		
		});//submit - click
		
		$('#reset').click(function(){ // 확인(reset) 버튼 눌렀을 때(초기화)
			$('input[name=password]').val("");
		
			$('input[name=password_new]').val("");
			
			$('input[name=password_new_check]').val("");
		});//reset - click
	})//ready
</script>
	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
				style="max-width: 500px;">
				<h1 class="display-5 mb-5">비밀번호 변경</h1>
				<h6>변경하실 비밀번호를 입력해주세요.</h6>
			</div>
			
			<div class="row justify-content-center">
				<div class="col-lg-7 mx-auto">
					<div class="bg-light rounded p-4 p-sm-5 wow fadeInUp"
						data-wow-delay="0.1s">
						<div class="row g-3 mx-auto">
							<!-- 아이디 -->
							<div class="col-9 mx-auto">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="userId" name="userId" value="${userId}" disabled>
                                    <label for="name">아이디</label>
                                </div>
                            </div>
                            <!-- //아이디 -->
                            
                            <!-- 현재 비밀번호 -->
                            <div class="col-9 mx-auto">
	                            <div class="form-floating mx-auto">
	                                <input type="password" class="form-control border-0" id="password" name="password" >
	                                <label for="password">현재 비밀번호</label>
	                            </div>
	                        </div>    
                            <!-- //현재 비밀번호 -->
                            
                             <!-- 새로운 비밀번호 -->
                            <div class="col-9 mx-auto">
	                            <div class="form-floating mx-auto">
	                                <input type="password" class="form-control border-0" id="password_new" name="password_new" >
	                                <label for="password_new">새 비밀번호</label>
	                            </div>
	                        </div>    
                            <!-- //새로운 비밀번호 -->
                            
                             <!-- 새로운 비밀번호 확인 -->
                            <div class="col-9 mx-auto">
	                            <div class="form-floating mx-auto">
	                                <input type="password" class="form-control border-0" id="password_new_check" name="password_new_check" >
	                                <label for="password_new_check">새 비밀번호 확인</label>
	                            </div>
	                        </div>    
                            <!-- //새로운 비밀번호 확인 -->
						</div><!--//row g-3  -->
						<!-- submit -->
						<div class="col-12 text-center" style="margin-top: 30px;">
							<input type="submit" id="submit" class="btn btn-success" value="확인">
							<input type="reset"  id="reset"  class="btn btn-success" value="다시작성" >
							<input type="button" class="btn btn-success" value="목록" onclick="myPage()">
						</div>
						<!-- //submit -->
						
					</div><!-- //bg-light -->
				</div><!-- //col-lg-7 -->
			</div><!-- //row justify-content-center -->
		</div><!-- //container -->
	</div><!-- //container-fluid -->

    

<%@ include file="../../common/common_bottom.jsp"%>
</html>