<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인</title>
<!-- 로그인페이지 -->
<%@ include file="../common/common_top.jsp"%>
<script type="text/javascript">
//login/all 경로로 주어야 로그인을 하지 않은 상태의 사용자들이 접근할 수 있음.

	//회원가입버튼 클릭
	function signUp(){
		location.href = "/login/all/signUp.lg";
	}
	//아이디찾기 클릭
	function findUserId(){
		location.href = "/login/all/findUserId.lg";
	}
	//비밀번호찾기 클릭
	function findPassword(){
		location.href = "/login/all/findPassword.lg";
	}
	
</script>
	<!-- Login Start -->
	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s"
				style="max-width: 500px;">
				<!-- <p class="fs-5 fw-bold text-primary">로그인</p> -->
				<h1 class="display-5 mb-5">로그인</h1>
			</div>

			<div class="row justify-content-center">
				<div class="col-lg-7">
					<div class="bg-light rounded p-4 p-sm-5 wow fadeInUp"
						data-wow-delay="0.1s">
						<div class="row g-3 " >
							<form class="form-signin" action="/login.lg" method="POST">
								<div class="col-12 col-md-9 mx-auto">
									<div class="form-floating">
										<input type="text" name="userId" id="userId"
											class="form-control  border-0" placeholder="ID" required autofocus> 
										<label for="userId">userId</label>
									</div>
								</div>
								<br>
								<div class="col-12 col-md-9 mx-auto">
									<div class="form-floating">
										<input type="password" name="password" id="password"
											class="form-control border-0" placeholder="Password" required autofocus>
										<label for="password">password</label>
									</div>
								</div>
								<br>
								<div class="col-12 text-center">
									<button class="btn btn-success" type="submit" id="submit">로그인</button>
									<c:if test="${param.err == true}">
										<p style="color: red">ID와 Password를 확인해주세요.</p>
									</c:if>
								</div>
								<br>
								<div class="col-12 mx-auto" align="center">
									<input type="button" class="btn btn-success" value="회원가입" onclick="signUp()">
								
									<input type="button" class="btn btn-success" value="아이디찾기" onclick="findUserId()">
								
									<input type="button" class="btn btn-success" value="비밀번호찾기" onclick="findPassword()">
								</div>
								
								
							</form>
					
						</div><!-- row g-3 -->
					</div><!-- bg-light rounded p-4 p-sm-5 wow fadeInUp -->
					
					
				</div><!-- col-lg-7 -->
			</div><!-- row justify-content-center -->
		</div><!-- container -->
	</div><!-- container-fluid py-5-->
	<!-- //Login End -->


	<%@ include file="../common/common_bottom.jsp"%>

</html>