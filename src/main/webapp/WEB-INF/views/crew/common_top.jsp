<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<!DOCTYPE html>
<html>
<head>
	
    <meta charset="utf-8">
    <title>산타 - 등산의 모든 것</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="https://cdn.discordapp.com/attachments/1107810789395542067/1113670521465344000/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600;700&family=Open+Sans:wght@400;500&display=swap" rel="stylesheet">  

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/animate/animate.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/css/style.css" rel="stylesheet">
    
  <style type="text/css">
  
  </style>
</head>

<body>
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" role="status" style="width: 3rem; height: 3rem;"></div>
    </div>
    <!-- //Spinner End -->


    <!-- Topbar Start -->
    <div class="container-fluid bg-dark text-light px-0 py-2">
        <div class="row gx-0 d-none d-lg-flex">
            <div class="col-lg-7 px-5 text-start">
               <!--  
               <div class="h-100 d-inline-flex align-items-center me-4">
                    <span class="fa fa-phone-alt me-2"></span>
                    <span>+012 345 6789</span>
                </div>
                <div class="h-100 d-inline-flex align-items-center">
                    <span class="far fa-envelope me-2"></span>
                    <span>info@example.com</span>
                </div> -->
            </div>
            <div class="col-lg-5 px-5 text-end">
				<div class="h-100 d-inline-flex align-items-center mx-n2">
					<!-- <div style="display: inline-block;">
						사용자 이미지는 확인 중
					</div> -->
					<div style="display: inline-block;">
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.username"/>님
						</sec:authorize>
					</div>
					
                   <!--  <span>Follow Us:</span>
                    <a class="btn btn-link text-light" href=""><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-link text-light" href=""><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-link text-light" href=""><i class="fab fa-linkedin-in"></i></a>
                    <a class="btn btn-link text-light" href=""><i class="fab fa-instagram"></i></a> -->
                </div>
            </div>
        </div>
    </div>
    <!-- //Topbar End -->


    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-white navbar-light sticky-top p-0">
		<a href="/users/all/main.lg" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
			<h1 class="m-0">산타</h1>
		</a>
		<button type="button" class="navbar-toggler me-4"
			data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<!-- collapse navbar-collapse -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<div class="navbar-nav ms-auto p-4 p-lg-0">
				<a href="/users/all/main.lg" class="nav-item nav-link">Home</a>
				<a href="/mountain/all/list.mnt" class="nav-item nav-link">산 별 정보</a>
				<a href="/crewboard/all/list.bdcr" class="nav-item nav-link">크루</a>
				<a href="/qna/all/list.qna" class="nav-item nav-link">QnA</a>    
				
				<!-- 커뮤니티 -->  
				<div class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">커뮤니티</a>
             		<div class="dropdown-menu bg-light m-0"> 
		                <a href="/notice/all/list.no" class="dropdown-item">공지사항</a>
		                <a href="/board/all/list.br" class="dropdown-item">자유게시판</a>
		                <a href="/supporters/all/list.su" class="dropdown-item">서포터즈</a>
		                <a href="/products/all/list.prd" class="dropdown-item">거래게시판</a>
					</div><!-- //dropdown-menu -->
				</div>
				<!-- //커뮤니티 -->
				<sec:authorize access="isAuthenticated()">
					<a href="/users/user/mypage.us" class="nav-item nav-link">마이페이지</a>
				</sec:authorize>	
				
				<!-- 관리자 -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="/users/admin/usersList.us" class="nav-item nav-link">관리자</a>
				</sec:authorize>	
				<%-- 
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="nav-item dropdown">
					<a href="/users/admin/usersList.us" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">관리자</a>
             		<div class="dropdown-menu bg-light m-0"> 
		                <a href="/users/admin/usersList.us" class="dropdown-item">회원 관리</a> 
		                <a href="/crew/admin/list.cr" class="dropdown-item">크루 관리</a>
		                <a href="#" class="dropdown-item">산 관리</a>
		                <a href="#" class="dropdown-item">게시판 관리</a>
		                <a href="/pay/admin/waitlist.pay" class="dropdown-item">거래 관리</a>
		                <a href="#" class="dropdown-item">서포터즈 관리</a>
					</div><!-- //dropdown-menu -->
				</div>
				</sec:authorize>	 --%>			
				<!-- //관리자 -->
			</div>
			<!-- //navbar-nav -->
			
			<!-- 로그인 / 비로그인 -->
			<!-- 로그인하지 않은 상태 -->
			<sec:authorize access="isAnonymous()">
				<a href="/login/all/loginPage.lg" class="btn btn-primary py-4 px-lg-4 rounded-0 d-none d-lg-block">
					로그인<i class="fa fa-arrow-right ms-3"></i>
				</a> 
			</sec:authorize>
			<!-- //로그인하지 않은 상태 -->
			
			<!-- 로그인한 상태 -->
			<sec:authorize access="isAuthenticated()">
				<a href="/logout" class="btn btn-primary py-4 px-lg-4 rounded-0 d-none d-lg-block">
					로그아웃<i class="fa fa-arrow-right ms-3"></i>
				</a> 
			</sec:authorize>
			<!-- //로그인한 상태 -->
			<!-- //로그인 / 비로그인 -->
		</div><!-- //collapse navbar-collapse -->
	</nav><!-- //navbar -->
	<!-- //Navbar End -->
    


 
