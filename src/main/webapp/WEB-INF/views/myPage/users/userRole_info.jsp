<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급안내</title>
<!-- 마이페이지-회원정보변경에서 등급안내 클릭 -->
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
<style type="text/css">
	table {
		border-collapse: separate;
		border-spacing: 0 10px;
	}
</style>    
</head>
<body>
	
	 <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">사용자 등급 안내</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         <table border="5" >
							<tr >
								<td width="30%">일반 등급</td>
								<td>조건 없음</td>
							</tr>
							 <tr>
								<td width="30%" >우수 등급</td>
								<td>스탬프 10개 등록 완료 시 자동으로 등업합니다.</td>
							</tr>
                         </table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //findUserId End -->
   
</body>
</html>