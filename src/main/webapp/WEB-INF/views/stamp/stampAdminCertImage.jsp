<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급안내</title>
<!-- 관리자-스탬프관리-인증사진 확인 클릭 -->
 	<!-- Favicon -->
    <link href="https://cdn.discordapp.com/attachments/1107810789395542067/1113670521465344000/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600;700&family=Open+Sans:wght@400;500&display=swap" rel="stylesheet">  

    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/animate/animate.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/bootstrap/user/css/style.css" rel="stylesheet">
</head>
<body>
	
	 <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">${certBean.usersid}님의 인증사진</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                    	<h3>${certBean.mountainname}</h3>
                        <div class="row g-3 mx-auto" >
                        		<img src="<%=request.getContextPath()%>/resources/images/stamp/cert/${certBean.stampimage}" alt="인증사진" style="width:450px;">
                            </div><!--//row g-3  --> 
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //findUserId End -->
   
</body>
</html>