<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마이페이지 수정</title>
<!-- 마이페이지-회원정보변경 클릭했을 때,비밀번호 확인 후 넘어온 회원정보 수정 페이지 -->
<%@ include file="../../common/common_top.jsp"%>
<%@ include file="../../common/common_nav_myPage.jsp"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function confirmUpdate(event) {
		  event.preventDefault(); // 기본 동작을 막음
	
		  var confirmed = confirm("정말로 정보를 수정하시겠습니까?");
		  if (confirmed) {
		    // 수정 정보를 서버로 전송하는 코드
		    document.getElementById("updateForm").submit();
		  }
	}
	/* 카카오 주소 API */
	function execDaumPostcode() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("address_extra").value = extraAddr;
	            
	            } else {
	                document.getElementById("address_extra").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("addressSub").focus();
	        }
	    }).open();
	}
	/* 카카오 주소 API */
	
	/* 등급안내 */
	function userRole_info(){
		window.open('userRole_info.us','등급안내','width=550,height=500');       
	}
	function myPage(){
		location.href = "/users/user/mypage.us"; 
	}
	
	/* 이미지 미리보기 */
	function previewImage(f){

	var file = f.files;

	// 확장자 체크
	if(!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)){
		alert('gif, jpg, png 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);

		// 선택한 파일 초기화
		f.outerHTML = f.outerHTML;

		document.getElementById('preview').innerHTML = '';

	}
	else {

		// FileReader 객체 사용
		var reader = new FileReader();

		// 파일 읽기가 완료되었을때 실행
		reader.onload = function(rst){
			document.getElementById('preview').innerHTML = '<h6>이미지 미리보기</h6><img src="' + rst.target.result + '"style="max-width: 300px;  max-height: 300px;">';
		}

		// 파일을 읽는다
		reader.readAsDataURL(file[0]);

	}
}


</script>

<style>
	.err{
		font-size : 7px;
		color : red;
		font-weight : bold;
	}
</style>

	<!-- myPage Start -->
	<form:form name="usersUpdate" commandName="usersBean" action="/users/user/usersUpdate.us" method="post" enctype="multipart/form-data">
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">회원정보 수정</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto">
                        	<!-- hidden -->
                        	<input type="hidden" name="userNum" 	value="${usersBean.userNum}">
                        	<input type="hidden" name="password" 	value="${usersBean.password}">
                        	<input type="hidden" name="status" 		value="${usersBean.status}">
                        	<!-- //hidden -->
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
                            <!-- 변경할 이미지 등록 -->
                            <div class="col-9">
                                <div class="form-floating">
                                	<input type="file" class="form-control border-0" name="upload" id="upload"
                                		value="${usersBean.upload}"
                                	 	accept="image/*" onchange="previewImage(this)" />
									<div id="preview" style="margin: 10px;">
									</div>
									<input type="hidden" name="upload2" value="${usersBean.image}">
                                </div>
                            </div>
                            <!-- //변경할 이미지 등록 -->	
                            
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
                                    <input type="text" class="form-control border-0" id="name" name="name" value="${usersBean.name}">
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
									<input type="date" class="form-control border-0" id="birth" name="birth" value="${fmt_birth}"
									readonly="readonly">
                                    <label for="birth">생년월일</label>
								 </div>
							    <form:errors cssClass="err" path="birth"/>
							</div>
                            <!-- //생년월일 -->
                            
                           	<!-- 등급 -->
							<div class="col-12">
							  <div class="col-9" style="float: left">
								<c:choose>
							        <c:when test="${usersBean.userRole eq 'r01'}">
							          <c:set var="userRole" value="일반회원" />
							        </c:when>
							        <c:when test="${usersBean.userRole eq 'r02'}">
							          <c:set var="userRole" value="우수회원" />
							        </c:when>
							        <c:when test="${usersBean.userRole eq 'r99'}">
							          <c:set var="userRole" value="관리자" />
							        </c:when>
							      </c:choose>							    
							    <div class="form-floating">
							      <input type="text" class="form-control border-0" id="userRole" value="${userRole}" readonly="readonly">
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
										<input type="radio" class="form-check-input border-0" name="gender" id="gender_${gender}" value="${gender}" 
										${usersBean.gender eq gender ? 'checked' : ''}>
										${gender} &nbsp;&nbsp;&nbsp;
											
									</c:forEach>
								  </div>
							    <form:errors cssClass="err" path="gender"/>
							</div>
							<!-- //성별 -->
                            
                            <!-- 이메일 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="email" name="email" value="${usersBean.email}">
                                    <label for="email">이메일</label>
                                </div>
                                <form:errors cssClass="err" path="email"/>
                            </div>
                            <!-- //이메일 -->
                             
                            <!-- 연락처 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="phone" name="phone" value="${usersBean.phone}">
                                    <label for="phone">연락처</label>
                                </div>
                                <form:errors cssClass="err" path="phone"/>
                            </div>
                            <!-- //연락처 -->
                            
                           <!-- 주소 -->
                            <input type="hidden" class="form-control border-0" id="postcode" name="postcode" style="width: 50%" >
                           
							<div class="col-12" >
								<div class="col-9" style="float: left;">
									<div class="form-floating"  style="margin-bottom: -20px;" >
										<input type="text" class="form-control border-0" name="address" id="address" value="${usersBean.address}"><br>
										<label for="address">주소</label>
									</div>	
									<form:errors cssClass="err" path="address"/>
								</div>
								<div style="float: left; margin-left:10px; display:inline-block;" >
                                	<input type="button" class="btn btn-secondary btn-sm" onclick="execDaumPostcode()" value="주소 수정">
                            	</div>
                            </div>		
                            
                            <div class="col-9">
									<div class="form-floating" >
										<input  path="addressSub" class="form-control border-0" name="addressSub"  id="addressSub" value="${usersBean.addressSub}"/>
										<label for="addressSub">상세주소</label>
										<input type="hidden" class="form-control border-0" id="address_extra" placeholder="참고항목">
	                           		</div>
	                           		<form:errors cssClass="err" path="addressSub"/>
							</div>	
                            <!-- //주소 -->
                           
                            <!-- point -->
                            <fmt:formatNumber var="fmt_point" value="${usersBean.point}" pattern="###,###"/>
                            <div class="col-9">
                                <div class="form-floating">
									<input type="text" class="form-control border-0" id="point" name="point" value="${usersBean.point}" readonly>
									<label for="point">point</label>
								</div>
								<form:errors cssClass="err" path="point"/>
                            </div>
                            <!-- //point -->
                           
                            <!-- submit -->
                            <div class="col-12 text-center" style="line-height: 10px">
								<input type="submit" class="btn btn-success"  value="수정하기" onclick="confirmUpdate()">
								<input type="reset" class="btn btn-success" value="다시작성" >
								<input type="button" class="btn btn-success" value="목록" onclick="myPage()">
							</div>
                            <!-- //submit -->
                         </div><!--//row g-3  -->
                    	</div><!-- //bg-light -->
                    </div><!-- //col-lg-9  -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div><!-- //container-fluid -->
        </form:form>
    <!-- //myPage End -->
    

<%@ include file="../../common/common_bottom.jsp"%>
</html>