<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>아이디 찾기</title>
<!-- 로그인페이지(loginPage.jsp) or 비밀번호 찾기 페이지(findPassword.jsp)-> 아이디 찾기 클릭(findUserId.jsp) -->
<%@ include file="../common/common_top.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
//login/all 경로로 주어야 로그인을 하지 않은 상태의 사용자들이 접근할 수 있음.
	
	//비밀번호 찾기 클릭
	function findPassword(){
		location.href = "/login/all/findPassword.lg";
	}
	//로그인 클릭
	function login(){
		location.href = "/login/all/loginPage.lg";
	}
	
	/* 아이디 존재여부 확인하기 위한 param 값 aJax로 확인 */
	$(document).ready(function(){
		
		$('#submit').click(function(){ // 아이디찾기(submit) 버튼 눌렀을 때
			if($('input[name=name]').val() == ""){
				alert('이름을 입력하세요');
				return false;
			}
			if($('input[name=email]').val() == ""){
				alert('이메일을 입력하세요');
				return false;
			}
			if($('input[name=phone]').val() == ""){
				alert('연락처를 입력하세요');
				return false;
			}
			//alert($('input[name=name]').val() + " / " + $('input[name=email]').val() + " / " + $('input[name=phone]').val());
			$.ajax({
				url : '/login/all/findUserId.lg', //users로 경로잡으면 권한이 없기때문에 접근할 수 X
				type : 'post',
				data : ({
					input_name	: $('input[name=name]').val(),
					input_email	: $('input[name=email]').val(),
					input_phone	: $('input[name=phone]').val()
				}),
				success : function(data){ //
					//alert("success "+ $('input[name=name]').val() + " / " + $('input[name=email]').val() + " / " + $('input[name=phone]').val());
					if(data != 'NO'){
						var user_confirm = confirm("[ "+ data + " ] 님 안녕하세요, 로그인 창으로 이동하시겠습니까?");
						if(user_confirm == true){
							alert("로그인창으로 이동합니다.");
							location.href = "/login/all/loginPage.lg";
						}
						else if(user_confirm == false){
							
						}
					}else{
						alert("존재하지않는 아이디입니다.");
					}
					
				}//success
			});//ajax 
		
		});//submit - click
		/* 아이디 존재여부 확인하기 위한 param 값 aJax로 확인 */
		
		/* 연락처 필드의 값이 변경될 때마다 이벤트 처리 */
	    $('#phone').on('input', function() {
	        var phoneNumber = $(this).val();
	        var formattedNumber = formatPhoneNumber(phoneNumber);
	        $(this).val(formattedNumber);
	    });
	    /*//연락처 필드의 값이 변경될 때마다 이벤트 처리 */
	    
	})//ready
	
	/* 연락처 하이픈처리 */
	function formatPhoneNumber(phoneNumber) {
	    phoneNumber = phoneNumber.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	    var formattedNumber = '';
	    if (phoneNumber.length < 4) {
	        formattedNumber = phoneNumber;
	    } else if (phoneNumber.length < 7) {
	        formattedNumber = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3);
	    } else if (phoneNumber.length < 11) {
	        formattedNumber =
	            phoneNumber.substr(0, 3) +
	            '-' +
	            phoneNumber.substr(3, 4) +
	            '-' +
	            phoneNumber.substr(7);
	    } else {
	        formattedNumber =
	            phoneNumber.substr(0, 3) +
	            '-' +
	            phoneNumber.substr(3, 4) +
	            '-' +
	            phoneNumber.substr(7);
	    }
	    return formattedNumber;
	}
	/*//연락처 하이픈처리 */
</script>
<style>
	.err{
		font-size : 7px;
		color : red;
		font-weight : bold;
	}
</style>
	<!-- findUserId Start -->
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">아이디 찾기</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                            <!-- 이름 -->
                        	<div class="col-9 mx-auto">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="name" name="name" >
                                    <label for="name">이름</label>
                                </div>
                            </div>
                            <!-- //이름 -->
                            
                            <!-- 이메일 -->
                            <div class="col-9 mx-auto">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="email" name="email" >
                                    <label for="email">이메일</label>
                                </div>
                            </div>
                            <!-- //이메일 -->
                             
                            <!-- 연락처 -->
                            <div class="col-9 mx-auto">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="phone" name="phone" maxlength="13">
                                    <label for="phone">연락처</label>
                                </div>
                            </div>
                            <!-- //연락처 -->
                            
                            </div><!--//row g-3  -->
                            <br> <br> <br>
                            <!-- submit & onclick-->
                            <div class="col-12 text-center" style="line-height: 10px">
								<input type="submit" id="submit" class="btn btn-success" value="아이디찾기" >
							</div>
							<br>
							<div class="col-12 text-center" style="line-height: 10px">
								<input type="button" class="btn btn-success" value="비밀번호찾기" onclick="findPassword()">
								<input type="button" class="btn btn-success" value="로그인" onclick="login()">
                            </div>
                            <!-- //submit & onclick -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //findUserId End -->

<%@ include file="../common/common_bottom.jsp"%>
</html>