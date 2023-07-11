<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>권한없음(접근 제한 페이지)</title>
<!-- 로그인페이지(loginPage.jsp) -> 회원가입 클릭(signUp.jsp)  -->
<%@ include file="../common/common_top.jsp"%>
    <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
        <div class="container text-center">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <i class="bi bi-exclamation-triangle display-1 text-primary"></i>
                    <h1 class="display-1">접근제한</h1>
                    <h1 class="mb-4">권한이 없는 페이지 입니다.</h1>
                    <a class="btn btn-primary py-3 px-4" href="#" onclick="history.go(-1)">
                    	<input type="button" class="btn btn-success" value="돌아가기" >
                    </a>
                </div>
            </div>
        </div>
    </div>

<%@ include file="../common/common_bottom.jsp"%>
</html>