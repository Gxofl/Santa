<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<!-- 마이페이지-회원정보변경 클릭했을 때, 수정페이지로 이동 전 비밀번호 확인 페이지 -->
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_myPage.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	
	/* 비밀번호 동일여부 확인하기 위한 param 값 aJax로 확인 */
	$(document).ready(function(){
		
		$('#submit').click(function(){ // 확인(submit) 버튼 눌렀을 때
			if($('input[name=password]').val() == ""){
				alert('비밀번호를 입력하세요');
				return false;
			}
			
			$.ajax({
				url : '/users/user/usersInfoCheck.us', 
				type : 'post',
				data : ({
					input_password	: $('input[name=password]').val()
				}),
				success : function(data){ 
					if(data == "NO" || data != "YES"){
						alert("비밀번호가 일치하지않습니다.");
					} else{
						location.href = "/users/user/usersUpdate.us";
					}
				}//data
			});//ajax 
		
		});//submit - click
		/* 비밀번호 동일여부 확인하기 위한 param 값 aJax로 확인 */
			
	})//ready
</script>
</head>
<body>

	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
				style="max-width: 500px;">
				<h1 class="display-5 mb-5">비밀번호 확인</h1>
				<h6>사용자 정보 확인을 위해 비밀번호를 입력해주세요</h6>
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
                                    <label for="userId">아이디</label>
                                </div>
                            </div>
                            <!-- //아이디 -->
                            
                            <!-- 비밀번호 -->
                            <div class="col-9 mx-auto">
	                            <div class="form-floating mx-auto">
	                                <input type="password" class="form-control border-0" id="password" name="password" >
	                                <label for="password">비밀번호</label>
	                            </div>
	                        </div>    
                            <!-- //비밀번호 -->
						</div><!--//row g-3  -->
						<!-- submit -->
						<div class="col-12 text-center" style="margin-top: 30px;">
							<input type="submit" id="submit"
								class="btn btn-success" value="확인">
						</div>
						<!-- //submit -->
						
					</div><!-- //bg-light -->
				</div><!-- //col-lg-7 -->
			</div><!-- //row justify-content-center -->
		</div><!-- //container -->
	</div><!-- //container-fluid -->


<%@ include file="../../common/common_bottom.jsp"%>
</html>