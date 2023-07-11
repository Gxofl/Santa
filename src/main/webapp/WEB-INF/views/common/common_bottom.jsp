<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer mt-3 py-1 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-3">
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-4">산별정보</h4>
                    <a class="btn btn-link" href="/mountain/all/list.mnt">전체목록</a>
                    <a class="btn btn-link" href="/mountain/all/list.mnt?category=difficulty_easy">난이도별 조회</a>
                    <a class="btn btn-link" href="/mountain/all/list.mnt?category=great">100대 명산 조회</a>
                    <a class="btn btn-link" href="/mountain/all/list.mnt?category=national">국립공원 조회</a>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-4">산타 회원</h4>
                    <a class="btn btn-link" href="/login/all/signUp.lg">회원가입</a>
                    <a class="btn btn-link" href="/login/all/findUserId.lg">아이디 찾기</a>
                    <a class="btn btn-link" href="/login/all/findPassword.lg">비밀번호 찾기</a>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-4">크루</h4>
                    <a class="btn btn-link" href="/crewboard/all/list.bdcr">크루모집 게시판</a>
                    <a class="btn btn-link" href="/crew/user/insert.cr">크루 생성</a>
                    <a class="btn btn-link" href="/crewboard/user/insert.cr">크루 모집 글작성</a>
                </div>

                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-4">커뮤니티</h4>
                    <a class="btn btn-link" href="/notice/all/list.no">공지사항</a>
                    <a class="btn btn-link" href="/board/all/list.br">자유게시판</a>
                    <a class="btn btn-link" href="/supporters/all/list.su">서포터즈</a>
                    <a class="btn btn-link" href="/products/all/list.prd">거래 게시판</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->


    <!-- Copyright Start -->
    <div class="container-fluid copyright py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                    &copy; <a class="border-bottom" href="#">Santa</a>, All Right Reserved.
                </div>
                <div class="col-md-6 text-center text-md-end">
                    <!--/*** This template is free as long as you keep the footer authorâs credit link/attribution link/backlink. If you'd like to use the template without the footer authorâs credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                    Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a href="https://themewagon.com">ThemeWagon</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Copyright End -->


<!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/wow/wow.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/counterup/counterup.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/parallax/parallax.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/isotope/isotope.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/lib/lightbox/js/lightbox.min.js"></script>

    <!-- Template Javascript -->
    <script src="<%=request.getContextPath()%>/resources/bootstrap/user/js/main.js"></script>
</body>

</html>