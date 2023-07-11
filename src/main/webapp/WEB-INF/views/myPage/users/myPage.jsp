<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마이페이지</title>
<!-- nav에서 마이페이지 클릭 -> 마이페이지 홈(myPage.jsp) -->
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_myPage.jsp"%>
<script type="text/javascript">
	
	/* 등급안내 */
	function userRole_info(){
		window.open('userRole_info.us','등급안내','width=550,height=500');       
	}

</script>
	<!-- myPage Start -->
	<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">${usersBean.userId}님 회원정보</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto">
                        	
                        	<!-- 등록된 이미지 -->
                        	<div class="col-9">
							    <img 
							        alt="${usersBean.image}"
							        src="<c:choose>
							                <c:when test="${usersBean.image == null}">
							                    <%=request.getContextPath() %>/resources/images/users/user_basic_box.png
							                </c:when>
							                <c:otherwise>
							                    <%=request.getContextPath() %>/resources/images/users/${usersBean.image}
							                </c:otherwise>
							            </c:choose>"
							        style="width: 200px; height: 150px;"  >
							</div>

                            <!-- //등록된 이미지 -->

                            <!-- 아이디 -->
							<div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="userId" name="userId" 
                                    value="${usersBean.userId}" readonly="readonly">
                                    <label for="name">아이디</label>
                                </div>
                            </div>
                            <!-- //아이디 -->
                            
                            <!-- 이름 -->
							<div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="name" name="name" 
                                    value="${usersBean.name}" readonly="readonly">
                                    <label for="name">이름</label>
                                </div>
                                <form:errors cssClass="err" path="name"/>
                            </div>
                            <!-- //이름 -->
                            
                            <!-- 생년월일 -->
                            <fmt:parseDate var="parse_birth" value="${usersBean.birth}" pattern = "yyyy-MM-dd"/>​
							<fmt:formatDate  var="fmt_birth" value="${parse_birth}" pattern="yyyy-MM-dd"/>
							<div class="col-9">
							 	<div class="form-floating">
									<input type="date" class="form-control border-0" id="birth" name="birth" 
									value="${fmt_birth}" readonly="readonly">
                                    <label for="birth">생년월일</label>
								 </div>
							    <form:errors cssClass="err" path="birth"/>
							</div>
                            <!-- //생년월일 -->
                            
							<!-- 등급 -->
							<div class="col-12">
							  <div class="col-9" style="float: left">
							    <div class="form-floating">
							      <c:choose>
							        <c:when test="${getUserRole eq 'r01'}">
							          <c:set var="userRole" value="일반회원" />
							        </c:when>
							        <c:when test="${getUserRole eq 'r02'}">
							          <c:set var="userRole" value="우수회원" />
							        </c:when>
							        <c:when test="${getUserRole eq 'r99'}">
							          <c:set var="userRole" value="관리자" />
							        </c:when>
							      </c:choose>
							      <input type="text" class="form-control border-0" readonly="readonly" id="userRole" value="${userRole}">
							      <label for="userRole">등급</label>
							    </div>
							  </div>
							  <div style="float: left; display:inline-block; margin-left:10px;">
							    <button target="_blank" class="btn btn-secondary btn-sm" onclick="userRole_info()">
							      등급 안내
							    </button>
							  </div>
							</div>
							<!-- //등급 -->
                            
                            <!-- 가입일 -->
                            <fmt:parseDate	var="parse_udate"	value="${usersBean.udate}" 	pattern = "yyyy-MM-dd"/>​
							<fmt:formatDate var="fmt_udate" 	value="${parse_udate}" 		pattern = "yyyy-MM-dd"/>
							<div class="col-9">
							 	<div class="form-floating">
									<input type="date" class="form-control border-0" id="udate" name="udate"
									 value="${fmt_udate}" readonly="readonly" >
                                    <label for="udate">가입일</label>
								 </div>
							</div>
                            <!-- //가입일 -->
                            
                            <!-- 성별 -->
							<div class="col-9">
							 	<div class="form-floating">
									<c:set var="gender" value="${genderArr}"/>
									<c:forEach var="gender" items="${gender}">
										<input type="radio" class="form-check-input border-0" name="gender" id="gender" value="${gender}" 
										${usersBean.gender eq gender ? 'checked' : ''}>
										${gender} &nbsp;&nbsp;&nbsp;
											
									</c:forEach>
								  </div>
							</div>
                            <!-- //성별 --
                            
                            <!-- 이메일 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="email" name="email" 
                                    value="${usersBean.email}" readonly="readonly" >
                                    <label for="email">이메일</label>
                                </div>
                            </div>
                            <!-- //이메일 -->
                             
                            <!-- 연락처 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="phone" name="phone" 
                                    value="${usersBean.phone}" readonly="readonly">
                                    <label for="phone">연락처</label>
                                </div>
                            </div>
                            <!-- //연락처 -->
                            
                           <!-- 주소 -->
							<input type="hidden" class="form-control border-0" id="postcode" name="postcode" style="width: 50%" >
							<div class="col-12" >
								<div class="col-9"  style="margin-bottom: -20px">
									<div class="form-floating" ">
										<input type="text" class="form-control border-0" name="address" id="address" 
										value="${usersBean.address}" readonly="readonly" ><br>
										<label for="address">주소</label>
									</div>	
								</div>
                            </div>		
                            
                            <div class="col-9">
								<div class="form-floating" >
									<input  path="addressSub" class="form-control border-0" name="addressSub"  id="addressSub" 
									value="${usersBean.addressSub}" readonly="readonly" />
									<label for="addressSub">상세주소</label>
									<input type="hidden" class="form-control border-0" id="address_extra" placeholder="참고항목">
                           		</div>
							</div>	
                            <!-- //주소 -->
                            
                            <!-- point -->
							<fmt:formatNumber var="fmt_point" value="${usersBean.point}" pattern="###,###"/>
							<div class="col-9">
							    <div class="form-floating" align="right">
							        <input class="form-control border-0" name="point" id="point" 
							        value="${fmt_point}원" readonly="readonly" />
							        <label for="point">point</label>
							    </div>
							</div>
                            <!-- //point -->
                         </div><!--//row g-3  -->
                    	</div><!-- //bg-light -->
                    </div><!-- //col-lg-9  -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
    <!-- //myPage End -->
    

<%@ include file="../../common/common_bottom.jsp"%>
</html>