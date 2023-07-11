<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/common_top.jsp"%>
	<!-- Carousel Start -->
    <div class="container-fluid p-0 wow fadeIn" data-wow-delay="0.1s">
        <div id="header-carousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="w-100" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/carousel-1.jpg" alt="Image">
                    <div class="carousel-caption">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <h1 class="display-1 text-white mb-5 animated slideInDown">
										"Embrace the thrill of hiking and plogging!"
									</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
               <%--  <div class="carousel-item">
                    <img class="w-100" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/carousel-2.jpg" alt="Image">
                    <div class="carousel-caption">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-7">
                                    <h1 class="display-1 text-white mb-5 animated slideInDown">
										"Embrace the thrill of hiking and plugging!"
									</h1>
                                    <a href="" class="btn btn-primary py-sm-3 px-sm-4">Explore More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> --%>
            <!-- <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#header-carousel"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button> -->
        </div>
    </div>
    <!-- Carousel End -->


    <!-- Top Feature Start -->
    <div class="container-fluid top-feature py-5 pt-lg-0">
        <div class="container py-5 pt-lg-0">
            <div class="row gx-0">
            </div>
        </div>
    </div>
    <!-- Top Feature End -->    
     <!-- Service Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <p class="fs-5 fw-bold text-primary">Our Services</p>
                <h1 class="display-5 mb-5">Like hike off the hog</h1>
            </div>
            <div class="row g-4">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                    <div class="service-item rounded d-flex h-100">
                        <div class="service-img rounded">
                            <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/main_mountain.jpg" alt="">
                        </div>
                        <div class="service-text rounded p-5">
                            <div class="btn-square rounded-circle mx-auto mb-3">
                                <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/icon/icon-2.png" alt="Icon">
                            </div>
                            <h4 class="mb-3">산별정보</h4>
                            <p class="mb-4"> 국내 산 정보를 제공합니다. 산의 높이와 위치는 물론, 산에 대한 상세한 정보와 사진을 함께 열람하실 수 있습니다. 원하는 검색어로 정보를 탐색할 수 있습니다. </p>
                            <a class="btn btn-sm" href="/mountain/all/list.mnt"><i class="fa fa-plus text-primary me-2"> Read More</i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="service-item rounded d-flex h-100">
                        <div class="service-img rounded">
                            <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/service-5.jpg" alt="">
                        </div>
                        <div class="service-text rounded p-5">
                            <div class="btn-square rounded-circle mx-auto mb-3">
                                <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/icon/icon-3.png" alt="Icon">
                            </div>  
                            <h4 class="mb-3">서포터즈 모집</h4>
                            <p class="mb-4">다양한 주제로 서포터즈를 모집합니다! 마음에 드는 서포터즈에 바로 신청하세요!</p>
                            <a class="btn btn-sm" href="/supporters/all/list.su"><i class="fa fa-plus text-primary me-2"></i>Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                    <div class="service-item rounded d-flex h-100">
                        <div class="service-img rounded">
                            <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/service-6.jpg" alt="">
                        </div>
                        <div class="service-text rounded p-5">
                            <div class="btn-square rounded-circle mx-auto mb-3">
                                <img class="img-fluid" src="<%=request.getContextPath()%>/resources/bootstrap/user/img/icon/icon-2.png" alt="Icon">
                            </div>
                            <h4 class="mb-3">등산 & 플로깅 크루</h4>
                            <p class="mb-4">직접 크루를 만들어 산타 회원들과 등산 or 플로깅을 즐겨보세요! 즐거움이 배가 될거예요!</p>
                            <a class="btn btn-sm" href="/crewboard/all/list.bdcr"><i class="fa fa-plus text-primary me-2"></i>Read More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Service End -->
    



<%@ include file="common/common_bottom.jsp"%>

</html>